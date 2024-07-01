/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import dao.PrestationDao;
import entities.Collaborateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author asolanas
 */
@WebServlet("/collaborateur")
@SuppressWarnings("serial")
public class AfficherCollaborateur extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        
        String collaborateurIdParam = req.getParameter("id");
        int collaborateurId = 1;

        if (collaborateurIdParam != null && !collaborateurIdParam.isEmpty()) {
            try {
                collaborateurId = Integer.parseInt(collaborateurIdParam);
            } catch (NumberFormatException e) {
                // Gérer l'erreur de conversion si nécessaire
            }
        }

        CollaborateurDao collaborateurDao = new CollaborateurDao();
        Collaborateur collaborateur = collaborateurDao.read(collaborateurId);

        // Formatage de la date
        if (collaborateur.getDate_de_renouvellement() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = collaborateur.getDate_de_renouvellement().format(formatter);
            req.setAttribute("formattedDate", formattedDate);
        }

        req.setAttribute("collaborateur", collaborateur);
        req.getRequestDispatcher("/WEB-INF/jsp/afficherCollaborateur.jsp").forward(req, resp);
    }
}
