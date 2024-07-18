/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.DaoFactory;
import dao.PartenaireDao;
import dao.ProposerDao;
import dao.ResponsableActiviteDao;
import entities.Partenaire;
import entities.Proposer;
import entities.ResponsableActivite;
import forms.CreerPartenaireFormChecker;
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
@WebServlet("/creer_partenaire")
@SuppressWarnings("serial")
public class CreerPartenaire extends HttpServlet {
    
   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
                Collection<ResponsableActivite> responsableActiviteList = DaoFactory.ResponsableActiviteDao().list();
        req.setAttribute("responsableActiviteList", responsableActiviteList);

        req.getRequestDispatcher("/WEB-INF/jsp/creerPartenaire.jsp").forward(req, resp);
    }
    
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        CreerPartenaireFormChecker rafc = new CreerPartenaireFormChecker(req);
        Partenaire partenaire = rafc.checkForm();

        if (rafc.getErrors().isEmpty()) {
            PartenaireDao partenaireDao = new PartenaireDao();
            ProposerDao proposerDao = new ProposerDao();
            try {
                 if (partenaireDao.exists(partenaire.getNom())) {
                    rafc.addError("nom", "Le partenaire existe déjà.");
                    loadLists(req);
                    req.setAttribute("errors", rafc.getErrors());
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerPrestation.jsp").forward(req, resp);
                    return;
                }
                   partenaireDao.create(partenaire);
                //methode creer pour recuperer le dernier Id cree
                int lastIdCreated = partenaireDao.getLastIdCreated();
                Partenaire part = partenaireDao.read(lastIdCreated);
                
                // Récupérer les responsables d'activité sélectionnés
            String[] responsableActiviteIds = req.getParameterValues("responsable");
// Vérifier que des responsables ont bien été sélectionnés
            if (responsableActiviteIds != null) {
            for (String responsableId : responsableActiviteIds) {
                Proposer proposer = new Proposer();
                proposer.setId_ra(Integer.parseInt(responsableId));
                proposer.setId_partenaire(part.getId());
                proposerDao.create(proposer);
            }}
            
            
                req.setAttribute("partenaire", part);
                
                
                  ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
                List<String> responsableNoms = new ArrayList<>();
                for(String responsableIdStr : responsableActiviteIds){
                    ResponsableActivite responsable = responsableActiviteDao.read(Integer.parseInt(responsableIdStr));
                    if(responsable != null) {
                        responsableNoms.add(responsable.getNom());
                    }
                }

                String responsablesActivite = String.join(", ", responsableNoms);

                req.setAttribute("responsablesActivite", responsablesActivite);
                
                req.setAttribute("message", "Partenaire bien ajouté !");
                req.getRequestDispatcher("/WEB-INF/jsp/afficherPartenaire.jsp").forward(req, resp);
            } catch (SQLException ex) {
                
              if (ex.getMessage().contains("Le partenaire existe déjà")) {
                    rafc.addError("presta", "Le partenaire existe déjà.");
                    loadLists(req);
                    req.setAttribute("errors", rafc.getErrors());
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerPartenaire.jsp").forward(req, resp);
                } else {
                    Logger.getLogger(CreerPrestation.class.getName()).log(Level.SEVERE, null, ex);
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerPartenaire.jsp").forward(req, resp);
                }  }
                } else {
            loadLists(req);
            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
            req.getRequestDispatcher("/WEB-INF/jsp/creerPartenaire.jsp").forward(req, resp);
        }

    }
private void loadLists(HttpServletRequest req) throws ServletException {
    Collection<ResponsableActivite> responsableActiviteList = DaoFactory.ResponsableActiviteDao().list();
    req.setAttribute("responsableActiviteList", responsableActiviteList);
}
}

