/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import dao.PossederDao;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.Prestation;
import entities.ResponsableActivite;
import forms.ModifierCollaborateurFormChecker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * servlet nommée ModifierCollaborateur qui gère les requêtes HTTP GET et POST /
 * contrôleur dans l'architecture MVC Affichage des détails d'un collaborateur
 * pour modification Validation des données soumises via un formulaire Gestion
 * des erreurs de validation Mise à jour des données dans la base de données
 * Préparation des données pour l'affichage après la modification Interaction
 * avec la couche de présentation
 *
 * @author cberge
 */
@WebServlet("/modifier_collaborateur")
@SuppressWarnings("serial")

public class ModifierCollaborateur extends HttpServlet {

    // private static final Logger logger = Logger.getLogger(ModifierCollaborateur.class.getName());
    /**
     * Traite les requêtes GET pour afficher le formulaire de modification du
     * collaborateur. Charge les informations du collaborateur à partir de
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
        //Récupère l'ID du collaborateur à partir des paramètres de la requête et le convertit en entier.
        int collaborateurId = Integer.parseInt(req.getParameter("id"));

        //Utilise le DAO (Data Access Object) pour lire les informations du collaborateur à partir de la base de données.
        CollaborateurDao collaborateurDao = new CollaborateurDao();
        Collaborateur collaborateur = collaborateurDao.read(collaborateurId);

        // Simuler la récupération de la liste des responsables
        //Utilise le DAO pour récupérer toutes les activités responsables et celles associées au collaborateur.
        ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        Collection<ResponsableActivite> responsableActiviteList = responsableActiviteDao.list();
        Collection<ResponsableActivite> listResponsableActiviteCollaborateur = responsableActiviteDao.listResponsableActivite(collaborateur.getId());

        //Crée une liste d'IDs des responsables à partir de la collection des responsables associés au collaborateur
        List<Integer> selectedResponsables = new ArrayList<>();
        // Parcourir la collection et ajouter les ID des responsables à la liste
        for (ResponsableActivite responsable : listResponsableActiviteCollaborateur) {
            selectedResponsables.add(responsable.getId());
        }
        //Redirige vers une page d'erreur si le collaborateur n'est pas trouvé dans la base de données
        if (collaborateur == null) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
            return;
        }

        // Exemple d'appel à writeHistory pour enregistrer la lecture des détails du collaborateur
        //Détermine si les valeurs "oui" et "a vie" sont sélectionnées pour le RQTH
        boolean isOuiSelected = "oui".equals(collaborateur.getRqth());
        boolean isAVieSelected = "a vie".equals(collaborateur.getRqth());

        //Ajoute les informations nécessaires en tant qu'attributs à l'objet HttpServletRequest pour qu'elles soient accessibles dans la JSP
        req.setAttribute("isOuiSelected", isOuiSelected);
        req.setAttribute("isAVieSelected", isAVieSelected);
        req.setAttribute("collaborateur", collaborateur);
        req.setAttribute("selectedResponsables", selectedResponsables);
        req.setAttribute("responsableActiviteList", responsableActiviteList);

        //Formate la date de renouvellement si le collaborateur a le RQTH et une date de renouvellement spécifiée.
        if ("oui".equals(collaborateur.getRqth()) && collaborateur.getDate_de_renouvellement() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = collaborateur.getDate_de_renouvellement().format(formatter);
            req.setAttribute("formattedDate", formattedDate);
        }
        //Transfère la requête et la réponse à la page JSP modifierCollaborateur.jsp pour afficher les informations du collaborateur et les responsables.
        req.getRequestDispatcher("/WEB-INF/jsp/modifierCollaborateur.jsp").forward(req, resp);
    }
    //Ce code configure et transfère les informations nécessaires pour modifier les détails d'un collaborateur 
    //et afficher une liste de responsables d'activité dans une page JSP. Les étapes incluent la gestion des encodages, 
    //la récupération des données de la base de données, la transformation des données, la gestion des erreurs, 
    //et la préparation des données pour l'affichage.

    /**
     * Traite les requêtes POST pour soumettre les modifications du formulaire
     * de modification du collaborateur. Valide les données du formulaire, gère
     * les erreurs éventuelles et met à jour les informations du collaborateur
     * en base de données.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        ModifierCollaborateurFormChecker nv = new ModifierCollaborateurFormChecker(req);
        Collaborateur collaborateur = nv.checkForm();

        ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        Collection<ResponsableActivite> responsableActiviteList = responsableActiviteDao.list();
        Collection<ResponsableActivite> listResponsableActiviteCollaborateur = responsableActiviteDao.listResponsableActivite(collaborateur.getId());

        List<Integer> selectedResponsables = new ArrayList<>();
        for (ResponsableActivite responsable : listResponsableActiviteCollaborateur) {
            selectedResponsables.add(responsable.getId());
        }

        boolean isOuiSelected = "oui".equals(collaborateur.getRqth());
        boolean isAVieSelected = "a vie".equals(collaborateur.getRqth());
        if (isOuiSelected && collaborateur.getDate_de_renouvellement() == null) {
            nv.addError("date_de_renouvellement", "Date de renouvellement invalide.");
        }

        if (nv.getErrors().isEmpty()) {
            CollaborateurDao collaborateurDao = new CollaborateurDao();
            PossederDao possederDao = new PossederDao();
            try {
                if (collaborateurDao.existsForOtherCollab(collaborateur.getMatricule(), collaborateur.getId())) {
                    nv.addError("matricule", "Le matricule existe déjà.");
                    req.setAttribute("collaborateur", collaborateur);
                    req.setAttribute("responsableActiviteList", responsableActiviteList);
                    req.setAttribute("selectedResponsables", selectedResponsables);
                    req.setAttribute("isOuiSelected", isOuiSelected);
                    req.setAttribute("isAVieSelected", isAVieSelected);
                    if (collaborateur.getDate_de_renouvellement() != null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String formattedDate = collaborateur.getDate_de_renouvellement().format(formatter);
                        req.setAttribute("formattedDate", formattedDate);
                    }
                    req.setAttribute("errors", nv.getErrors());
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/modifierCollaborateur.jsp").forward(req, resp);
                    return;
                }

                String[] responsableActiviteIds = req.getParameterValues("responsable");
                List<Integer> responsableActiviteIdList = new ArrayList<>();
                if (responsableActiviteIds != null) {
                    for (String idStr : responsableActiviteIds) {
                        int id = Integer.parseInt(idStr);
                        responsableActiviteIdList.add(id);
                    }
                }

                collaborateur.setResponsableIds(responsableActiviteIdList);
                collaborateurDao.update(collaborateur);

                req.setAttribute("collaborateur", collaborateur);
                req.setAttribute("isOuiSelected", isOuiSelected);
                req.setAttribute("isAVieSelected", isAVieSelected);
                if (collaborateur.getDate_de_renouvellement() != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String formattedDate = collaborateur.getDate_de_renouvellement().format(formatter);
                    req.setAttribute("formattedDate", formattedDate);
                }

                List<String> responsableNoms = new ArrayList<>();
                for (int responsableId : responsableActiviteIdList) {
                    ResponsableActivite responsable = responsableActiviteDao.read(responsableId);
                    if (responsable != null) {
                        responsableNoms.add(responsable.getNom());
                    }
                }

                String responsablesActivite = String.join(", ", responsableNoms);

                Collection<Prestation> prestations = collaborateurDao.listPrestationCollaborateur(collaborateur.getId());
                List<String> responsableNomsPrestation = new ArrayList<>();
                List<Integer> respIdAdd = new ArrayList<>();
                for (Prestation presta : prestations) {
                    Integer raPrestation = presta.getId_ra();
                    boolean raExists = respIdAdd.stream().anyMatch(p -> p == raPrestation);
                    if (!raExists) {
                        String nomRaPrestation = responsableActiviteDao.read(raPrestation).getNom();
                        responsableNomsPrestation.add(nomRaPrestation);
                        respIdAdd.add(raPrestation);
                    }
                }

                String responsablesActivitePrestations = String.join(", ", responsableNomsPrestation);
                req.setAttribute("responsablesActivitePrestation", responsablesActivitePrestations);

                req.setAttribute("responsablesActivite", responsablesActivite);
                req.setAttribute("message", "Votre collaborateur a bien été modifié");
                req.getRequestDispatcher("/WEB-INF/jsp/afficherCollaborateur.jsp").forward(req, resp);

            } catch (SQLException ex) {
                Logger.getLogger(ModifierCollaborateur.class.getName()).log(Level.SEVERE, null, ex);
                req.setAttribute("errorMsg", "Erreur lors de la modification du collaborateur");
                req.getRequestDispatcher("/WEB-INF/jsp/modifierCollaborateur.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
            req.setAttribute("selectedResponsables", selectedResponsables);
            req.setAttribute("responsableActiviteList", responsableActiviteList);
            req.setAttribute("isOuiSelected", isOuiSelected);
            req.setAttribute("isAVieSelected", isAVieSelected);
            req.setAttribute("errors", nv.getErrors());
            req.setAttribute("collaborateur", collaborateur);
            req.getRequestDispatcher("/WEB-INF/jsp/modifierCollaborateur.jsp").forward(req, resp);
        }
    }
}
