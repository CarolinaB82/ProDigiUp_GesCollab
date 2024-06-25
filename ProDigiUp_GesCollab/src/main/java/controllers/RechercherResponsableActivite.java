/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import entities.Collaborateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author asolanas
 */
@WebServlet("/rechercher_ra")
public class RechercherResponsableActivite extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String matricule = req.getParameter("matricule");
        List<Collaborateur> resultats = new ArrayList<>();
        String erreur = null;

        try {
            CollaborateurDao collaborateurDao = new CollaborateurDao();

            if (nom != null && !nom.isEmpty()) {
                resultats = collaborateurDao.rechercherParNom(nom);
            } else if (prenom != null && !prenom.isEmpty()) {
                resultats = collaborateurDao.rechercherParPrenom(prenom);
            } else if (matricule != null && !matricule.isEmpty()) {
                if (matricule.matches("\\d+")) {
                    resultats = collaborateurDao.rechercherParMatricule(matricule);
                } else {
                    erreur = "Le matricule doit contenir uniquement des chiffres.";
                }
            } else {
                erreur = "Veuillez entrer un nom, prénom ou matricule.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            erreur = "Erreur lors de la recherche.";
        }

        req.setAttribute("resultats", resultats);
        req.setAttribute("erreur", erreur);
        req.getRequestDispatcher("/WEB-INF/jsp/responsableActivite.jsp").forward(req, resp);
    }
}
