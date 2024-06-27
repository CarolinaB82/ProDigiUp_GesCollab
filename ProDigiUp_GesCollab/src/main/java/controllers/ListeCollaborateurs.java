/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.CollaborateurDao;
import dao.DaoFactory;
import entities.Collaborateur;
import entities.CollaborateurPrestationPartenaireRa;
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
 *
 * @author cberge
 */
@WebServlet("/liste_collaborateurs")
@SuppressWarnings("serial")
public class ListeCollaborateurs extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        Collection<CollaborateurPrestationPartenaireRa> collaborateurs = DaoFactory.getCollaborateurDao().listCollaborateurPrestationPartenaireRa();

        req.setAttribute("collaborateurs", collaborateurs);

        req.getRequestDispatcher("/WEB-INF/jsp/listeCollaborateurs.jsp").forward(req, resp);
    }

}
