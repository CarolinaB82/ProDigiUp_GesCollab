/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.ResponsableActiviteDao;
import entities.ResponsableActivite;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asolanas
 */
@WebServlet("/rechercher_ra")
public class RechercherResponsableActivite extends HttpServlet {

    private ResponsableActiviteDao responsableActiviteDao;

    @Override
    public void init() throws ServletException {
        this.responsableActiviteDao = new ResponsableActiviteDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String recherche = req.getParameter("recherche");
        String type = req.getParameter("type");

        System.out.println("Recherche: " + recherche + ", Type: " + type);

        if (recherche != null && type != null) {
            try {
                List<ResponsableActivite> suggestions;
                switch (type) {
                    case "matricule":
                        suggestions = responsableActiviteDao.rechercherRaParMatricule(recherche);
                        break;
                    case "nom":
                        suggestions = responsableActiviteDao.rechercherRaParNom(recherche);
                        break;
                    case "prenom":
                        suggestions = responsableActiviteDao.rechercherRaParPrenom(recherche);
                        break;

                    default:
                        suggestions = new ArrayList<>();
                }
                System.out.println("Suggestions: " + suggestions);
                resp.setContentType("text/html;charset=UTF-8");
                PrintWriter out = resp.getWriter();
                out.println("<table class='custom-table'>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>🔗Matricule</th>");
                out.println("<th>🔗Nom</th>");
                out.println("<th>Prénom</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                for (ResponsableActivite ra : suggestions) {
                    String url = req.getContextPath() + "/afficher_ra?id=" + ra.getId();
                    System.out.println("URL générée : " + url);
                    out.println("<tr>");
                    out.println("<td><a href=\"" + url + "\">" + ra.getMatricule() + "</a></td>");
                    out.println("<td><a href=\"" + url + "\">" + ra.getNom() + "</a></td>");
                    out.println("<td>" + ra.getPrenom() + "</td>");
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
            try {
                List<ResponsableActivite> resultats = new ArrayList<>();
                if (recherche != null && !recherche.isEmpty()) {
                    resultats.addAll(responsableActiviteDao.rechercherRaParMatricule(recherche));
                    resultats.addAll(responsableActiviteDao.rechercherRaParNom(recherche));
                    resultats.addAll(responsableActiviteDao.rechercherRaParPrenom(recherche));

                }
                req.setAttribute("resultats", resultats);
                req.setAttribute("currentPage", "rechercher_ra");
                req.getRequestDispatcher("/WEB-INF/jsp/responsableActivite.jsp").forward(req, resp);
            } catch (SQLException e) {
                System.out.println("Paramètres de recherche manquants ou invalides");
                throw new ServletException("Erreur lors de la recherche", e);
            }
        }
    }
}
