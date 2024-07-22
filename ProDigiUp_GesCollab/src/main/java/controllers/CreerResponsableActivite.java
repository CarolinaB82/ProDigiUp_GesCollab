package controllers;

import dao.CollaborateurDao;
import dao.DaoFactory;
import dao.PartenaireDao;
import dao.PossederDao;
import dao.ProposerDao;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.Partenaire;
import entities.Posseder;
import entities.Proposer;
import entities.ResponsableActivite;
import forms.CreerResponsableActiviteFormChecker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * servlet nommée CreerRa qui gère les requêtes HTTP GET et POST / contrôleur
 * dans l'architecture MVC Creation de partenaire Valication des données en lien
 * avec CreerResponsableActiviteFormChecker Interaction avec la base de données
 * Utilisation JSP pour la vue
 *
 * @author asolanas
 */
@WebServlet("/creer_ra")
@SuppressWarnings("serial")
public class CreerResponsableActivite extends HttpServlet {

    /**
     * Gère les requêtes GET pour afficher la page de création de responsable
     * d'activité. Charge les listes de collaborateurs et partenaires depuis la
     * base de données et les ajoute en tant qu'attributs à la requête avant de
     * rediriger vers la page de création.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<Collaborateur> collaborateurList = DaoFactory.getCollaborateurDao().list();
        Collection<Partenaire> partenaireList = DaoFactory.getPartenaireDao().list();

        req.setAttribute("collaborateurList", collaborateurList);
        req.setAttribute("partenaireList", partenaireList);
        req.getRequestDispatcher("/WEB-INF/jsp/creerResponsableActivite.jsp").forward(req, resp);
    }

    /**
     * Gère les requêtes POST pour créer un nouveau responsable d'activité à
     * partir des données du formulaire. Valide le formulaire et gère les
     * erreurs le cas échéant.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        CreerResponsableActiviteFormChecker rafc = new CreerResponsableActiviteFormChecker(req);
        ResponsableActivite responsableActivite = rafc.checkForm();

        if (rafc.getErrors().isEmpty()) {
            ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
            PossederDao possederDao = new PossederDao();
            ProposerDao proposerDao = new ProposerDao();
            try {
                if (responsableActiviteDao.exists(responsableActivite.getMatricule())) {
                    rafc.addError("nom", "Le responsable activite existe déjà.");
                    loadLists(req);
                    req.setAttribute("errors", rafc.getErrors());
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerResponsableActivite.jsp").forward(req, resp);
                    return;
                }

                responsableActiviteDao.create(responsableActivite);

                int lastIdCreated = responsableActiviteDao.getLastIdCreated();
                ResponsableActivite ra = responsableActiviteDao.read(lastIdCreated);

                String[] collaborateurIds = req.getParameterValues("collaborateur");
                String[] partenaireIds = req.getParameterValues("partenaire");

                if (collaborateurIds != null) {
                    for (String collaborateurId : collaborateurIds) {
                        Posseder posseder = new Posseder();
                        posseder.setId_ra(ra.getId());
                        posseder.setId_collaborateur(Integer.parseInt(collaborateurId));
                        possederDao.create(posseder);
                    }
                }

                if (partenaireIds != null) {
                    for (String partenaireId : partenaireIds) {
                        Proposer proposer = new Proposer();
                        proposer.setId_ra(ra.getId());
                        proposer.setId_partenaire(Integer.parseInt(partenaireId));
                        proposerDao.create(proposer);
                    }
                }

                req.setAttribute("ra", ra);

                PartenaireDao partenaireDao = new PartenaireDao();
                List<String> partenaireNoms = new ArrayList<>();
                for (String partenaireIdStr : partenaireIds) {
                    Partenaire part = partenaireDao.read(Integer.parseInt(partenaireIdStr));
                    if (part != null) {
                        partenaireNoms.add(part.getNom());
                    }
                }

                String partenaire = String.join(", ", partenaireNoms);
                req.setAttribute("partenaire", partenaire);

                CollaborateurDao collaborateurDao = new CollaborateurDao();
                List<String> collaborateurNoms = new ArrayList<>();
                for (String collaborateurIdStr : collaborateurIds) {
                    Collaborateur collaborateur = collaborateurDao.read(Integer.parseInt(collaborateurIdStr));
                    if (collaborateur != null) {
                        collaborateurNoms.add(collaborateur.getNom());
                    }
                }

                String collaborateur = String.join(", ", collaborateurNoms);
                req.setAttribute("collaborateur", collaborateur);

                req.setAttribute("message", "Responsable d'Activité bien ajouté !");
                req.getRequestDispatcher("/WEB-INF/jsp/afficherResponsableActivite.jsp").forward(req, resp);
            } catch (SQLException ex) {
                if (ex.getMessage().contains("Le matricule existe déjà")) {
                    rafc.addError("matricule", "Le matricule existe déjà.");
                    loadLists(req);
                    req.setAttribute("errors", rafc.getErrors());
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerResponsableActivite.jsp").forward(req, resp);
                } else {
                    Logger.getLogger(CreerResponsableActivite.class.getName()).log(Level.SEVERE, null, ex);
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerResponsableActivite.jsp").forward(req, resp);
                }
            }
        } else {
            loadLists(req);
            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
            req.getRequestDispatcher("/WEB-INF/jsp/creerResponsableActivite.jsp").forward(req, resp);
        }
    }

    private void loadLists(HttpServletRequest req) throws ServletException {
        Collection<Collaborateur> collaborateurList = DaoFactory.getCollaborateurDao().list();
        Collection<Partenaire> partenaireList = DaoFactory.getPartenaireDao().list();
        req.setAttribute("collaborateurList", collaborateurList);
        req.setAttribute("partenaireList", partenaireList);
    }
}
