/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.PartenaireDao;
import dao.ProposerDao;
import dao.ResponsableActiviteDao;
import entities.Partenaire;
import entities.Prestation;
import entities.ResponsableActivite;
import forms.ModifierPartenaireFormChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * servlet nommée ModifierPartenaire qui gère les requêtes HTTP GET et POST /
 * contrôleur dans l'architecture MVC Affichage des détails d'un partenaire pour
 * modification Validation des données soumises via un formulaire Gestion des
 * erreurs de validation Mise à jour des données dans la base de données
 * Préparation des données pour l'affichage après la modification Interaction
 * avec la couche de présentation
 *
 * @author cberge
 */
@WebServlet("/modifier_partenaire")
@SuppressWarnings("serial")

public class ModifierPartenaire extends HttpServlet {

    /**
     * Traite les requêtes GET pour afficher le formulaire de modification du
     * partenaire. Charge les informations du partenaire à partir de
     * l'identifiant fourni dans les paramètres de la requête. Récupère
     * également la liste des responsables d'activité depuis la base de données.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        int partenaireId = Integer.parseInt(req.getParameter("id"));

        //Utilise le DAO (Data Access Object) pour lire les informations du collaborateur à partir de la base de données.
        PartenaireDao partenaireDao = new PartenaireDao();
        Partenaire partenaire = partenaireDao.read(partenaireId);

        // Simuler la récupération de la liste des responsables
        //Utilise le DAO pour récupérer toutes les activités responsables et celles associées au collaborateur.
        ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        Collection<ResponsableActivite> responsableActiviteList = responsableActiviteDao.list();
        Collection<ResponsableActivite> listResponsableActivitePartenaire = responsableActiviteDao.listResponsablesActivite(partenaire.getId());

        //Crée une liste d'IDs des responsables à partir de la collection des responsables associés au collaborateur
        List<Integer> selectedResponsables = new ArrayList<>();
        // Parcourir la collection et ajouter les ID des responsables à la liste
        for (ResponsableActivite responsable : listResponsableActivitePartenaire) {
            selectedResponsables.add(responsable.getId());
        }
        //Redirige vers une page d'erreur si le collaborateur n'est pas trouvé dans la base de données
        if (partenaire == null) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
            return;
        }
        req.setAttribute("partenaire", partenaire);
        req.setAttribute("selectedResponsables", selectedResponsables);
        req.setAttribute("responsableActiviteList", responsableActiviteList);

        //Transfère la requête et la réponse à la page JSP modifierCollaborateur.jsp pour afficher les informations du collaborateur et les responsables.
        req.getRequestDispatcher("/WEB-INF/jsp/modifierPartenaire.jsp").forward(req, resp);
    }

    /**
     * Traite les requêtes POST pour soumettre les modifications du formulaire
     * de modification du partenaire. Valide les données du formulaire, gère les
     * erreurs éventuelles et met à jour les informations du partenaire en base
     * de données.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        ModifierPartenaireFormChecker nv = new ModifierPartenaireFormChecker(req);
        Partenaire partenaire = nv.checkForm();

        ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        Collection<ResponsableActivite> responsableActiviteList = responsableActiviteDao.list();
        Collection<ResponsableActivite> listResponsableActivitePartenaire = responsableActiviteDao.listResponsablesActivite(partenaire.getId());

        List<Integer> selectedResponsables = new ArrayList<>();
        for (ResponsableActivite responsable : listResponsableActivitePartenaire) {
            selectedResponsables.add(responsable.getId());
        }

        if (nv.getErrors().isEmpty()) {

            PartenaireDao partenaireDao = new PartenaireDao();
            ProposerDao proposerDao = new ProposerDao();

            String[] responsableActiviteIds = req.getParameterValues("responsable");
            List<Integer> responsableActiviteIdList = new ArrayList<>();

            if (responsableActiviteIds != null) {
                for (String idStr : responsableActiviteIds) {
                    int id = Integer.parseInt(idStr);
                    responsableActiviteIdList.add(id);
                }
            }

            partenaire.setResponsableIds(responsableActiviteIdList);
            try {
                partenaireDao.update(partenaire);
            } catch (SQLException ex) {
                Logger.getLogger(ModifierPartenaire.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("partenaire", partenaire);

            List<String> responsableNoms = new ArrayList<>();
            for (int responsableId : responsableActiviteIdList) {
                ResponsableActivite responsable = responsableActiviteDao.read(responsableId);
                if (responsable != null) {
                    responsableNoms.add(responsable.getNom());
                }
            }

            String responsablesActivite = String.join(", ", responsableNoms);
            
            
            Collection<Prestation> prestations = partenaireDao.listPrestationPartenaire(partenaire.getId());
            List<String> responsableNomsPrestation = new ArrayList<>();
            List<Integer> respIdAdd = new ArrayList<>();
            for (Prestation presta : prestations) {
                Integer raPrestation = presta.getId_ra();
                boolean raExists = respIdAdd.stream()
                        .anyMatch(p -> p == raPrestation);
                if (!raExists) {
                    String nomRaPrestation = responsableActiviteDao.read(raPrestation).getNom();
                    responsableNomsPrestation.add(nomRaPrestation);
                    respIdAdd.add(raPrestation);
                }
            }

            String responsablesActivitePrestations = String.join(", ", responsableNomsPrestation);
            req.setAttribute("responsablesActivitePrestation", responsablesActivitePrestations);
            
            
            req.setAttribute("responsablesActivite", responsablesActivite);
            req.setAttribute("message", "Votre partenaire est bien modifié");
            req.getRequestDispatcher("/WEB-INF/jsp/afficherPartenaire.jsp").forward(req, resp);

        } else {
            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
            req.setAttribute("selectedResponsables", selectedResponsables);
            req.setAttribute("responsableActiviteList", responsableActiviteList);
            req.setAttribute("errors", nv.getErrors());
            req.setAttribute("partenaire", partenaire);
            req.getRequestDispatcher("/WEB-INF/jsp/modifierPartenaire.jsp").forward(req, resp);
        }
    }
}
