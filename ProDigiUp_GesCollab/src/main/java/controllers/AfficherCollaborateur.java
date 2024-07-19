/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.Prestation;
import entities.ResponsableActivite;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * servlet nommée AfficherCollaborateur qui gère les requêtes HTTP GET /
 * contrôleur dans l'architecture MVC Afficher les détails d'un collaborateur
 * Interaction avec la base de données Utilisation de JSP pour la vue
 *
 * @author asolanas
 */
@WebServlet("/collaborateur")
@SuppressWarnings("serial")
public class AfficherCollaborateur extends HttpServlet {

    /**
     * Gère les requêtes GET pour afficher les détails d'un collaborateur.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
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
        for (ResponsableActivite responsable : listResponsableActiviteCollaborateur) {
            responsableNoms.add(responsable.getNom());
        }

        String responsablesActivite = String.join(", ", responsableNoms);
        
        Collection<Prestation> prestations = collaborateurDao.listPrestationCollaborateur(collaborateurId);
        List<String> responsableNomsPrestation = new ArrayList<>();
        List<Integer> respIdAdd = new ArrayList<>();
        for(Prestation presta : prestations){
            Integer raPrestation = presta.getId_ra();
            boolean raExists = respIdAdd.stream()
                                   .anyMatch(p -> p == raPrestation);
            if(!raExists){
                String nomRaPrestation = responsableActiviteDao.read(raPrestation).getNom();
                responsableNomsPrestation.add(nomRaPrestation);
                respIdAdd.add(raPrestation);
            }
            
        }
        
        String responsablesActivitePrestations = String.join(", ", responsableNomsPrestation);

        req.setAttribute("responsablesActivite", responsablesActivite);
        req.setAttribute("responsablesActivitePrestation", responsablesActivitePrestations);
        req.setAttribute("collaborateur", collaborateur);
        req.getRequestDispatcher("/WEB-INF/jsp/afficherCollaborateur.jsp").forward(req, resp);
    }
    // modifier_collaborateur.jsp

}
