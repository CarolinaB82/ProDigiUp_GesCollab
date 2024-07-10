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
import entities.CollaborateurPrestationPartenaireRa;
import entities.Partenaire;
import entities.Prestation;
import entities.ResponsableActivite;
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
 *
 * @author cberge
 */
@WebServlet("/prestation")
@SuppressWarnings("serial")

public class AfficherPrestation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        String prestationIdParam = req.getParameter("id");
        int prestationId = 1;

        if (prestationIdParam != null && !prestationIdParam.isEmpty()) {
            try {
                prestationId = Integer.parseInt(prestationIdParam);
            } catch (NumberFormatException e) {
                // Gérer l'erreur de conversion si nécessaire
            }
        }

        PrestationDao prestationDao = DaoFactory.getPrestationDao();
        Prestation prestation = prestationDao.read(prestationId);

        if (prestation == null) {
            resp.sendRedirect(req.getContextPath() + "/404.jsp");
            return;
        }

         Collection<ResponsableActivite> listResponsableActivitePrestation = prestationDao.listPrestationResponsableActivite(prestation.getId());
        List<String> responsableNoms = new ArrayList<>();
        for (ResponsableActivite responsable : listResponsableActivitePrestation) {
            responsableNoms.add(responsable.getNom());
        }
         System.out.println("Responsables d'activité : " + responsableNoms);

        Collection<Collaborateur> listPrestationCollaborateur = prestationDao.listPrestationCollaborateur(prestation.getId());
        List<String> collaborateurNoms = new ArrayList<>();
        for (Collaborateur collaborateur : listPrestationCollaborateur) {
            collaborateurNoms.add(collaborateur.getNom());
        }

        Collection<Partenaire> listPrestationPartenaire = prestationDao.listPrestationPartenaire(prestation.getId());
        List<String> partenaireNoms = new ArrayList<>();
        for (Partenaire partenaire : listPrestationPartenaire) {
            partenaireNoms.add(partenaire.getNom());
        }

        String responsablesActivite = String.join(", ", responsableNoms);
        String collaborateurs = String.join(", ", collaborateurNoms);
        String partenaires = String.join(", ", partenaireNoms);

        req.setAttribute("prestation", prestation);
        req.setAttribute("responsablesActivite", responsablesActivite);
        req.setAttribute("collaborateurs", collaborateurs);
        req.setAttribute("partenaires", partenaires);
        req.getRequestDispatcher("/WEB-INF/jsp/afficherPrestation.jsp").forward(req, resp);
    }
}

