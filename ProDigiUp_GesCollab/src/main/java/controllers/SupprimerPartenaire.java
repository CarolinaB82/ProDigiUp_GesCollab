/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

/**
 *
 * @author cberge
 */


import dao.CollaborateurDao;
import dao.PartenaireDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/supprimer_partenaire")
@SuppressWarnings("serial")
public class SupprimerPartenaire extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        
         String partenaireIdParam = req.getParameter("id");
        if (partenaireIdParam != null && !partenaireIdParam.isEmpty()) {
            try {
                int partenaireId = Integer.parseInt(partenaireIdParam);

                PartenaireDao partenaireDao = new PartenaireDao();
                partenaireDao.delete(partenaireId);
              
                resp.sendRedirect("liste_collaborateurs?deleteSuccess=true"); // Redirection vers la liste après suppression
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

