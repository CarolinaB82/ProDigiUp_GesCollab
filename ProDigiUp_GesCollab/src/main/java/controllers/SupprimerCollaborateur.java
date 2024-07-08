/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author cberge
 */
@WebServlet("/supprimer_collaborateur")
public class SupprimerCollaborateur extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        
         String collaborateurIdParam = req.getParameter("id");
        if (collaborateurIdParam != null && !collaborateurIdParam.isEmpty()) {
            try {
                int collaborateurId = Integer.parseInt(collaborateurIdParam);

                CollaborateurDao collaborateurDao = new CollaborateurDao();
                collaborateurDao.delete(collaborateurId);
                
                resp.sendRedirect("liste_collaborateurs"); // Redirection vers la liste après suppression
                return;
            } catch (NumberFormatException e) {
                // Gérer l'erreur de conversion si nécessaire
                e.printStackTrace();
            }
        }

        // Redirection vers une page d'erreur ou une autre page appropriée en cas d'erreur
        resp.sendRedirect("403.jsp"); // Exemple de redirection vers une page d'erreur
    }
}
