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

        
        
        
        
        
        
        
        
        
        
        
        
        // Validation du code postal
        if (codePostalStr == null || codePostalStr.isEmpty()) {
            setError("code_postal", "Le code postal est trop long, 5 chiffres svp.");
        } else if (!codePostalStr.matches("\\d{1,5}")) {
            setError("code_postal", "Le code postal doit contenir 5 chiffres.");
        } else {
            try {
                Integer code_postal = Integer.parseInt(codePostalStr);
                obj.setCode_postal(code_postal);
            } catch (NumberFormatException e) {
                setError("code_postal", "Erreur de conversion pour le champ code_postal : " + e.getMessage());
            }
        }

        // Validation du numéro de téléphone personnel
        if (telephone_personnel == null || telephone_personnel.isEmpty()) {
            setError("telephone_personnel", "Le numéro de téléphone personnel est trop long, 10 chiffres svp.");
        } else if (!telephone_personnel.matches("\\d{1,10}")) {
            setError("telephone_personnel", "Le numéro de téléphone personnel doit contenir entre 1 et 10 chiffres.");
        } else {
            obj.setTelephone_personnel(telephone_personnel);
        }

        // Validation de la date de naissance
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
       /* 1ere Version    // Validation du code postal
        if (codePostalStr == null || codePostalStr.isEmpty()) {
            setError("code_postal", "Le code postal est requis.");
        } else if (!codePostalStr.matches("\\d{1,10}")) {
            setError("code_postal", "Le code postal doit contenir entre 1 et 10 chiffres.");
        } else {
        try {
            Integer code_postal = Integer.parseInt(codePostalStr);
            obj.setCode_postal(code_postal);
        } catch (NumberFormatException e) {
            System.err.println("Erreur de conversion pour le champ code_postal : " + e.getMessage());
        }

        /* 
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

        
        
        
        
        
        
        
          if (telephone_personnel == null || telephone_personnel.isEmpty()) {
            setError("telephone_personnel", "Le numéro de téléphone personnel est requis.");
        } else if (!telephone_personnel.matches("\\d{1,10}")) {
            setError("telephone_personnel", "Le numéro de téléphone personnel doit contenir entre 1 et 10 chiffres.");
        } else {
        obj.setTelephone_personnel(telephone_personnel);
        }*/
          
           // Traiter les autres champs String
        obj.setNom(nom);
        obj.setPrenom(prenom);
        obj.setNumero_voie(numero_voie);
        obj.setAdresse(adresse);
        obj.setVille(ville);
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