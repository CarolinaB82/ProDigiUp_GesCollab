/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import dao.PossederDao;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.Posseder;
import entities.ResponsableActivite;
import forms.CreerCollaborateurFormChecker;
import forms.ModifierCollaborateurFormChecker;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cberge
 */
@WebServlet("/modifier_collaborateur")
@SuppressWarnings("serial")

public class ModifierCollaborateur extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        //Récupère l'ID du collaborateur à partir des paramètres de la requête et le convertit en entier.
        int collaborateurId = Integer.parseInt(req.getParameter("id"));
        

        //Utilise le DAO (Data Access Object) pour lire les informations du collaborateur à partir de la base de données.
        CollaborateurDao collaborateurDao = new CollaborateurDao();
        Collaborateur collaborateur = collaborateurDao.read(collaborateurId);
        
         // Simuler la récupération de la liste des responsables
         //Utilise le DAO pour récupérer toutes les activités responsables et celles associées au collaborateur.
        ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        Collection<ResponsableActivite> responsableActiviteList = responsableActiviteDao.list();
        Collection<ResponsableActivite> listResponsableActiviteCollaborateur = responsableActiviteDao.listResponsableActivite(collaborateur.getId());
    
        //Crée une liste d'IDs des responsables à partir de la collection des responsables associés au collaborateur
        List<Integer> selectedResponsables = new ArrayList<>();
        // Parcourir la collection et ajouter les ID des responsables à la liste
        for (ResponsableActivite responsable : listResponsableActiviteCollaborateur) {
            selectedResponsables.add(responsable.getId());
        }
        //Redirige vers une page d'erreur si le collaborateur n'est pas trouvé dans la base de données
        if (collaborateur == null) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
            return;
        }
        //Détermine si les valeurs "oui" et "a vie" sont sélectionnées pour le RQTH
        boolean isOuiSelected = "oui".equals(collaborateur.getRqth());
        boolean isAVieSelected = "a vie".equals(collaborateur.getRqth());
       
        //Ajoute les informations nécessaires en tant qu'attributs à l'objet HttpServletRequest pour qu'elles soient accessibles dans la JSP
        req.setAttribute("isOuiSelected", isOuiSelected);
        req.setAttribute("isAVieSelected", isAVieSelected);
        req.setAttribute("collaborateur", collaborateur);
        req.setAttribute("selectedResponsables",selectedResponsables);
        req.setAttribute("responsableActiviteList", responsableActiviteList);

        //Formate la date de renouvellement si le collaborateur a le RQTH et une date de renouvellement spécifiée.
        if ("oui".equals(collaborateur.getRqth()) && collaborateur.getDate_de_renouvellement() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = collaborateur.getDate_de_renouvellement().format(formatter);
            req.setAttribute("formattedDate", formattedDate);
        }
        //Transfère la requête et la réponse à la page JSP modifierCollaborateur.jsp pour afficher les informations du collaborateur et les responsables.
        req.getRequestDispatcher("/WEB-INF/jsp/modifierCollaborateur.jsp").forward(req, resp);
    }
     //Ce code configure et transfère les informations nécessaires pour modifier les détails d'un collaborateur 
    //et afficher une liste de responsables d'activité dans une page JSP. Les étapes incluent la gestion des encodages, 
    //la récupération des données de la base de données, la transformation des données, la gestion des erreurs, 
    //et la préparation des données pour l'affichage.
    


@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
         //Crée une instance de ModifierCollaborateurFormChecker pour valider les données du formulaire et récupérer un objet Collaborateur avec les données soumises.
        ModifierCollaborateurFormChecker nv = new ModifierCollaborateurFormChecker(req);
        Collaborateur collaborateur = nv.checkForm();
        
        //Utilise le DAO pour récupérer toutes les activités responsables et celles associées au collaborateur.
         ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
        Collection<ResponsableActivite> responsableActiviteList = responsableActiviteDao.list();
        Collection<ResponsableActivite> listResponsableActiviteCollaborateur = responsableActiviteDao.listResponsableActivite(collaborateur.getId());

        
        
        //Crée une liste d'IDs des responsables à partir de la collection des responsables associés au collaborateur.
        List<Integer> selectedResponsables = new ArrayList<>();
        // Parcourir la collection et ajouter les ID des responsables à la liste
        for (ResponsableActivite responsable : listResponsableActiviteCollaborateur) {
            selectedResponsables.add(responsable.getId());
        }
        //Vérifie si le champ RQTH est "oui" et si la date de renouvellement est manquante, ajoute une erreur si nécessaire.
        boolean isOuiSelected = "oui".equals(collaborateur.getRqth());
        boolean isAVieSelected = "a vie".equals(collaborateur.getRqth());
        if (isOuiSelected && collaborateur.getDate_de_renouvellement() == null) {
                nv.addError("date_de_renouvellement", "Date de renouvellement invalide.");
        }
        //Si le formulaire ne contient pas d'erreurs, continue le traitement ; sinon, retourne à la page du formulaire avec les erreurs.
         if (nv.getErrors().isEmpty()) {
             
             
            CollaborateurDao collaborateurDao = new CollaborateurDao();
            PossederDao possederDao = new PossederDao();
            try {
                
             //Vérification de l'unicité du matricule : Vérifie si le matricule existe déjà pour un autre collaborateur. 
             //Si oui, ajoute une erreur et retourne à la page du formulaire.
             //Conversion et stockage des IDs des responsables : Convertit les IDs des responsables en entier et les stocke dans le collaborateur.
             //Mise à jour du collaborateur : Met à jour le collaborateur dans la base de données.
            //Préparation des attributs pour la vue : Prépare les attributs pour la vue et redirige vers la page de succès.
                if (collaborateurDao.existsForOtherCollab(collaborateur.getMatricule(), collaborateur.getId())) {
                    nv.addError("matricule", "Le matricule existe déjà.");
                    req.setAttribute("collaborateur", collaborateur);
                    req.setAttribute("responsableActiviteList", responsableActiviteList);
                    req.setAttribute("selectedResponsables",selectedResponsables);
                    req.setAttribute("isOuiSelected", isOuiSelected);
                    req.setAttribute("isAVieSelected", isAVieSelected);
                    if (collaborateur.getDate_de_renouvellement() != null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String formattedDate = collaborateur.getDate_de_renouvellement().format(formatter);
                        req.setAttribute("formattedDate", formattedDate);
                    }
                    req.setAttribute("errors", nv.getErrors());
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/modifierCollaborateur.jsp").forward(req, resp);
                    return;
                }
                
                
                String[] responsableActiviteIds = req.getParameterValues("responsable");
                
                // Initialiser une liste d'entiers pour stocker les IDs convertis
                List<Integer> responsableActiviteIdList = new ArrayList<>();

                // Parcourir le tableau de chaînes de caractères et convertir chaque élément en entier
                if (responsableActiviteIds != null) {
                    for (String idStr : responsableActiviteIds) {
                        int id = Integer.parseInt(idStr);
                        responsableActiviteIdList.add(id);
                    }
                }
                
                collaborateur.setResponsableIds(responsableActiviteIdList);
                collaborateurDao.update(collaborateur);
                req.setAttribute("collaborateur", collaborateur);
                
                req.setAttribute("isOuiSelected", isOuiSelected);
                req.setAttribute("isAVieSelected", isAVieSelected);
                if (collaborateur.getDate_de_renouvellement() != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String formattedDate = collaborateur.getDate_de_renouvellement().format(formatter);
                    req.setAttribute("formattedDate", formattedDate);
                }
                
                List<String> responsableNoms = new ArrayList<>();
                for(int responsableId : responsableActiviteIdList){
                    ResponsableActivite responsable = responsableActiviteDao.read(responsableId);
                    if(responsable != null) {
                        responsableNoms.add(responsable.getNom());
                    }
                }
        
                String responsablesActivite = String.join(", ", responsableNoms);
                req.setAttribute("responsablesActivite", responsablesActivite);
                req.setAttribute("message", "Votre collaborateur a bien été modifié");
                req.getRequestDispatcher("/WEB-INF/jsp/afficherCollaborateur.jsp").forward(req, resp);

            } catch (SQLException ex) {                
                    Logger.getLogger(ModifierCollaborateur.class.getName()).log(Level.SEVERE, null, ex);
                    req.setAttribute("errorMsg", "Erreur lors de la modification du collaborateur");
                    req.getRequestDispatcher("/WEB-INF/jsp/modifierCollaborateur.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
            req.setAttribute("selectedResponsables",selectedResponsables);
            req.setAttribute("responsableActiviteList", responsableActiviteList);
            req.setAttribute("isOuiSelected", isOuiSelected);
            req.setAttribute("isAVieSelected", isAVieSelected);
            req.setAttribute("errors", nv.getErrors());
            req.setAttribute("collaborateur", collaborateur);
            req.getRequestDispatcher("/WEB-INF/jsp/modifierCollaborateur.jsp").forward(req, resp);
        }
    }
    
    

}
