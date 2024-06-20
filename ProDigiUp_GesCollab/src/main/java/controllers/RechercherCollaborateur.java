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
@WebServlet("/rechercher")
public class RechercherCollaborateur extends HttpServlet {

    private final CollaborateurDao collaborateurDao = new CollaborateurDao();

        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recherche = req.getParameter("recherche");
        System.out.println("Recherche: " + recherche); // Debug message
        List<Collaborateur> collaborateurs = new ArrayList<>();
        try {
            collaborateurs = collaborateurDao.rechercher(recherche);
            System.out.println("Résultats trouvés: " + collaborateurs.size()); // Debug message
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("collaborateurs", collaborateurs);
        req.getRequestDispatcher("/WEB-INF/jsp/resultatRechercheCollaborateur.jsp").forward(req, resp);
    }
}


