package controllers;

import dao.CollaborateurDao;
import dao.DaoFactory;
import dao.PartenaireDao;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.Partenaire;
import entities.Prestation;
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

/**
 * * servlet nommée AfficherRa qui gère les requêtes HTTP GET / contrôleur dans
 * l'architecture MVC Afficher les détails d'un ra Interaction avec la base de
 * données Utilisation de JSP pour la vue
 *
 * @author asolanas
 */
@WebServlet("/afficher_ra")
@SuppressWarnings("serial")
public class AfficherResponsableActivite extends HttpServlet {

    /**
     * Gère les requêtes GET pour afficher les détails d'un Responsable
     * d'Activité.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
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

        PartenaireDao partenaireDao = new PartenaireDao();
        Collection<Partenaire> listPartenaireRa = partenaireDao.listPartenaire(ra.getId());
        List<String> partenaireNoms = new ArrayList<>();
        for (Partenaire partenaire : listPartenaireRa) {
            partenaireNoms.add(partenaire.getNom());
        }

        String partenaire = String.join(", ", partenaireNoms);

        req.setAttribute("partenaire", partenaire);

        CollaborateurDao collaborateurDao = new CollaborateurDao();
        Collection<Collaborateur> listCollaborateurRa = collaborateurDao.listCollaborateur(ra.getId());
        List<String> collaborateurNoms = new ArrayList<>();
        for (Collaborateur collaborateur : listCollaborateurRa) {
            collaborateurNoms.add(collaborateur.getNom());
        }

        String collaborateur = String.join(", ", collaborateurNoms);

        req.setAttribute("collaborateur", collaborateur);
        
        
        Collection<Prestation> prestations = raDao.listPrestationResponsableActivite(raId);
        List<String> partenaireNomsPrestation = new ArrayList<>();
        List<Integer> partenaireIdAdd = new ArrayList<>();
        List<String> collaborateurNomsPrestation = new ArrayList<>();
        List<Integer> collaborateurIdAdd = new ArrayList<>();
        for(Prestation presta : prestations){
            Integer partPrestation = presta.getId_partenaire();
            Integer collabPrestation = presta.getId_collaborateur();
            
            boolean partExists = partenaireIdAdd.stream()
                                   .anyMatch(p -> p == partPrestation);
            boolean collabExists = collaborateurIdAdd.stream()
                                   .anyMatch(p -> p == collabPrestation);
            
            if(!partExists){
                String nomPartPrestation = partenaireDao.read(partPrestation).getNom();
                partenaireNomsPrestation.add(nomPartPrestation);
                partenaireIdAdd.add(partPrestation);
            }
            
            if(!collabExists){
                String nomCollabPrestation = collaborateurDao.read(collabPrestation).getNom();
                collaborateurNomsPrestation.add(nomCollabPrestation);
                collaborateurIdAdd.add(collabPrestation);
            }
        }
        
        String partenairesPrestation = String.join(", ", partenaireNomsPrestation);
        String collaborateursPrestation = String.join(", ", collaborateurNomsPrestation);
        
        req.setAttribute("partenairesPrestation", partenairesPrestation);
        req.setAttribute("collaborateursPrestation", collaborateursPrestation);
        
        
        

        // Rediriger vers la JSP appropriée pour l'affichage
        req.getRequestDispatcher("/WEB-INF/jsp/afficherResponsableActivite.jsp").forward(req, resp);

    }
}
