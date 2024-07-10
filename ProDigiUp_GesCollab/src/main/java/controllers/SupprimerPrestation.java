/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.PrestationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author asolanas
 */
@WebServlet("/supprimerPrestation")
public class SupprimerPrestation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        String prestationIdParam = req.getParameter("id");
        if (prestationIdParam != null && !prestationIdParam.isEmpty()) {
            try {
                int prestationId = Integer.parseInt(prestationIdParam);

                PrestationDao prestationDao = new PrestationDao();
                prestationDao.delete(prestationId);

                resp.sendRedirect("liste_collaborateurs"); // Redirection vers la liste après suppression
                return;
            } catch (NumberFormatException e) {
                // Gérer l'erreur de conversion si nécessaire
                e.printStackTrace();
            }
        }

        // Redirection vers une page d'erreur ou une autre page appropriée en cas d'erreur
        resp.sendRedirect("WEB-INF/jsp/403.jsp"); // Exemple de redirection vers une page d'erreur
    }
}
