/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import entities.Collaborateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author asolanas
 */
@WebServlet("/collaborateur")
@SuppressWarnings("serial")
public class AfficherCollaborateur extends HttpServlet {

   /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
         int collaborateurId = 1;
        CollaborateurDao collaborateurDao = new CollaborateurDao();
        // Lire les informations du collaborateur depuis la base de données
        Collaborateur collaborateur = collaborateurDao.read(collaborateurId);

        // Transmettre les informations du collaborateur à la page JSP
        req.setAttribute("collaborateur", collaborateur);
        req.getRequestDispatcher("/WEB-INF/jsp/afficherCollaborateur.jsp").forward(req, resp);
    }*/
    
    @Override
protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
     // Récupérer l'identifiant du collaborateur depuis la requête
        String collaborateurIdParam = req.getParameter("id");
        int collaborateurId = 1; // Valeur par défaut ou celle que vous choisissez si aucun paramètre n'est fourni
        
        if (collaborateurIdParam != null && !collaborateurIdParam.isEmpty()) {
            try {
                collaborateurId = Integer.parseInt(collaborateurIdParam);
            } catch (NumberFormatException e) {
                // Gérer l'erreur de conversion si nécessaire
            }
        }
        
        CollaborateurDao collaborateurDao = new CollaborateurDao ();
        // Lire les informations du collaborateur depuis la base de données
        Collaborateur collaborateur = collaborateurDao.read(collaborateurId);

        // Transmettre les informations du collaborateur à la page JSP
        req.setAttribute("collaborateur", collaborateur);
        req.getRequestDispatcher("/WEB-INF/jsp/afficherCollaborateur.jsp").forward(req, resp);

        
    }

    
}
