package forms;

import entities.Collaborateur;
import jakarta.servlet.http.HttpServletRequest;
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
        String mail_1 = request.getParameter("mail_1");
        String mail_2 = request.getParameter("mail_2");
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

        String telephonePersonnel = request.getParameter("telephone_personnel");
        if (telephonePersonnel != null && !telephonePersonnel.isEmpty()) {
            String telephonePattern = "\\+?[0-9\\(\\)\\- ]{1,30}";
            if (!telephonePersonnel.matches(telephonePattern)) {
                setError("telephone_personnel", "Le format du téléphone est incorrect.");
            }
        }
        
        // Traiter les autres champs String
        obj.setNom(nom);
        obj.setPrenom(prenom);
        obj.setMail_1(mail_1);
        obj.setMail_2(mail_2);
        obj.setTelephone_personnel(telephone_personnel);
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
