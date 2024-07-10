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
import java.util.Collection;

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
        int prestationId = 1; // Valeur par défaut ou celle que vous choisissez si aucun paramètre n'est fourni

        if (prestationIdParam != null && !prestationIdParam.isEmpty()) {
            try {
                prestationId = Integer.parseInt(prestationIdParam);
            } catch (NumberFormatException e) {
                // Gérer l'erreur de conversion si nécessaire
            }
        }

        PrestationDao prestationDao = new PrestationDao();
        // Lire les informations du collaborateur depuis la base de données
        Prestation prestation = prestationDao.read(prestationId);

        req.setAttribute("prestation", prestation);
        CollaborateurDao collaborateurDao = new CollaborateurDao();
        Collaborateur collabPresta = collaborateurDao.read(prestation.getId_collaborateur());
        req.setAttribute("collaborateur", collabPresta.getNom());

        ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        ResponsableActivite responsable = responsableActiviteDao.read(prestation.getId_ra());
        req.setAttribute("responsablesActivite", responsable.getNom());

        PartenaireDao partenaireDao = new PartenaireDao();
        Partenaire part = partenaireDao.read(prestation.getId_partenaire());
        req.setAttribute("partenaire", part.getNom());

        req.getRequestDispatcher("/WEB-INF/jsp/afficherPrestation.jsp").forward(req, resp);

    }
}
