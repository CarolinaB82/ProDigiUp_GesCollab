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
import entities.ResponsableActivite;
import forms.ModifierResponsableActiviteFormChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cberge
 */
@WebServlet("/modifier_ra")
@SuppressWarnings("serial")

public class ModifierResponsableActivite extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        int responsableActiviteId = Integer.parseInt(req.getParameter("id"));

        ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        ResponsableActivite ra = responsableActiviteDao.read(responsableActiviteId);

        PartenaireDao partenaireDao = new PartenaireDao();
        CollaborateurDao collaborateurDao = new CollaborateurDao();
        ResponsableActiviteDao raDao = new ResponsableActiviteDao();

        Collection<Partenaire> partenaires = partenaireDao.list();
        Collection<Collaborateur> collaborateurs = collaborateurDao.list();
        Collection<ResponsableActivite> responsablesActivite = raDao.list();

        if (ra == null) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
            return;
        }

        req.setAttribute("ra", ra);
        req.setAttribute("partenaires", partenaires);
        req.setAttribute("collaborateurs", collaborateurs);
        req.setAttribute("responsablesActivite", responsablesActivite);
        req.getRequestDispatcher("/WEB-INF/jsp/modifierResponsableActivite.jsp").forward(req, resp);
    }

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

            // Récupération des partenaires sélectionnés
            String[] partenaireIds = req.getParameterValues("partenaire");
            List<Integer> partenaireIdList = new ArrayList<>();

            if (partenaireIds != null) {
                for (String idStr : partenaireIds) {
                    int id = Integer.parseInt(idStr);
                    partenaireIdList.add(id);
                }
            }

            ra.setPartenaireIds(partenaireIdList);
            responsableActiviteDao.update(ra);
            req.setAttribute("ra", ra);

            List<String> partenairesNoms = new ArrayList<>();
            for (int partenaireId : partenaireIdList) {
                Partenaire partenaire = partenaireDao.read(partenaireId);
                if (partenaire != null) {
                    partenairesNoms.add(partenaire.getNom());
                }
            }

            // Récupération des collaborateurs sélectionnés
            String[] collaborateurIds = req.getParameterValues("collaborateur");
            List<Integer> collaborateurIdList = new ArrayList<>();

            if (collaborateurIds != null) {
                for (String idStr : collaborateurIds) {
                    int id = Integer.parseInt(idStr);
                    collaborateurIdList.add(id);
                }
            }

            ra.setCollaborateurIds(collaborateurIdList);
            responsableActiviteDao.update(ra);
            req.setAttribute("ra", ra);

            List<String> collaborateurNoms = new ArrayList<>();
            for (int collaborateurId : collaborateurIdList) {
                Collaborateur collaborateur = collaborateurDao.read(collaborateurId);
                if (collaborateur != null) {
                    collaborateurNoms.add(collaborateur.getNom());
                }
            }

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
