/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

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
 * servlet nommée SupprimerPrestation qui gère les requêtes HTTP GET /
 * contrôleur dans l'architecture MVC Suppression d'une prestation Gestion des
 * exceptions de conversion d'ID Utilisation du DAO pour la suppression
 * Redirection après suppression Gestion des erreurs de paramètres
 *
 * @author asolanas
 */
@WebServlet("/supprimerPrestation")
public class SupprimerPrestation extends HttpServlet {

    /**
     * Traite les requêtes POST pour la suppression d'une prestation.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
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
