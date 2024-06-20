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
@WebServlet("/fiche_collaborateur")
@SuppressWarnings("serial")
public class AfficherCollaborateur extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
         int collaborateurId = 1;
        CollaborateurDao collaborateurDao = new CollaborateurDao();
        // Lire les informations du collaborateur depuis la base de données
        Collaborateur collaborateur = collaborateurDao.read(collaborateurId);

        // Transmettre les informations du collaborateur à la page JSP
        req.setAttribute("collaborateur", collaborateur);
        req.getRequestDispatcher("/WEB-INF/jsp/afficherCollaborateur.jsp").forward(req, resp);
    }
    
    
    
}
