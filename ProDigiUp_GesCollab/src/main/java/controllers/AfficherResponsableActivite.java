package controllers;

import dao.CollaborateurDao;
import dao.DaoFactory;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.CollaborateurPrestationPartenaireRa;
import entities.PrestationRaPartenaire;
import entities.ResponsableActivite;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author asolanas
 */
@WebServlet("/afficher_ra")
@SuppressWarnings("serial")
public class AfficherResponsableActivite extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        String responsableIdParam = req.getParameter("id");
        int raId = 1; // Valeur par défaut

        if (responsableIdParam != null && !responsableIdParam.isEmpty()) {
            try {
                raId = Integer.parseInt(responsableIdParam);
            } catch (NumberFormatException e) {
                // Gérer l'erreur de conversion si nécessaire
            }
        }
        // Récupérer le ResponsableActivite spécifique
        ResponsableActiviteDao raDao = DaoFactory.ResponsableActiviteDao();
        ResponsableActivite ra = raDao.read(raId);

        // Ajouter le ResponsableActivite et la liste aux attributs de la requête
        req.setAttribute("ra", ra);

        // Rediriger vers la JSP appropriée pour l'affichage
        req.getRequestDispatcher("/WEB-INF/jsp/afficherResponsableActivite.jsp").forward(req, resp);
    }
}
