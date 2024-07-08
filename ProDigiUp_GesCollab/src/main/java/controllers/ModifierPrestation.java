package controllers;

import dao.DaoFactory;
import dao.PrestationDao;
import dao.ResponsableActiviteDao;
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
    
    String idParam = req.getParameter("id_prestation");
    if (idParam == null || idParam.isEmpty()) {
        resp.sendRedirect(req.getContextPath() + "/403.jsp");
        return;
    }

    int prestationId;
    try {
        prestationId = Integer.parseInt(idParam);
    } catch (NumberFormatException e) {
        resp.sendRedirect(req.getContextPath() + "/404.jsp");
        return;
    }

    PrestationDao prestationDao = new PrestationDao();
    Prestation prestation = prestationDao.read(prestationId);
    
    if (prestation == null) {
        resp.sendRedirect(req.getContextPath() + "/500.jsp");
        return;
    }

    ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
    Collection<ResponsableActivite> responsableActiviteList = responsableActiviteDao.list();
    Collection<ResponsableActivite> listResponsableActivitesPrestation = responsableActiviteDao.listResponsableActivite(prestation.getId());
    List<Integer> selectedResponsables = new ArrayList<>();
    for (ResponsableActivite responsable : listResponsableActivitesPrestation) {
        selectedResponsables.add(responsable.getId());
    }
    
    req.setAttribute("prestation", prestation);
    req.setAttribute("selectedResponsables", selectedResponsables);
    req.setAttribute("responsableActiviteList", responsableActiviteList);
    req.getRequestDispatcher("/WEB-INF/jsp/modifierPrestation.jsp").forward(req, resp);
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        // Récupérer les données du formulaire
        String siglum_presta = request.getParameter("siglum_presta");
        String num_affaire = request.getParameter("num_affaire");
        String nom_presta = request.getParameter("nom_presta");
        String ref_fact_partenaire = request.getParameter("ref_fact_partenaire");
        String mail_partenaire = request.getParameter("mail_partenaire");
        String ref_fact_airbus = request.getParameter("ref_fact_airbus");
        String mail_airbus = request.getParameter("mail_airbus");
        String idRaParam = request.getParameter("id_ra");
        String idCollaborateurParam = request.getParameter("id_collaborateur");
        String idPartenaireParam = request.getParameter("id_partenaire");
        String idPrestationParam = request.getParameter("id_prestation");

        StringBuilder errorMessage = new StringBuilder();

        // Validation des paramètres
        if (siglum_presta == null || siglum_presta.isEmpty()) {
            errorMessage.append("Le siglum de la prestation est requis.<br>");
        }
        if (num_affaire == null || num_affaire.isEmpty()) {
            errorMessage.append("Le numéro d'affaire est requis.<br>");
        }
        if (nom_presta == null || nom_presta.isEmpty()) {
            errorMessage.append("Le nom de la prestation est requis.<br>");
        }
        if (ref_fact_partenaire == null || ref_fact_partenaire.isEmpty()) {
            errorMessage.append("La référence de facturation du partenaire est requise.<br>");
        }
        if (mail_partenaire == null || mail_partenaire.isEmpty()) {
            errorMessage.append("L'email du partenaire est requis.<br>");
        }
        if (ref_fact_airbus == null || ref_fact_airbus.isEmpty()) {
            errorMessage.append("La référence de facturation Airbus est requise.<br>");
        }
        if (mail_airbus == null || mail_airbus.isEmpty()) {
            errorMessage.append("L'email Airbus est requis.<br>");
        }
        if (idRaParam == null || idRaParam.isEmpty()) {
            errorMessage.append("L'identifiant du responsable est requis.<br>");
        }
        if (idCollaborateurParam == null || idCollaborateurParam.isEmpty()) {
            errorMessage.append("L'identifiant du collaborateur est requis.<br>");
        }
        if (idPartenaireParam == null || idPartenaireParam.isEmpty()) {
            errorMessage.append("L'identifiant du partenaire est requis.<br>");
        }
        if (idPrestationParam == null || idPrestationParam.isEmpty()) {
            errorMessage.append("L'identifiant de la prestation est requis.<br>");
        }

        if (errorMessage.length() > 0) {
            request.setAttribute("errorMessage", errorMessage.toString());
            request.getRequestDispatcher("modifierPrestation.jsp").forward(request, response);
            return;
        }

        // Convertir les paramètres en entier
        int id_ra = Integer.parseInt(idRaParam);
        int id_collaborateur = Integer.parseInt(idCollaborateurParam);
        int id_partenaire = Integer.parseInt(idPartenaireParam);
        int id_prestation = Integer.parseInt(idPrestationParam);

        // Créer un objet Prestation avec les données récupérées
        Prestation prestation = new Prestation();
        prestation.setId(id_prestation);
        prestation.setSiglum_presta(siglum_presta);
        prestation.setNum_affaire(num_affaire);
        prestation.setNom_presta(nom_presta);
        prestation.setRef_fact_partenaire(ref_fact_partenaire);
        prestation.setMail_partenaire(mail_partenaire);
        prestation.setRef_fact_airbus(ref_fact_airbus);
        prestation.setMail_airbus(mail_airbus);
        prestation.setId_ra(id_ra);
        prestation.setId_collaborateur(id_collaborateur);
        prestation.setId_partenaire(id_partenaire);

        // Valider les données
        if (ModifierPrestationFormChecker.isFormValid(prestation)) {
            // Mettre à jour les données dans la base de données
            PrestationDao prestationDao = new PrestationDao();
            prestationDao.update(prestation);
            response.sendRedirect("/WEB-INF/jsp/afficherPrestation.jsp"); // Redirigez vers une page de succès
        } else {
            request.setAttribute("errorMessage", "Données invalides. Veuillez vérifier les champs du formulaire.");
            request.getRequestDispatcher("/WEB-INF/jsp/modifierPrestation.jsp").forward(request, response); // Renvoyer au formulaire en cas d'erreur
        }
    }
}

