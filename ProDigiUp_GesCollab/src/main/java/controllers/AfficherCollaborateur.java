/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import dao.PrestationDao;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.ResponsableActivite;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author asolanas
 */
@WebServlet("/collaborateur")
@SuppressWarnings("serial")
public class AfficherCollaborateur extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        
        String collaborateurIdParam = req.getParameter("id");
        int collaborateurId = 1;

        if (collaborateurIdParam != null && !collaborateurIdParam.isEmpty()) {
            try {
                collaborateurId = Integer.parseInt(collaborateurIdParam);
            } catch (NumberFormatException e) {
                // Gérer l'erreur de conversion si nécessaire
            }
        }

        CollaborateurDao collaborateurDao = new CollaborateurDao();
        Collaborateur collaborateur = collaborateurDao.read(collaborateurId);

        // Formatage de la date
        if (collaborateur.getDate_de_renouvellement() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = collaborateur.getDate_de_renouvellement().format(formatter);
            req.setAttribute("formattedDate", formattedDate);
        }
        
         ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        Collection<ResponsableActivite> listResponsableActiviteCollaborateur = responsableActiviteDao.listResponsableActivite(collaborateur.getId());
        List<String> responsableNoms = new ArrayList<>();
        for(ResponsableActivite responsable : listResponsableActiviteCollaborateur){
            responsableNoms.add(responsable.getNom());
        }
        
        String responsablesActivite = String.join(", ", responsableNoms);

        req.setAttribute("responsablesActivite", responsablesActivite);
        req.setAttribute("collaborateur", collaborateur);
        req.getRequestDispatcher("/WEB-INF/jsp/afficherCollaborateur.jsp").forward(req, resp);
    }
    // modifier_collaborateur.jsp

}