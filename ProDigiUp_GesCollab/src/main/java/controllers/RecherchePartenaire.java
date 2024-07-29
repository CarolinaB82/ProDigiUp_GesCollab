/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.PartenaireDao;
import entities.Partenaire;
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
 * @author cberge
 */
@WebServlet("/rechercher_partenaire")
@SuppressWarnings("serial")
public class RecherchePartenaire extends HttpServlet {

    private PartenaireDao partenaireDao;

    @Override
    public void init() throws ServletException {
        this.partenaireDao = new PartenaireDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String recherche = req.getParameter("recherche");
        String type = req.getParameter("type");

        boolean rechercheEffectuee = (recherche != null && !recherche.isEmpty() && type != null);

        if (rechercheEffectuee) {
            try {
                List<Partenaire> suggestions;
                switch (type) {
                    case "nom":
                        suggestions = partenaireDao.rechercherParNom(recherche);
                        break;
                    case "ville":
                        suggestions = partenaireDao.rechercherParVille(recherche);
                        break;
                    default:
                        suggestions = new ArrayList<>();
                }

                resp.setContentType("text/html;charset=UTF-8");
                PrintWriter out = resp.getWriter();
                out.println("<table class='custom-table'>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>🔗Nom</th>");
                out.println("<th>🔗Ville</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                for (Partenaire part : suggestions) {
                    String url = req.getContextPath() + "/partenaire?id=" + part.getId();
                    out.println("<tr>");
                    out.println("<td><a href=\"" + url + "\">" + part.getNom() + "</a></td>");
                    out.println("<td><a href=\"" + url + "\">" + part.getVille() + "</a></td>");
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
                List<Partenaire> resultats = new ArrayList<>();
                if (recherche != null && !recherche.isEmpty()) {
                    resultats.addAll(partenaireDao.rechercherParNom(recherche));
                    resultats.addAll(partenaireDao.rechercherParVille(recherche));
                }
                req.setAttribute("resultats", resultats);
                req.setAttribute("rechercheEffectuee", rechercheEffectuee);
                req.setAttribute("recherche", recherche);
                req.setAttribute("currentPage", "rechercher_partenaire");
                req.getRequestDispatcher("/WEB-INF/jsp/partenaire.jsp").forward(req, resp);
            } catch (SQLException e) {
                throw new ServletException("Erreur lors de la recherche", e);
            }
        }
    }
}

