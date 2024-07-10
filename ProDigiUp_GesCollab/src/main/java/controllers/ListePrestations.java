/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.DaoFactory;
import entities.Prestation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

/**
 *
 * @author cberge
 */
@WebServlet("/liste_prestations")
@SuppressWarnings("serial")
public class ListePrestations extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        Collection<Prestation> prestations = DaoFactory.getPrestationDao().list();

        req.setAttribute("prestations", prestations);

        req.getRequestDispatcher("/WEB-INF/jsp/listePrestations.jsp").forward(req, resp);
    }

}
