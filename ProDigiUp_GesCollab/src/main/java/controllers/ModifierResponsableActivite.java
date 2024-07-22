/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import dao.PartenaireDao;
import dao.PossederDao;
import dao.ProposerDao;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.Partenaire;
import entities.Prestation;
import entities.ResponsableActivite;
import forms.ModifierResponsableActiviteFormChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * servlet nommée ModifierResponsableActivite qui gère les requêtes HTTP GET et
 * POST / contrôleur dans l'architecture MVC Affichage des détails d'un ra pour
 * modification Validation des données soumises via un formulaire Gestion des
 * erreurs de validation Mise à jour des données dans la base de données
 * Préparation des données pour l'affichage après la modification Interaction
 * avec la couche de présentation
 *
 * @author cberge
 */
@WebServlet("/modifier_ra")
@SuppressWarnings("serial")

public class ModifierResponsableActivite extends HttpServlet {

    /**
     * Traite les requêtes GET pour afficher le formulaire de modification du
     * responsable d'activité. Charge les informations du responsable d'activité
     * à partir de l'identifiant fourni dans les paramètres de la requête.
     * Charge également la liste des partenaires et collaborateurs associés à ce
     * responsable d'activité depuis la base de données.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        int responsableActiviteId = Integer.parseInt(req.getParameter("id"));

        ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        ResponsableActivite ra = responsableActiviteDao.read(responsableActiviteId);

        // Récupération des partenaires
        PartenaireDao partenaireDao = new PartenaireDao();
        Collection<Partenaire> partenaireList = partenaireDao.list();
        Collection<Partenaire> listPartenaireRa = partenaireDao.listPartenaire(ra.getId());

        List<Integer> selectedPartenaires = new ArrayList<>();
        for (Partenaire partenaire : listPartenaireRa) {
            selectedPartenaires.add(partenaire.getId());
        }

        if (ra == null) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
            return;
        }

        CollaborateurDao collaborateurDao = new CollaborateurDao();
        Collection<Collaborateur> collaborateurList = collaborateurDao.list();
        Collection<Collaborateur> listCollaborateurRa = collaborateurDao.listCollaborateur(ra.getId());

        List<Integer> selectedCollaborateurs = new ArrayList<>();
        for (Collaborateur collaborateur : listCollaborateurRa) {
            selectedCollaborateurs.add(collaborateur.getId());
        }

        if (ra == null) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
            return;
        }

        req.setAttribute("ra", ra);
        req.setAttribute("selectedPartenaires", selectedPartenaires);
        req.setAttribute("partenaireList", partenaireList);
        req.setAttribute("selectedCollaborateurs", selectedCollaborateurs);
        req.setAttribute("collaborateurList", collaborateurList);

        //Transfère la requête et la réponse à la page JSP modifierCollaborateur.jsp pour afficher les informations du collaborateur et les responsables.
        req.getRequestDispatcher("/WEB-INF/jsp/modifierResponsableActivite.jsp").forward(req, resp);
    }

    /**
     * Traite les requêtes POST pour soumettre les modifications du formulaire
     * de modification du responsable d'activité. Valide les données du
     * formulaire, gère les erreurs éventuelles et met à jour les informations
     * du responsable d'activité en base de données.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        ModifierResponsableActiviteFormChecker nv = new ModifierResponsableActiviteFormChecker(req);
        ResponsableActivite ra = nv.checkForm();

        PartenaireDao partenaireDao = new PartenaireDao();
        Collection<Partenaire> partenaireList = partenaireDao.list();
        Collection<Partenaire> listPartenaireRa = partenaireDao.listPartenaire(ra.getId());

        List<Integer> selectedPartenaires = new ArrayList<>();
        for (Partenaire partenaire : listPartenaireRa) {
            selectedPartenaires.add(partenaire.getId());
        }

        CollaborateurDao collaborateurDao = new CollaborateurDao();
        Collection<Collaborateur> collaborateurList = collaborateurDao.list();
        Collection<Collaborateur> listCollaborateurRa = collaborateurDao.listCollaborateur(ra.getId());

        List<Integer> selectedCollaborateurs = new ArrayList<>();
        for (Collaborateur collab : listCollaborateurRa) {
            selectedCollaborateurs.add(collab.getId());
        }

        if (nv.getErrors().isEmpty()) {

            ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
            ProposerDao proposerDao = new ProposerDao();
            PossederDao possederDao = new PossederDao();

            // Récupération des valeurs des checkboxes
            String[] partenaireIds = req.getParameterValues("partenaire");
            List<Integer> partenaireIdList = new ArrayList<>();

            if (partenaireIds != null) {
                for (String idStr : partenaireIds) {
                    int id = Integer.parseInt(idStr);
                    partenaireIdList.add(id);
                }
            }

            ra.setPartenaireIds(partenaireIdList);

            List<String> partenairesNoms = new ArrayList<>();
            for (int partenaireId : partenaireIdList) {
                Partenaire partenaire = partenaireDao.read(partenaireId);
                if (partenaire != null) {
                    partenairesNoms.add(partenaire.getNom());
                }
            }

            String[] collaborateurIds = req.getParameterValues("collaborateur");
            List<Integer> collaborateurIdList = new ArrayList<>();

            if (collaborateurIds != null) {
                for (String idStr : collaborateurIds) {
                    int id = Integer.parseInt(idStr);
                    collaborateurIdList.add(id);
                }
            }

            ra.setCollaborateurIds(collaborateurIdList);

            List<String> collaborateurNoms = new ArrayList<>();
            for (int collaborateurId : collaborateurIdList) {
                Collaborateur collaborateur = collaborateurDao.read(collaborateurId);
                if (collaborateur != null) {
                    collaborateurNoms.add(collaborateur.getNom());
                }
            }

            responsableActiviteDao.update(ra);

            Collection<Prestation> prestations = responsableActiviteDao.listPrestationResponsableActivite(ra.getId());
            List<String> partenaireNomsPrestation = new ArrayList<>();
            List<Integer> partenaireIdAdd = new ArrayList<>();
            List<String> collaborateurNomsPrestation = new ArrayList<>();
            List<Integer> collaborateurIdAdd = new ArrayList<>();
            for (Prestation presta : prestations) {
                Integer partPrestation = presta.getId_partenaire();
                Integer collabPrestation = presta.getId_collaborateur();

                boolean partExists = partenaireIdAdd.stream().anyMatch(p -> p == partPrestation);
                boolean collabExists = collaborateurIdAdd.stream().anyMatch(p -> p == collabPrestation);

                if (!partExists) {
                    String nomPartPrestation = partenaireDao.read(partPrestation).getNom();
                    partenaireNomsPrestation.add(nomPartPrestation);
                    partenaireIdAdd.add(partPrestation);
                }

                if (!collabExists) {
                    String nomCollabPrestation = collaborateurDao.read(collabPrestation).getNom();
                    collaborateurNomsPrestation.add(nomCollabPrestation);
                    collaborateurIdAdd.add(collabPrestation);
                }
            }

            String partenairesPrestation = String.join(", ", partenaireNomsPrestation);
            String collaborateursPrestation = String.join(", ", collaborateurNomsPrestation);

            req.setAttribute("partenairesPrestation", partenairesPrestation);
            req.setAttribute("collaborateursPrestation", collaborateursPrestation);

            req.setAttribute("ra", ra);

            String partenaire = String.join(", ", partenairesNoms);
            req.setAttribute("partenaire", partenaire);
            req.setAttribute("message", "Votre partenaire est bien modifié");

            String collaborateur = String.join(", ", collaborateurNoms);
            req.setAttribute("collaborateur", collaborateur);
            req.setAttribute("message", "Votre collaborateur est bien modifié");

            req.getRequestDispatcher("/WEB-INF/jsp/afficherResponsableActivite.jsp").forward(req, resp);

        } else {

            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
            req.setAttribute("selectedPartenaires", selectedPartenaires);
            req.setAttribute("partenaireList", partenaireList);
            req.setAttribute("selectedCollaborateurs", selectedCollaborateurs);
            req.setAttribute("collaborateurList", collaborateurList);
            req.setAttribute("errors", nv.getErrors());

            req.setAttribute("ra", ra);
            req.getRequestDispatcher("/WEB-INF/jsp/modifierResponsableActivite.jsp").forward(req, resp);
        }
    }
}