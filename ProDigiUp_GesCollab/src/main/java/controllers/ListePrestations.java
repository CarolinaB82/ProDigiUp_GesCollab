package controllers;

import dao.CollaborateurDao;
import dao.DaoFactory;
import dao.PartenaireDao;
import dao.ResponsableActiviteDao;
import entities.CollaborateurPrestationPartenaireRa;
import entities.Prestation;
import entities.PrestationPartRaCollab;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static org.apache.commons.lang3.ClassUtils.comparator;

/**
 * servlet nommée ListePartenaires qui gère les requêtes HTTP GET / contrôleur
 * dans l'architecture MVC Affichage de la liste des partenaires Utilisation de
 * JSP pour la vue
 *
 * @author cberge
 */
@WebServlet("/liste_prestations")
@SuppressWarnings("serial")
public class ListePrestations extends HttpServlet {

    /**
     * Traite les requêtes GET en récupérant et affichant la liste des
     * prestations.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        Collection<Prestation> prestations = DaoFactory.getPrestationDao().list();

        Collection<PrestationPartRaCollab> prestationsPartRaCollab = new ArrayList<>();

        CollaborateurDao collabDao = DaoFactory.getCollaborateurDao();
        PartenaireDao partDao = DaoFactory.getPartenaireDao();
        ResponsableActiviteDao raDao = DaoFactory.ResponsableActiviteDao();

        for (Prestation presta : prestations) {
            PrestationPartRaCollab prestaPartRaCollab = new PrestationPartRaCollab();
            prestaPartRaCollab.setPrestation(presta);
            prestaPartRaCollab.setNomCollab(collabDao.read(presta.getId_collaborateur()).getNom());
            prestaPartRaCollab.setNomPartenaire(partDao.read(presta.getId_partenaire()).getNom());
            prestaPartRaCollab.setNomRa(raDao.read(presta.getId_ra()).getNom());
            prestationsPartRaCollab.add(prestaPartRaCollab);
        }

        String sort = req.getParameter("sort");
        String order = req.getParameter("order");

        Comparator<PrestationPartRaCollab> comparator = Comparator.comparing(presta -> presta.getPrestation().getNom_presta().toLowerCase());

        if ("nom".equals(sort)) {
            comparator = Comparator.comparing(presta -> presta.getPrestation().getNom_presta().toLowerCase());
        }

        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        List<PrestationPartRaCollab> sortedPrestations = prestationsPartRaCollab.stream().sorted(comparator).collect(Collectors.toList());

        req.setAttribute("prestations", sortedPrestations);
        req.setAttribute("param", req.getParameterMap());

        req.getRequestDispatcher("/WEB-INF/jsp/listePrestations.jsp").forward(req, resp);
    }
}
