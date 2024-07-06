package controllers;

import dao.CollaborateurDao;
import dao.DaoFactory;
import dao.PartenaireDao;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.CollaborateurPrestationPartenaireRa;
import entities.Partenaire;
import entities.PrestationRaPartenaire;
import entities.ResponsableActivite;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

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

        
//        // Vérifier si le responsable d'activité est trouvé
//        if (ra == null) {
//            
//            resp.sendRedirect(req.getContextPath() + "/error.jsp");
//            return;
//        }
        // Ajouter le ResponsableActivite et la liste aux attributs de la requête
        req.setAttribute("ra", ra);
        
        PartenaireDao partenaireDao = new PartenaireDao();
        Collection<Partenaire> listPartenaireRa = partenaireDao.listPartenaire(ra.getId());
        List<String> partenaireNoms = new ArrayList<>();
        for(Partenaire partenaire : listPartenaireRa){
            partenaireNoms.add(partenaire.getNom());
        }
        
        String partenaire = String.join(", ", partenaireNoms);

        req.setAttribute("partenaire", partenaire);
        
         CollaborateurDao collaborateurDao = new CollaborateurDao();
        Collection<Collaborateur> listCollaborateurRa = collaborateurDao.listCollaborateur(ra.getId());
        List<String> collaborateurNoms = new ArrayList<>();
        for(Collaborateur collaborateur : listCollaborateurRa){
            collaborateurNoms.add(collaborateur.getNom());
        }
        
        String collaborateur = String.join(", ", collaborateurNoms);

        req.setAttribute("collaborateur", collaborateur);
        

        // Rediriger vers la JSP appropriée pour l'affichage
        req.getRequestDispatcher("/WEB-INF/jsp/afficherResponsableActivite.jsp").forward(req, resp);

    }
}
