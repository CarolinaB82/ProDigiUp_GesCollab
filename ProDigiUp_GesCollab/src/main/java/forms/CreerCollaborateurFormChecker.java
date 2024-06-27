package forms;

import dao.DaoFactory;
import entities.Collaborateur;
import forms.FormChecker;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CreerCollaborateurFormChecker extends FormChecker<Collaborateur> {

    public CreerCollaborateurFormChecker(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Collaborateur checkForm() {
        Collaborateur obj = new Collaborateur();

        String matriculeStr = request.getParameter("matricule");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String telephone_personnel = request.getParameter("telephone_personnel");
        String statut = request.getParameter("statut");
        String categorie = request.getParameter("categorie");
        String genre = request.getParameter("genre");
        String rqth = request.getParameter("rqth");
        String dateDeRenouvellement = request.getParameter("date_de_renouvellement");

        String metier = request.getParameter("metier");

        // Convertir les champs Integer
        try {
            Integer matricule = Integer.parseInt(matriculeStr);
            obj.setMatricule(matricule);
        } catch (NumberFormatException e) {
            setError("matricule", "Le matricule ne peut pas contenir de caractères alphanumériques");

        }
        // Convertir les champs Date
        LocalDate date_de_renouvellement = null;
        if (dateDeRenouvellement != null && !dateDeRenouvellement.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date_de_renouvellement = LocalDate.parse(dateDeRenouvellement, formatter);
                obj.setDate_de_renouvellement(date_de_renouvellement);
            } catch (DateTimeParseException e) {
                setError("date_de_renouvellement", "Erreur de conversion pour le champ date_de_renouvellement : " + e.getMessage());
            }
        }
        /*else {
            setError("date_de_renouvellement", "Le champ date_de_renouvellement est vide ou non valide.");
        }*/

        obj.setTelephone_personnel(telephone_personnel);
        if (telephone_personnel == null || telephone_personnel.isEmpty()) {
            setError("telephone_personnel", "Le numéro de téléphone personnel est requis.");
        } else if (!telephone_personnel.matches("\\d{10}")) {
            setError("telephone_personnel", "Le numéro de téléphone personnel doit contenir entre 1 et 10 chiffres.");
        }
        // Traiter les autres champs String
        obj.setNom(nom);
        obj.setPrenom(prenom);
        obj.setStatut(statut);
        obj.setCategorie(categorie);
        obj.setGenre(genre);
        obj.setRqth(rqth);

        obj.setMetier(metier);

        if (errors.isEmpty()) {
            //DaoFactory.getCollaborateurDao().save(obj);
        }
        request.setAttribute("bean", obj);
        return obj;
    }
}
