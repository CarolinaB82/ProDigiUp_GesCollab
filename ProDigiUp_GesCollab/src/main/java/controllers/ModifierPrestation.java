package controllers;

import dao.CollaborateurDao;
import dao.DaoFactory;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asolanas
 */
@WebServlet("/modifierPrestation")
@SuppressWarnings("serial")
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
        Collection<ResponsableActivite> ras = raDao.list();

        req.setAttribute("prestation", prestation);
        req.setAttribute("partenaires", partenaires);
        req.setAttribute("collaborateurs", collaborateurs);
        req.setAttribute("ras", ras);
        req.getRequestDispatcher("/WEB-INF/jsp/modifierPrestation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        ModifierPrestationFormChecker formChecker = new ModifierPrestationFormChecker();
        Prestation prestation = formChecker.checkForm((javax.servlet.http.HttpServletRequest) req);
        Map<String, String> errors = formChecker.getErrors();

        if (errors.isEmpty()) {
            PrestationDao prestationDao = new PrestationDao();
            prestationDao.update(prestation);
            resp.sendRedirect(req.getContextPath() + "/prestation?id=" + prestation.getId());
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("prestation", prestation);
            req.getRequestDispatcher("/WEB-INF/jsp/modifierPrestation.jsp").forward(req, resp);
        }
    }

}
