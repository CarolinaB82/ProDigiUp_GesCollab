/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.DaoFactory;
import entities.CollaborateurPrestationPartenaireRa;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

/**
 * servlet nommée ListeCollaborateurs qui gère les requêtes HTTP GET /
 * contrôleur dans l'architecture MVC Affichage de la liste des collaborateurs
 * Utilisation de JSP pour la vue
 *
 * @author cberge
 */
@WebServlet("/liste_collaborateurs")
@SuppressWarnings("serial")
public class ListeCollaborateurs extends HttpServlet {

    /**
     * Traite les requêtes GET en récupérant et affichant la liste des
     * collaborateurs avec leurs prestations, partenaires et responsables
     * d'activité associés.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        Collection<CollaborateurPrestationPartenaireRa> collaborateurs = DaoFactory.getCollaborateurDao().listCollaborateurPrestationPartenaireRa();

        req.setAttribute("collaborateurs", collaborateurs);

        req.getRequestDispatcher("/WEB-INF/jsp/listeCollaborateurs.jsp").forward(req, resp);
    }

}
