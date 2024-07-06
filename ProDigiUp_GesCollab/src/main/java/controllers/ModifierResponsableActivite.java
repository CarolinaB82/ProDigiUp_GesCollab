/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import dao.PartenaireDao;
import dao.PossederDao;
import dao.ProposerDao;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.Partenaire;
import entities.ResponsableActivite;
import forms.ModifierResponsableActiviteFormChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cberge
 */
@WebServlet("/modifier_ra")
@SuppressWarnings("serial")

public class ModifierResponsableActivite extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        int responsableActiviteId = Integer.parseInt(req.getParameter("id"));

        ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        ResponsableActivite ra = responsableActiviteDao.read(responsableActiviteId);

        
        // Récupération des partenaires
        PartenaireDao partenaireDao = new PartenaireDao();
        Collection<Partenaire> partenaireList = partenaireDao.list();
        Collection<Partenaire> listPartenaireRa = partenaireDao.listPartenaire(ra.getId());

        List<Integer> selectedPartenaires = new ArrayList<>();
        for (Partenaire partenaire : listPartenaireRa) {
            selectedPartenaires.add(partenaire.getId());
        }
        
        if (ra == null) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
            return;
        }

        CollaborateurDao collaborateurDao = new CollaborateurDao();
        Collection<Collaborateur> collaborateurList = collaborateurDao.list();
        Collection<Collaborateur> listCollaborateurRa = collaborateurDao.listCollaborateur(ra.getId());

        List<Integer> selectedCollaborateurs = new ArrayList<>();
        for (Collaborateur collaborateur : listCollaborateurRa) {
            selectedCollaborateurs.add(collaborateur.getId());
        }
        
        if (ra == null) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
            return;
        }
 //Détermine si les valeurs "oui" et "a vie" sont sélectionnées pour le RQTH
//        boolean isOuiSelected = "oui".equals(collaborateur.getRqth());
//        boolean isAVieSelected = "a vie".equals(collaborateur.getRqth());
//       
//        //Ajoute les informations nécessaires en tant qu'attributs à l'objet HttpServletRequest pour qu'elles soient accessibles dans la JSP
//        req.setAttribute("isOuiSelected", isOuiSelected);
//        req.setAttribute("isAVieSelected", isAVieSelected);
//        
        
        
        
        req.setAttribute("ra", ra);
        req.setAttribute("selectedPartenaires", selectedPartenaires);
        req.setAttribute("partenaireList", partenaireList);
        req.setAttribute("selectedCollaborateurs", selectedCollaborateurs);
        req.setAttribute("collaborateurList", collaborateurList);
        

  //      Formate la date de renouvellement si le collaborateur a le RQTH et une date de renouvellement spécifiée.
//        if ("oui".equals(collaborateur.getRqth()) && collaborateur.getDate_de_renouvellement() != null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String formattedDate = collaborateur.getDate_de_renouvellement().format(formatter);
//            req.setAttribute("formattedDate", formattedDate);
//        }
//        
        
        
        
        //Transfère la requête et la réponse à la page JSP modifierCollaborateur.jsp pour afficher les informations du collaborateur et les responsables.
        req.getRequestDispatcher("/WEB-INF/jsp/modifierResponsableActivite.jsp").forward(req, resp);
    }
//}
//}
//        
//        //Détermine si les valeurs "oui" et "a vie" sont sélectionnées pour le RQTH
//        boolean isOuiSelected = "oui".equals(collaborateur.getRqth());
//        boolean isAVieSelected = "a vie".equals(collaborateur.getRqth());
//       
//        //Ajoute les informations nécessaires en tant qu'attributs à l'objet HttpServletRequest pour qu'elles soient accessibles dans la JSP
//        req.setAttribute("isOuiSelected", isOuiSelected);
//        req.setAttribute("isAVieSelected", isAVieSelected);
//        req.setAttribute("collaborateur", collaborateur);
//        req.setAttribute("selectedResponsables",selectedResponsables);
//        req.setAttribute("responsableActiviteList", responsableActiviteList);
//
//        //Formate la date de renouvellement si le collaborateur a le RQTH et une date de renouvellement spécifiée.
//        if ("oui".equals(collaborateur.getRqth()) && collaborateur.getDate_de_renouvellement() != null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String formattedDate = collaborateur.getDate_de_renouvellement().format(formatter);
//            req.setAttribute("formattedDate", formattedDate);
//        }
//        //Transfère la requête et la réponse à la page JSP modifierCollaborateur.jsp pour afficher les informations du collaborateur et les responsables.
//        req.getRequestDispatcher("/WEB-INF/jsp/modifierCollaborateur.jsp").forward(req, resp);
//    }
//     //Ce code configure et transfère les informations nécessaires pour modifier les détails d'un collaborateur 
//    //et afficher une liste de responsables d'activité dans une page JSP. Les étapes incluent la gestion des encodages, 
//    //la récupération des données de la base de données, la transformation des données, la gestion des erreurs, 
//    //et la préparation des données pour l'affichage.
//    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        ModifierResponsableActiviteFormChecker nv = new ModifierResponsableActiviteFormChecker(req);
        ResponsableActivite ra = nv.checkForm();

        
        PartenaireDao partenaireDao = new PartenaireDao();
        Collection<Partenaire> partenaireList = partenaireDao.list();
        Collection<Partenaire> listPartenaireRa = partenaireDao.listPartenaire(ra.getId());
        
        List<Integer> selectedPartenaires = new ArrayList<>();
        for (Partenaire partenaire : listPartenaireRa) {
            selectedPartenaires.add(partenaire.getId());
        }
        
         CollaborateurDao collaborateurDao = new CollaborateurDao();
        Collection<Collaborateur> collaborateurList = collaborateurDao.list();
        Collection<Collaborateur> listCollaborateurRa = collaborateurDao.listCollaborateur(ra.getId());

        List<Integer> selectedCollaborateurs = new ArrayList<>();
        for (Collaborateur collab : listCollaborateurRa) {
            selectedCollaborateurs.add(collab.getId());
        }
        
        if (nv.getErrors().isEmpty()) {
            
        
        ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
            ProposerDao proposerDao = new ProposerDao();
            PossederDao possederDao = new PossederDao();
            
            
             String[] PartenaireIds = req.getParameterValues("partenaire");
            List<Integer> partenaireIdList = new ArrayList<>();

       if (PartenaireIds != null) {
                for (String idStr : PartenaireIds) {
                    int id = Integer.parseInt(idStr);
                    partenaireIdList.add(id);
                }
            }

            ra.setPartenaireIds(partenaireIdList);
            responsableActiviteDao.update(ra);
            req.setAttribute("ra", ra);
            
             List<String> partenairesNoms = new ArrayList<>();
            for (int partenaireId : partenaireIdList) {
                Partenaire partenaire = partenaireDao.read(partenaireId);
                if (partenaire != null) {
                    partenairesNoms.add(partenaire.getNom());
                }
            }
            
            
             String[] CollaborateurIds = req.getParameterValues("collaborateur");
            List<Integer> collaborateurIdList = new ArrayList<>();

       if (CollaborateurIds != null) {
                for (String idStr : CollaborateurIds) {
                    int id = Integer.parseInt(idStr);
                    collaborateurIdList.add(id);
                }
            }

            ra.setCollaborateurIds(partenaireIdList);
            responsableActiviteDao.update(ra);
            req.setAttribute("ra", ra);
            
             List<String> collaborateurNoms = new ArrayList<>();
            for (int collaborateurId : collaborateurIdList) {
                Collaborateur collaborateur = collaborateurDao.read(collaborateurId);
                if (collaborateur != null) {
                    collaborateurNoms.add(collaborateur.getNom());
                }
            }
            
             String partenaire = String.join(", ", partenairesNoms);
            req.setAttribute("partenaire", partenaire);
            req.setAttribute("message", "Votre partenaire est bien modifié");
            
            String collaborateur = String.join(", ", collaborateurNoms);
            req.setAttribute("collaborateur",collaborateur);
            req.setAttribute("message", "Votre collaborateur est bien modifié");
            
            req.getRequestDispatcher("/WEB-INF/jsp/afficherResponsableActivite.jsp").forward(req, resp);
            
    } else {
    
     req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
            req.setAttribute("selectedPartenaires", selectedPartenaires);
            req.setAttribute("partenaireList", partenaireList);
            req.setAttribute("selectedCollaborateurs", selectedCollaborateurs);
            req.setAttribute("collaborateurList", collaborateurList);
            req.setAttribute("errors", nv.getErrors());
            
            req.setAttribute("ra", ra);
            req.getRequestDispatcher("/WEB-INF/jsp/modifierResponsableActivite.jsp").forward(req, resp);
        }
            
    }

}
       