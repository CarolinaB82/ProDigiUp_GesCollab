package controllers;
 
import dao.CollaborateurDao;
import dao.PartenaireDao;
import dao.PrestationDao;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.Partenaire;
import entities.Prestation;
import entities.ResponsableActivite;
import forms.ModifierPrestationFormChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
 
/**
* servlet nommée ModifierPrestation qui gère les requêtes HTTP GET et POST /
* contrôleur dans l'architecture MVC Affichage des détails d'une prestation
* pour modification Validation des données soumises via un formulaire Gestion
* des erreurs de validation Mise à jour des données dans la base de données
* Préparation des données pour l'affichage après la modification Interaction
* avec la couche de présentation
*
* @author asolanas
*/
@WebServlet("/modifierPrestation")
public class ModifierPrestation extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
 
    /**
     * Traite les requêtes GET pour afficher le formulaire de modification de la
     * prestation. Charge les informations de la prestation à partir de
     * l'identifiant fourni dans les paramètres de la requête. Charge également
     * la liste des partenaires, collaborateurs et responsables d'activité
     * depuis la base de données.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
 
        int prestationId = Integer.parseInt(req.getParameter("id"));
 
        PrestationDao prestationDao = new PrestationDao();
        Prestation prestation = prestationDao.read(prestationId);
 
        if (prestation == null) {
            resp.sendRedirect(req.getContextPath() + "/404.jsp");
            return;
        }
 
        PartenaireDao partenaireDao = new PartenaireDao();
        CollaborateurDao collaborateurDao = new CollaborateurDao();
        ResponsableActiviteDao raDao = new ResponsableActiviteDao();
 
        Collection<Partenaire> partenaires = partenaireDao.list();
        Collection<Collaborateur> collaborateurs = collaborateurDao.list();
        Collection<ResponsableActivite> responsablesActivite = raDao.list();
 
        req.setAttribute("prestation", prestation);
        req.setAttribute("partenaires", partenaires);
        req.setAttribute("collaborateurs", collaborateurs);
        req.setAttribute("responsablesActivite", responsablesActivite);
        req.getRequestDispatcher("/WEB-INF/jsp/modifierPrestation.jsp").forward(req, resp);
    }
 
    /**
     * Traite les requêtes POST pour soumettre les modifications du formulaire
     * de modification de la prestation. Valide les données du formulaire, gère
     * les erreurs éventuelles et met à jour les informations de la prestation
     * en base de données.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
 
        ModifierPrestationFormChecker nv = new ModifierPrestationFormChecker(req);
        Prestation prestation = nv.checkForm();
 
        if (prestation == null) {
            resp.sendRedirect(req.getContextPath() + "/404.jsp");
            return;
        }
 
        PrestationDao prestationDao = new PrestationDao();
        ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        PartenaireDao partenaireDao = new PartenaireDao();
        CollaborateurDao collaborateurDao = new CollaborateurDao();
 
        if (nv.getErrors().isEmpty()) {
            prestationDao.update(prestation);
 
            // Relecture des données mises à jour
            prestation = prestationDao.read(prestation.getId());
 
            Collaborateur collabPresta = collaborateurDao.read(prestation.getId_collaborateur());
            req.setAttribute("collaborateur", collabPresta.getNom());
 
            ResponsableActivite responsable = responsableActiviteDao.read(prestation.getId_ra());
            req.setAttribute("responsablesActivite", responsable.getNom());
 
            Partenaire part = partenaireDao.read(prestation.getId_partenaire());
            req.setAttribute("partenaire", part.getNom());
            req.setAttribute("prestation", prestation);
 
            req.setAttribute("message", "La prestation a été mise à jour avec succès.");
            req.getRequestDispatcher("/WEB-INF/jsp/afficherPrestation.jsp").forward(req, resp);
        } else {
            Collection<ResponsableActivite> responsableActiviteList = responsableActiviteDao.list();
            Collection<Partenaire> partenaireList = partenaireDao.list();
            Collection<Collaborateur> collaborateurList = collaborateurDao.list();
            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
            req.setAttribute("selectedResponsables", responsableActiviteList);
            req.setAttribute("selectedPartenaires", partenaireList);
            req.setAttribute("selectedCollaborateurs", collaborateurList);
            req.setAttribute("responsableActiviteList", responsableActiviteList);
            req.setAttribute("partenaireList", partenaireList);
            req.setAttribute("collaborateurList", collaborateurList);
            req.setAttribute("errors", nv.getErrors());
            req.setAttribute("prestation", prestation);
            req.getRequestDispatcher("/WEB-INF/jsp/modifierPrestation.jsp").forward(req, resp);
        }
    }
 
}
