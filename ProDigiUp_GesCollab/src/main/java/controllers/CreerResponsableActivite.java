package controllers;

import dao.DaoFactory;
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
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asolanas
 */
@WebServlet("/creer_ra")
@SuppressWarnings("serial")
public class CreerResponsableActivite extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<Collaborateur> collaborateurList = DaoFactory.getCollaborateurDao().list();
        Collection<Partenaire> partenaireList = DaoFactory.getPartenaireDao().list();

        req.setAttribute("collaborateurList", collaborateurList);
        req.setAttribute("partenaireList", partenaireList);
        req.getRequestDispatcher("/WEB-INF/jsp/creerResponsableActivite.jsp").forward(req, resp);
    }

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
               
                //  ResponsableActivite ra = responsableActiviteDao.read(responsableActivite.getId());
                req.setAttribute("ra", ra);
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
