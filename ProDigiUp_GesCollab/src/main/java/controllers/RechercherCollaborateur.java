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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asolanas
 */
@WebServlet("/rechercher")
@SuppressWarnings("serial")
public class RechercherCollaborateur extends HttpServlet {

    private CollaborateurDao collaborateurDao;

    @Override
    public void init() throws ServletException {
        // Initialisez votre DAO ici (par exemple en utilisant une connexion à la base de données)
        this.collaborateurDao = new CollaborateurDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recherche = req.getParameter("recherche");
        String type = req.getParameter("type");

        if (recherche != null && type != null) {
            // Gérer la requête AJAX pour les suggestions
            try {
                List<Collaborateur> suggestions;
                switch (type) {
                    case "nom":
                        suggestions = collaborateurDao.rechercherParNom(recherche);
                        break;
                    case "prenom":
                        suggestions = collaborateurDao.rechercherParPrenom(recherche);
                        break;
                    case "matricule":
                        suggestions = collaborateurDao.rechercherParMatricule(recherche);
                        break;
                    default:
                        suggestions = new ArrayList<>();
                }

                resp.setContentType("text/html;charset=UTF-8");
                PrintWriter out = resp.getWriter();
                out.println("<table class='custom-table'>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Matricule</th>");
                out.println("<th>Nom</th>");
                out.println("<th>Prénom</th>");
                out.println("<th>Métier</th>");
                out.println("<th>Statut</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                for (Collaborateur collaborateur : suggestions) {
                    out.println("<tr>");
                    out.println("<td><a href=\"" + req.getContextPath() + "/collaborateur?id=" + collaborateur.getId() + "\">" + collaborateur.getMatricule() + "</a></td>");
                    out.println("<td><a href=\"" + req.getContextPath() + "/collaborateur?id=" + collaborateur.getId() + "\">" + collaborateur.getNom() + "</a></td>");
                    out.println("<td>" + collaborateur.getPrenom() + "</td>");
                    out.println("<td>" + collaborateur.getMetier() + "</td>");
                    out.println("<td>" + collaborateur.getStatut() + "</td>");
                    out.println("</tr>");
                }
            out.println("</tbody>");
                out.println("</table>");
                out.close();
                return;
            } catch (SQLException e) {
                throw new ServletException("Erreur lors de la recherche", e);
            }
        } else {
            // Gérer la requête de recherche standard et rediriger vers une page JSP avec les résultats
            try {
                List<Collaborateur> resultats = new ArrayList<>();
                if (recherche != null && !recherche.isEmpty()) {
                    resultats.addAll(collaborateurDao.rechercherParNom(recherche));
                    resultats.addAll(collaborateurDao.rechercherParPrenom(recherche));
                    resultats.addAll(collaborateurDao.rechercherParMatricule(recherche));
                }

                req.setAttribute("resultats", resultats);
                req.setAttribute("currentPage", "rechercher");
                req.getRequestDispatcher("/WEB-INF/jsp/collaborateur.jsp").forward(req, resp);
            } catch (SQLException e) {
                throw new ServletException("Erreur lors de la recherche", e);
            }
        }
    }
}
