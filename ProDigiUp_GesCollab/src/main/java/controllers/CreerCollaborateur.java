/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import entities.Collaborateur;
import forms.CreerCollaborateurFormChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author cberge
 */

@WebServlet("/creer_collaborateur")
@SuppressWarnings("serial")
public class CreerCollaborateur extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher ("/WEB-INF/jsp/creerCollaborateur.jsp"). forward(req, resp);
    
    }

    
   @Override 
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        CreerCollaborateurFormChecker nv = new CreerCollaborateurFormChecker(req);
       Collaborateur collaborateur = nv.checkForm();
       
        
            if (nv.getErrors().isEmpty()){
                CollaborateurDao collaborateurDao = new CollaborateurDao();
                // Appel de la méthode create du DOA
                // Si une erreur dans l'insert alors une SQLException est levé
                // On l'intercepte dans le catch et on affiche un msg d'erreur à l'utilisateur
                try {
                    collaborateurDao.create(collaborateur);
                    Collaborateur collab = collaborateurDao.read(collaborateur.getId());
                    req.setAttribute("collaborateur", collab);
                    req.setAttribute("message", "Votre collaborateur est bien enregistré");
                    req.getRequestDispatcher("/WEB-INF/jsp/collaborateur.jsp").forward(req, resp);
                   
                } catch (SQLException ex) {
                    Logger.getLogger(CreerCollaborateur.class.getName()).log(Level.SEVERE, null, ex);
                    req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                    req.getRequestDispatcher("/WEB-INF/jsp/creerCollaborateur.jsp/").forward(req, resp);
                }
            }else{
                req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs");
                req.getRequestDispatcher("/WEB-INF/jsp/creerCollaborateur.jsp").forward(req, resp);
            }
    
}
}