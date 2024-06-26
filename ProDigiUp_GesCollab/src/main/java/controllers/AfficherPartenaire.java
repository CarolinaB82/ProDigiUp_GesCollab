/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.DaoFactory;
import dao.PartenaireDao;
import entities.CollaborateurPrestationPartenaireRa;
import entities.Partenaire;
import entities.PrestationRaPartenaire;
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
 * @author asolanas
 */
@WebServlet("/partenaire")
@SuppressWarnings("serial")
public class AfficherPartenaire extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        String partenaireIdParam = req.getParameter("id");
        int partenaireId = 1;

        if (partenaireIdParam != null && !partenaireIdParam.isEmpty()) {
            try {
                partenaireId = Integer.parseInt(partenaireIdParam);
            } catch (NumberFormatException e) {
                // Gérer l'erreur de conversion si nécessaire
            }
        }

        PartenaireDao partenaireDao = DaoFactory.getPartenaireDao();
        Partenaire partenaire = partenaireDao.read(partenaireId);

        req.setAttribute("partenaire", partenaire);

        req.getRequestDispatcher("/WEB-INF/jsp/afficherPartenaire.jsp").forward(req, resp);
    }

}
