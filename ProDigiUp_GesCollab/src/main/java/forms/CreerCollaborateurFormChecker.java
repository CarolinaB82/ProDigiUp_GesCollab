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
        String dateDeNaissanceStr = request.getParameter("date_de_naissance");
        String numero_voie = request.getParameter("numero_voie");
        String adresse = request.getParameter("adresse");
        String codePostalStr = request.getParameter("code_postal");
        String ville = request.getParameter("ville");
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

        try {
            Integer code_postal = Integer.parseInt(codePostalStr);
            obj.setCode_postal(code_postal);
        } catch (NumberFormatException e) {
            System.err.println("Erreur de conversion pour le champ code_postal : " + e.getMessage());
        }

        /* Convertir les champs Date
        String dateDeNaissanceStr = getParameter("date_de_naissance");
        Date date_de_naissance = null;
        if (dateDeNaissanceStr != null && !dateDeNaissanceStr.isEmpty()) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                date_de_naissance = formatter.parse(dateDeNaissanceStr);
                obj.setDate_de_naissance(date_de_naissance);
            } catch (ParseException e) {
                System.err.println("Erreur de conversion pour le champ date_de_naissance : " + e.getMessage());
            }
        } else {
            System.err.println("Le champ date_de_naissance est vide ou non valide.");
        }*/
 // Convertir les champs Date
        LocalDate date_de_naissance = null;
        if (dateDeNaissanceStr != null && !dateDeNaissanceStr.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date_de_naissance = LocalDate.parse(dateDeNaissanceStr, formatter);
                obj.setDate_de_naissance(date_de_naissance);
            } catch (DateTimeParseException e) {
                setError("date_de_naissance", "Erreur de conversion pour le champ date_de_naissance : " + e.getMessage());
            }
        } else {
            setError("date_de_naissance", "Le champ date_de_naissance est vide ou non valide.");
        }

        // Traiter les autres champs String
        obj.setNom(nom);
        obj.setPrenom(prenom);
        obj.setNumero_voie(numero_voie);
        obj.setAdresse(adresse);
        obj.setVille(ville);
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