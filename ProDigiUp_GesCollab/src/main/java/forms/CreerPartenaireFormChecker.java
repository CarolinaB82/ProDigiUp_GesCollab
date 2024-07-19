/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package forms;

import entities.Partenaire;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Cette classe est responsable de la validation et du traitement des données
 * soumises depuis un formulaire de création ou de mise à jour d'un partenaire.
 * Elle utilise les données récupérées à partir d'une requête HTTP pour créer un
 * objet Partenaire avec les champs validés et convertis au besoin.
 *
 * Elle étend la classe FormChecker générique pour le type Partenaire.
 *
 *
 * @author cberge
 */
public class CreerPartenaireFormChecker extends FormChecker<Partenaire> {

    public CreerPartenaireFormChecker(HttpServletRequest request) {
        super(request);
    }

    /**
     * Vérifie et traite les données soumises depuis un formulaire de création
     * ou de mise à jour d'un partenaire. Les données sont récupérées à partir
     * de la requête HTTP.
     *
     * @return Un objet Partenaire contenant les données validées du formulaire.
     */
    @Override
    public Partenaire checkForm() {
        Partenaire obj = new Partenaire();

        String nom = request.getParameter("nom");
        String numero_voieStr = request.getParameter("numero_voie");
        String adresse = request.getParameter("adresse");
        String code_postalStr = request.getParameter("code_postal");
        String ville = request.getParameter("ville");

        // Traiter les autres champs String
        obj.setNom(nom);

        try {
            Integer numero_voie = Integer.parseInt(numero_voieStr);
            obj.setNumero_voie(numero_voie);
        } catch (NumberFormatException e) {
            setError("matricule", "Le matricule ne peut pas contenir de caractères alphanumériques");

        }
        obj.setAdresse(adresse);

        try {
            Integer code_postal = Integer.parseInt(code_postalStr);
            obj.setCode_postal(code_postal);
        } catch (NumberFormatException e) {
            setError("matricule", "Le matricule ne peut pas contenir de caractères alphanumériques");

        }

        obj.setVille(ville);

        if (errors.isEmpty()) {
            //DaoFactory.getCollaborateurDao().save(obj);
        }
        request.setAttribute("bean", obj);
        return obj;
    }
}
