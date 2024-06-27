package controllers;

import dao.ResponsableActiviteDao;
import entities.ResponsableActivite;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        //req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        // Récupérer l'identifiant du responsable activite depuis la requête
        String raIdParam = req.getParameter("id");
        int raId = 1; // Valeur par défaut ou celle que vous choisissez si aucun paramètre n'est fourni

        if (raIdParam != null && !raIdParam.isEmpty()) {
            try {
                raId = Integer.parseInt(raIdParam);
            } catch (NumberFormatException e) {
                // Gérer l'erreur de conversion si nécessaire
            }
        }

        ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        // Lire les informations du responsable activite depuis la base de données
        ResponsableActivite ra = responsableActiviteDao.read(raId);

        // Transmettre les informations du responsable activite à la page JSP
        req.setAttribute("ra", ra);
        req.getRequestDispatcher("/WEB-INF/jsp/afficherResponsableActivite.jsp").forward(req, resp);

    }

}
