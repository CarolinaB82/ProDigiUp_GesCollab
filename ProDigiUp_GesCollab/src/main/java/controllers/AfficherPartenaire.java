/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.DaoFactory;
import dao.PartenaireDao;
import dao.ResponsableActiviteDao;
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
 * servlet nommée AfficherPartenaire qui gère les requêtes HTTP GET / contrôleur
 * dans l'architecture MVC Afficher les détails d'un partenaire Interaction avec
 * la base de données Utilisation de JSP pour la vue
 *
 * @author asolanas
 */
@WebServlet("/partenaire")
@SuppressWarnings("serial")
public class AfficherPartenaire extends HttpServlet {

    /**
     * Gère les requêtes GET pour afficher les détails d'un partenaire.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        String partenaireIdParam = req.getParameter("id");
        int partenaireId = 1;

        if (partenaireIdParam != null && !partenaireIdParam.isEmpty()) {
            try {
                partenaireId = Integer.parseInt(partenaireIdParam);
            } catch (NumberFormatException e) {
                // Gérer l'erreur de conversion si nécessaire
            }
        }

        PartenaireDao partenaireDao = DaoFactory.getPartenaireDao();
        Partenaire partenaire = partenaireDao.read(partenaireId);

        req.setAttribute("partenaire", partenaire);
        ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        Collection<ResponsableActivite> listResponsableActivitePartenaire = responsableActiviteDao.listResponsablesActivite(partenaire.getId());
        List<String> responsableNoms = new ArrayList<>();
        for (ResponsableActivite responsable : listResponsableActivitePartenaire) {
            responsableNoms.add(responsable.getNom());
        }

        String responsablesActivite = String.join(", ", responsableNoms);

        Collection<Prestation> prestations = partenaireDao.listPrestationPartenaire(partenaireId);
        List<String> responsableNomsPrestation = new ArrayList<>();
        List<Integer> respIdAdd = new ArrayList<>();
        for (Prestation presta : prestations) {
            Integer raPrestation = presta.getId_ra();
            boolean raExists = respIdAdd.stream()
                    .anyMatch(p -> p == raPrestation);
            if (!raExists) {
                String nomRaPrestation = responsableActiviteDao.read(raPrestation).getNom();
                responsableNomsPrestation.add(nomRaPrestation);
                respIdAdd.add(raPrestation);
            }
        }

        String responsablesActivitePrestations = String.join(", ", responsableNomsPrestation);

        req.setAttribute("responsablesActivite", responsablesActivite);
        req.setAttribute("responsablesActivitePrestation", responsablesActivitePrestations);
        req.setAttribute("partenaire", partenaire);
        req.getRequestDispatcher("/WEB-INF/jsp/afficherPartenaire.jsp").forward(req, resp);
    }

}
