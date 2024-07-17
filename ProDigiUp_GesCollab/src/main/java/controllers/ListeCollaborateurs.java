/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import dao.DaoFactory;
import entities.Collaborateur;
import entities.CollaborateurPrestationPartenaireRa;
import entities.Prestation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author cberge
 */
@WebServlet("/liste_collaborateurs")
public class ListeCollaborateurs extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        String sort = req.getParameter("sort");
        String order = req.getParameter("order");

        Collection<CollaborateurPrestationPartenaireRa> collaborateurs = DaoFactory.getCollaborateurDao().listCollaborateurPrestationPartenaireRa();
        List<CollaborateurPrestationPartenaireRa> sortedCollaborateurs = new ArrayList<>(collaborateurs);

        Comparator<CollaborateurPrestationPartenaireRa> comparator = Comparator.comparing(collab -> collab.getCollaborateur().getMatricule()); // Default comparator

        if ("nom".equals(sort)) {
            comparator = Comparator.comparing(collab -> collab.getCollaborateur().getNom());
        } else if ("activite".equals(sort)) {
            comparator = Comparator.comparing(collab -> collab.isPrestationActive() ? 0 : 1);
        } else if ("prestation".equals(sort)) {
            comparator = Comparator.comparing(collab -> {
                if (collab.getPrestRaPart() != null && !collab.getPrestRaPart().isEmpty()) {
                    return collab.getPrestRaPart().iterator().next().getPrestation().getNom_presta();
                } else {
                    return "";
                }
            });
        } else if ("responsable".equals(sort)) {
            comparator = Comparator.comparing(collab -> {
                if (collab.getPrestRaPart() != null && !collab.getPrestRaPart().isEmpty()) {
                    return collab.getPrestRaPart().iterator().next().getResp().getNom();
                } else {
                    return "";
                }
            });
        } else if ("partenaire".equals(sort)) {
            comparator = Comparator.comparing(collab -> {
                if (collab.getPrestRaPart() != null && !collab.getPrestRaPart().isEmpty()) {
                    return collab.getPrestRaPart().iterator().next().getPart().getNom();
                } else {
                    return "";
                }
            });
        }

        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        sortedCollaborateurs = sortedCollaborateurs.stream().sorted(comparator).collect(Collectors.toList());

        req.setAttribute("collaborateurs", sortedCollaborateurs);
        req.getRequestDispatcher("/WEB-INF/jsp/listeCollaborateurs.jsp").forward(req, resp);
    }
}

