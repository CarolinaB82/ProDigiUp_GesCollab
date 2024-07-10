/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */



package controllers;

import dao.CollaborateurDao;
import dao.DaoFactory;
import dao.PartenaireDao;
import dao.PrestationDao;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.Partenaire;
import entities.Prestation;
import entities.ResponsableActivite;
import forms.ModifierPrestationFormChecker;
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
import java.util.Map;

/**
 *
 * @author asolanas
 */
@WebServlet("/modifierPrestation")
public class ModificateurPrestation extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        int prestationId = Integer.parseInt(req.getParameter("id"));

        PrestationDao prestationDao = new PrestationDao();
        Prestation prestation = prestationDao.read(prestationId);

        if (prestation == null) {
            resp.sendRedirect(req.getContextPath() + "/404.jsp");
            return;
        }

        PartenaireDao partenaireDao = new PartenaireDao();
        CollaborateurDao collaborateurDao = new CollaborateurDao();
        ResponsableActiviteDao raDao = new ResponsableActiviteDao();

        Collection<Partenaire> partenaires = partenaireDao.list();
        Collection<Collaborateur> collaborateurs = collaborateurDao.list();
        Collection<ResponsableActivite> responsablesActivite = raDao.list();

        req.setAttribute("prestation", prestation);
        req.setAttribute("partenaires", partenaires);
        req.setAttribute("collaborateurs", collaborateurs);
        req.setAttribute("responsablesActivite", responsablesActivite);
        req.getRequestDispatcher("/WEB-INF/jsp/modifierPrestation.jsp").forward(req, resp);
    }

    @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

    ModifierPrestationFormChecker nv = new ModifierPrestationFormChecker(req);
    Prestation prestation = nv.checkForm();

    if (prestation == null) {
        resp.sendRedirect(req.getContextPath() + "/404.jsp");
        return;
    }

    PrestationDao prestationDao = new PrestationDao();
    ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
    PartenaireDao partenaireDao = new PartenaireDao();
    CollaborateurDao collaborateurDao = new CollaborateurDao();

    if (nv.getErrors().isEmpty()) {
        prestationDao.update(prestation);

        // Relecture des données mises à jour
        prestation = prestationDao.read(prestation.getId());

////         Relecture des responsables, collaborateurs et partenaires associés
//        Collection<ResponsableActivite> listPrestationResponsableActivite = responsableActiviteDao.listResponsableActivite(prestation.getId());
//        List<String> responsableNoms = new ArrayList<>();
//        for (ResponsableActivite responsable : listPrestationResponsableActivite) {
//            responsableNoms.add(responsable.getNom());
//        }
//
//        Collection<Collaborateur> listPrestationCollaborateur = prestationDao.listPrestationCollaborateur(prestation.getId());
//        List<String> collaborateurNoms = new ArrayList<>();
//        for (Collaborateur collaborateur : listPrestationCollaborateur) {
//            collaborateurNoms.add(collaborateur.getNom());
//        }
//
//        Collection<Partenaire> listPrestationPartenaire = prestationDao.listPrestationPartenaire(prestation.getId());
//        List<String> partenaireNoms = new ArrayList<>();
//        for (Partenaire partenaire : listPrestationPartenaire) {
//            partenaireNoms.add(partenaire.getNom());
//        }
//
//        // Transmission des données mises à jour
//        String responsablesActivite = String.join(", ", responsableNoms);
//        String collaborateurs = String.join(", ", collaborateurNoms);
//        String partenaires = String.join(", ", partenaireNoms);



        Collaborateur collabPresta = collaborateurDao.read(prestation.getId_collaborateur());
        req.setAttribute("collaborateur", collabPresta.getNom());

        ResponsableActivite responsable = responsableActiviteDao.read(prestation.getId_ra());
        req.setAttribute("responsablesActivite", responsable.getNom());

        Partenaire part = partenaireDao.read(prestation.getId_partenaire());
        req.setAttribute("partenaire", part.getNom());
        req.setAttribute("prestation", prestation);
        
        
        req.setAttribute("message", "La prestation a été mise à jour avec succès.");
        req.getRequestDispatcher("/WEB-INF/jsp/afficherPrestation.jsp").forward(req, resp);
    } else {
        Collection<ResponsableActivite> responsableActiviteList = responsableActiviteDao.list();
        Collection<Partenaire> partenaireList = partenaireDao.list();
        Collection<Collaborateur> collaborateurList = collaborateurDao.list();
        req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
        req.setAttribute("selectedResponsables", responsableActiviteList);
        req.setAttribute("selectedPartenaires", partenaireList);
        req.setAttribute("selectedCollaborateurs", collaborateurList);
        req.setAttribute("responsableActiviteList", responsableActiviteList);
        req.setAttribute("partenaireList", partenaireList);
        req.setAttribute("collaborateurList", collaborateurList);
        req.setAttribute("errors", nv.getErrors());
        req.setAttribute("prestation", prestation);
        req.getRequestDispatcher("/WEB-INF/jsp/modifierPrestation.jsp").forward(req, resp);
    }
}

}


