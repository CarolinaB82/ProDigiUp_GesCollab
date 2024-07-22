/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import dao.DaoFactory;
import entities.PartRa;
import entities.Partenaire;
import entities.ResponsableActivite;
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
@WebServlet("/liste_partenaires")
@SuppressWarnings("serial")
public class ListePartenaires extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        String sort = req.getParameter("sort");
        String order = req.getParameter("order");

       // Récupérer tous les partenaires
        Collection<Partenaire> partenaires = DaoFactory.getPartenaireDao().list();

        // Préparer la liste de PartRa
        List<PartRa> partRaList = new ArrayList<>();
        
        Comparator<PartRa> comparator = Comparator.comparing(partRa -> partRa.getPartenaire().getNom());
        
        for (Partenaire partenaire : partenaires) {
            // Récupérer les responsables d'activités pour ce partenaire
            Collection<ResponsableActivite> responsaActivites = DaoFactory.ResponsableActiviteDao().listResponsablesActivite(partenaire.getId());
            // Créer une liste de noms de responsables d'activités
            StringBuilder nomRaBuilder = new StringBuilder();
            for (ResponsableActivite ra : responsaActivites) {
                if (nomRaBuilder.length() > 0) {
                    nomRaBuilder.append(", ");
                }
                nomRaBuilder.append(ra.getNom());
            }
            // Créer un objet PartRa
            PartRa partRa = new PartRa();
            partRa.setPartenaire(partenaire);
            partRa.setNomRa(nomRaBuilder.toString());
            // Ajouter à la liste
            partRaList.add(partRa);
        }

        // Tri des partenaires
        
        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        List<PartRa> sortedPartRaList = partRaList.stream().sorted(comparator).collect(Collectors.toList());

        req.setAttribute("partenaires", sortedPartRaList);
        req.getRequestDispatcher("/WEB-INF/jsp/listePartenaires.jsp").forward(req, resp);
    }
 }

