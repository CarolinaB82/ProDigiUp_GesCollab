package controllers;

import dao.CollaborateurDao;
import entities.Collaborateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Servlet implementation class FicheCollaborateur Gère les requêtes GET pour
 * afficher la fiche d'un collaborateur spécifié par son identifiant. Charge les
 * informations du collaborateur depuis la base de données et les transmet à la
 * page JSP.
 *
 *
 * @author cberge
 */
@WebServlet("/fiche_collaborateur")
@SuppressWarnings("serial")
public class FicheCollaborateur extends HttpServlet {

    /**
     * Traite les requêtes GET en récupérant et affichant les informations du
     * collaborateur spécifié. Si aucun identifiant n'est fourni en paramètre,
     * utilise un identifiant par défaut (ici : 1).
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupérer l'identifiant du collaborateur depuis la requête
        String collaborateurIdParam = req.getParameter("id");
        int collaborateurId = 1; // Valeur par défaut ou celle que vous choisissez si aucun paramètre n'est fourni

        if (collaborateurIdParam != null && !collaborateurIdParam.isEmpty()) {
            try {
                collaborateurId = Integer.parseInt(collaborateurIdParam);
            } catch (NumberFormatException e) {
                // Gérer l'erreur de conversion si nécessaire
            }
        }

        CollaborateurDao collaborateurDao = new CollaborateurDao();
        // Lire les informations du collaborateur depuis la base de données
        Collaborateur collaborateur = collaborateurDao.read(collaborateurId);
        //CollaborateurNomPrestation collaborateur = collaborateurDao.getCollaborateurNomPrestation(collaborateurId);

        // Formatage de la date
        if (collaborateur.getDate_de_renouvellement() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = collaborateur.getDate_de_renouvellement().format(formatter);
            req.setAttribute("formattedDate", formattedDate);
        }

        // Formatage de la date
        if (collaborateur.getDate_de_renouvellement() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = collaborateur.getDate_de_renouvellement().format(formatter);
            req.setAttribute("formattedDate", formattedDate);
        }

        // Transmettre les informations du collaborateur à la page JSP
        req.setAttribute("collaborateur", collaborateur);

        req.getRequestDispatcher("/WEB-INF/jsp/collaborateur.jsp").forward(req, resp);
    }

}
