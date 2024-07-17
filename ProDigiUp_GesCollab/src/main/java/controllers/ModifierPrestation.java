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

@WebServlet("/modifierPrestation")
public class ModifierPrestation extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        if (nv.getErrors().isEmpty()) {
            prestationDao.update(prestation);
            req.setAttribute("message", "La prestation a été mise à jour avec succès.");
            req.getRequestDispatcher("/WEB-INF/jsp/afficherPrestation.jsp").forward(req, resp);
        } else {
            PartenaireDao partenaireDao = new PartenaireDao();
            CollaborateurDao collaborateurDao = new CollaborateurDao();
            ResponsableActiviteDao raDao = new ResponsableActiviteDao();
            
            Collection<Partenaire> partenaires = partenaireDao.list();
            Collection<Collaborateur> collaborateurs = collaborateurDao.list();
            Collection<ResponsableActivite> responsablesActivite = raDao.list();

            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
            req.setAttribute("responsableActiviteList", responsablesActivite);
            req.setAttribute("partenaireList", partenaires);
            req.setAttribute("collaborateurList", collaborateurs);
            req.setAttribute("errors", nv.getErrors());
            req.setAttribute("prestation", prestation);
            req.getRequestDispatcher("/WEB-INF/jsp/modifierPrestation.jsp").forward(req, resp);
        }
    }
}
