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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author cberge
 */
@WebServlet("/liste_prestations")
public class ListePrestations extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        String sort = req.getParameter("sort");
        String order = req.getParameter("order");

        Collection<Prestation> prestations = DaoFactory.getPrestationDao().list();
        List<Prestation> sortedPrestations = new ArrayList<>(prestations);

        if ("nom_presta".equals(sort)) {
            Comparator<Prestation> comparator = Comparator.comparing(Prestation::getNom_presta);

            if ("desc".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }

            sortedPrestations = sortedPrestations.stream().sorted(comparator).collect(Collectors.toList());
        }

        req.setAttribute("prestations", sortedPrestations);
        req.getRequestDispatcher("/WEB-INF/jsp/listePrestations.jsp").forward(req, resp);
    }
}

