package controllers;

import dao.ResponsableActiviteDao;
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
        req.getRequestDispatcher("/WEB-INF/jsp/creerResponsableActivite.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        CreerResponsableActiviteFormChecker rafc = new CreerResponsableActiviteFormChecker(req);
        ResponsableActivite responsableActivite = rafc.checkForm();

        if (rafc.getErrors().isEmpty()) {
            ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
            try {

                if (responsableActiviteDao.exists(responsableActivite.getMatricule())) {
                    rafc.addError("matricule", "Le matricule existe déjà.");
                    req.setAttribute("errors", rafc.getErrors());
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerResponsableActivite.jsp").forward(req, resp);
                    return;
                }

                responsableActiviteDao.create(responsableActivite);
                ResponsableActivite ra = responsableActiviteDao.read(responsableActivite.getId());
                req.setAttribute("ra", ra);
                req.setAttribute("message", "Responsable d'Activité bien ajouté !");
                req.getRequestDispatcher("/WEB-INF/jsp/afficherResponsableActivite.jsp").forward(req, resp);
            } catch (SQLException ex) {

                if (ex.getMessage().contains("Le matricule existe déjà")) {
                    rafc.addError("matricule", "Le matricule existe déjà.");
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
            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
            req.getRequestDispatcher("/WEB-INF/jsp/creerResponsableActivite.jsp").forward(req, resp);
        }

    }
}
