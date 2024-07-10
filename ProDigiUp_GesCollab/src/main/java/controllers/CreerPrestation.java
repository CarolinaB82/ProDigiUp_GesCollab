/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
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
import forms.CreerPrestationFormChecker;
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
 *
 * @author cberge
 */
@WebServlet("/creer_prestation")
@SuppressWarnings("serial")
public class CreerPrestation extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Collection<ResponsableActivite> responsableActiviteList = DaoFactory.ResponsableActiviteDao().list();
        Collection<Collaborateur> collaborateurList = DaoFactory.getCollaborateurDao().list();
        Collection<Partenaire> partenaireList = DaoFactory.getPartenaireDao().list();
        // Ajouter les listes en tant qu'attributs de la requête
        req.setAttribute("responsableActiviteList", responsableActiviteList);
        req.setAttribute("collaborateurList", collaborateurList);
        req.setAttribute("partenaireList", partenaireList);
        req.getRequestDispatcher("/WEB-INF/jsp/creerPrestation.jsp").forward(req, resp);
    }

        
         @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        
        CreerPrestationFormChecker rafc = new CreerPrestationFormChecker(req);
        Prestation prestation = rafc.checkForm();

        if (rafc.getErrors().isEmpty()) {
            PrestationDao prestationDao = new PrestationDao();
            try {
                 if (prestationDao.exists(prestation.getNom_presta())) {
                    rafc.addError("nom_presta", "La prestation existe déjà.");
                    loadLists(req);
                    req.setAttribute("errors", rafc.getErrors());
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerPrestation.jsp").forward(req, resp);
                    return;
                }
                
                 
                 
                prestationDao.create(prestation);
                //methode creer pour recuperer le dernier Id cree
                int lastIdCreated = prestationDao.getLastIdCreated();
                Prestation presta = prestationDao.read(lastIdCreated);
                
                 req.setAttribute("prestation", presta);
                 
                CollaborateurDao collaborateurDao = new CollaborateurDao();
                Collaborateur collabPresta = collaborateurDao.read(presta.getId_collaborateur());
                req.setAttribute("collaborateur", collabPresta.getNom());
                
                ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
                ResponsableActivite responsable = responsableActiviteDao.read(presta.getId_ra());
                req.setAttribute("responsablesActivite", responsable.getNom());

                 PartenaireDao partenaireDao = new PartenaireDao();
                 Partenaire part = partenaireDao.read(presta.getId_partenaire());
                req.setAttribute("partenaire", part.getNom());
                 
                 
                
                
                req.setAttribute("message", "Prestation bien ajouté !");
                req.getRequestDispatcher("/WEB-INF/jsp/afficherPrestation.jsp").forward(req, resp);
            } catch (SQLException ex) {
                
              if (ex.getMessage().contains("La prestation existe déjà")) {
                    rafc.addError("presta", "La prestation existe déjà.");
                    loadLists(req);
                    req.setAttribute("errors", rafc.getErrors());
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerPrestation.jsp").forward(req, resp);
                } else {
                    Logger.getLogger(CreerPrestation.class.getName()).log(Level.SEVERE, null, ex);
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerPrestation.jsp").forward(req, resp);
                }  }
                } else {
            loadLists(req);
            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
            req.getRequestDispatcher("/WEB-INF/jsp/creerPrestation.jsp").forward(req, resp);
        }

    }
private void loadLists(HttpServletRequest req) throws ServletException {
    Collection<ResponsableActivite> responsableActiviteList = DaoFactory.ResponsableActiviteDao().list();
    Collection<Collaborateur> collaborateurList = DaoFactory.getCollaborateurDao().list();
    Collection<Partenaire> partenaireList = DaoFactory.getPartenaireDao().list();
    req.setAttribute("responsableActiviteList", responsableActiviteList);
    req.setAttribute("collaborateurList", collaborateurList);
    req.setAttribute("partenaireList", partenaireList);
}
}

