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
        String metier = request.getParameter("metier");

        // Convertir les champs Integer
        try {
            Integer matricule = Integer.parseInt(matriculeStr);
            obj.setMatricule(matricule);
        } catch (NumberFormatException e) {
            System.err.println("Erreur de conversion pour le champ matricule : " + e.getMessage());
        }

        // Traiter les autres champs String
        obj.setNom(nom);
        obj.setPrenom(prenom);
        obj.setTelephone_personnel(telephone_personnel);
        obj.setStatut(statut);
        obj.setCategorie(categorie);
        obj.setGenre(genre);
        obj.setRqth(rqth);
        obj.setMetier(metier);

        if(errors.isEmpty()){
            //DaoFactory.getCollaborateurDao().save(obj);
        }
        request.setAttribute("bean", obj);
        return obj;
    }
}