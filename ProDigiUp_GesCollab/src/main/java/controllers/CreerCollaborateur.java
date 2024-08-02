/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import dao.DaoFactory;
import dao.PossederDao;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.Posseder;
import entities.ResponsableActivite;
import forms.CreerCollaborateurFormChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import historique.ValidationUtil;
import static historique.ValidationUtil.sanitizeMatricule;

/**
 * servlet nommée CreerCollaborateur qui gère les requêtes HTTP GET et POST /
 * contrôleur dans l'architecture MVC Creation de collaborateur Valication des
 * données en lien avec CreerCollaborateurFormChecker Interaction avec la base
 * de données Utilisation JSP pour la vue
 *
 * @author cberge
 */
@WebServlet("/creer_collaborateur")
@SuppressWarnings("serial")
public class CreerCollaborateur extends HttpServlet {

    /**
     * Gère les requêtes GET pour afficher la page de création de collaborateur.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<ResponsableActivite> responsableActiviteList = DaoFactory.ResponsableActiviteDao().list();
        req.setAttribute("responsableActiviteList", responsableActiviteList);

        req.getRequestDispatcher("/WEB-INF/jsp/creerCollaborateur.jsp").forward(req, resp);
    }

    /**
     * Gère les requêtes POST pour créer un nouveau collaborateur à partir des
     * données du formulaire. Valide le formulaire et gère les erreurs le cas
     * échéant.
     *
     * @param req HttpServletRequest représentant la requête HTTP
     * @param resp HttpServletResponse représentant la réponse HTTP
     * @throws ServletException Si une erreur de servlet se produit
     * @throws IOException Si une erreur d'entrée-sortie se produit
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        CreerCollaborateurFormChecker nv = new CreerCollaborateurFormChecker(req);
        Collaborateur collaborateur = nv.checkForm();
        
        // Récupération et nettoyage des paramètres de la requête
    String matricule = req.getParameter("matricule").trim();
    String sanitizedMatricule = sanitizeMatricule(matricule);
 if (!sanitizedMatricule.isEmpty()) {
        try {
            // Convertir la chaîne assainie en entier
            int matriculeInt = Integer.parseInt(sanitizedMatricule);
            collaborateur.setMatricule(matriculeInt);
        } catch (NumberFormatException e) {
            nv.addError("matricule", "Le matricule doit être un nombre entier.");
        }
    } else {
        nv.addError("matricule", "Matricule invalide.");
    }

    // Validation et nettoyage du nom
    String nom = req.getParameter("nom").trim();
    String sanitizedNom = nom.replaceAll("[^a-zA-Z ]", ""); // Nettoyage pour garder uniquement les lettres et espaces
    if (sanitizedNom.isEmpty()) {
        nv.addError("nom", "Nom invalide.");
    } else {
        collaborateur.setNom(sanitizedNom);
    }

        // Gestion de la sélection RQTH et de la date de renouvellement
        String rqth = req.getParameter("rqth");
        boolean isOuiSelected = "oui".equals(rqth);
        boolean isAVieSelected = "a vie".equals(rqth);
        String dateDeRenouvellementStr = req.getParameter("date_de_renouvellement");
        LocalDate dateDeRenouvellement = null;

        if (isOuiSelected) {
            try {

                dateDeRenouvellement = LocalDate.parse(dateDeRenouvellementStr);
            } catch (DateTimeParseException e) {
                nv.addError("date_de_renouvellement", "Date de renouvellement invalide.");
            }
        } else {
            dateDeRenouvellement = null; // Default date if "non" is selected
        }

        collaborateur.setDate_de_renouvellement(dateDeRenouvellement);

        req.setAttribute("isOuiSelected", isOuiSelected);
        req.setAttribute("isAVieSelected", isAVieSelected);

        //if (nv.getErrors().isEmpty()) {
        if (nv.getErrors().isEmpty()) {
            CollaborateurDao collaborateurDao = new CollaborateurDao();
            PossederDao possederDao = new PossederDao();
            // Appel de la méthode create du DOA
            // Si une erreur dans l'insert alors une SQLException est levé
            // On l'intercepte dans le catch et on affiche un msg d'erreur à l'utilisateur

            try {
                if (collaborateurDao.exists(collaborateur.getMatricule())) {
                    nv.addError("matricule", "Le matricule existe déjà.");
                    loadLists(req);
                    req.setAttribute("errors", nv.getErrors());
                    req.setAttribute("collaborateur", collaborateur);
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerCollaborateur.jsp").forward(req, resp);
                    return;
                }

                collaborateurDao.create(collaborateur);

                //methode creer pour recuperer le dernier Id cree
                int lastIdCreated = collaborateurDao.getLastIdCreated();
                Collaborateur collab = collaborateurDao.read(lastIdCreated);
                // Formatage de la date
                if (collaborateur.getDate_de_renouvellement() != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String formattedDate = collaborateur.getDate_de_renouvellement().format(formatter);
                    req.setAttribute("formattedDate", formattedDate);
                }
                String[] responsableActiviteIds = req.getParameterValues("responsable");

                if (responsableActiviteIds != null) {
                    for (String responsableId : responsableActiviteIds) {
                        int idResponsable = Integer.parseInt(responsableId);

                        if (idResponsable == collab.getId()) {
                            nv.addError("responsable", "L'ID du collaborateur ne peut pas être égal à l'ID du responsable d'activité.");
                            loadLists(req);
                            req.setAttribute("errors", nv.getErrors());
                            req.setAttribute("collaborateur", collaborateur);
                            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                            req.getRequestDispatcher("/WEB-INF/jsp/creerCollaborateur.jsp").forward(req, resp);
                            return;
                        }

                        Posseder posseder = new Posseder();
                        posseder.setId_ra(Integer.parseInt(responsableId));
                        posseder.setId_collaborateur(collab.getId());
                        possederDao.create(posseder);
                    }
                }

                req.setAttribute("collaborateur", collab);

                ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
                List<String> responsableNoms = new ArrayList<>();
                if (responsableActiviteIds != null) {
                    for (String responsableIdStr : responsableActiviteIds) {
                        ResponsableActivite responsable = responsableActiviteDao.read(Integer.parseInt(responsableIdStr));
                        if (responsable != null) {
                            responsableNoms.add(responsable.getNom());
                        }
                    }
                }
                

                String responsablesActivite = String.join(", ", responsableNoms);

                req.setAttribute("responsablesActivite", responsablesActivite);

                req.setAttribute("message", "Votre collaborateur est bien enregistré");
                req.getRequestDispatcher("/WEB-INF/jsp/afficherCollaborateur.jsp").forward(req, resp);

            } catch (SQLException ex) {
                if (ex.getMessage().contains("Le matricule existe déjà")) {
                    nv.addError("matricule", "Le matricule existe déjà.");
                    loadLists(req);
                    req.setAttribute("errors", nv.getErrors());
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerCollaborateur.jsp").forward(req, resp);
                } else {
                    Logger.getLogger(CreerCollaborateur.class.getName()).log(Level.SEVERE, null, ex);
                    req.setAttribute("errorMsg", "Erreur lors de la création du collaborateur");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerCollaborateur.jsp").forward(req, resp);
                }
            }
        } else {
            loadLists(req);
            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
            req.setAttribute("errors", nv.getErrors());
            req.setAttribute("collaborateur", collaborateur);
            req.getRequestDispatcher("/WEB-INF/jsp/creerCollaborateur.jsp").forward(req, resp);
        }

    }

    private void loadLists(HttpServletRequest req) throws ServletException {
        Collection<ResponsableActivite> responsableActiviteList = DaoFactory.ResponsableActiviteDao().list();
        req.setAttribute("responsableActiviteList", responsableActiviteList);
    }

}
