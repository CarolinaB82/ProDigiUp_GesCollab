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
 * Description de la classe DesactiverCollaborateur
 *
 * @author cberge
 */
//@WebServlet("/desactiverCollaborateur")
//public class DesactiverCollaborateur extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
//        
//        String collaborateurIdParam = req.getParameter("id");
//        int collaborateurId = 0;
//
//        if (collaborateurIdParam != null && !collaborateurIdParam.isEmpty()) {
//            try {
//                collaborateurId = Integer.parseInt(collaborateurIdParam);
//            } catch (NumberFormatException e) {
//                // Gérer l'erreur de conversion si nécessaire
//                resp.sendRedirect(req.getContextPath() + "/afficherCollaborateur?id=" + collaborateurId);
//                return;
//            }
//        }
//
//        CollaborateurDao collaborateurDao = new CollaborateurDao();
//        collaborateurDao.desactiver(collaborateurId);
//
//        resp.sendRedirect(req.getContextPath() + "/afficherCollaborateur?id=" + collaborateurId);
//    }
//}
