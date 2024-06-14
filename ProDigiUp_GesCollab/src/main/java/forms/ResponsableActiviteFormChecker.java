package forms;

import dao.DaoFactory;
import entities.ResponsableActivite;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author asolanas
 */
public class ResponsableActiviteFormChecker extends FormChecker<ResponsableActivite> {

    public ResponsableActiviteFormChecker(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ResponsableActivite checkForm() {

        ResponsableActivite ra = new ResponsableActivite();
        //ResponsableActivite responsableActivite = (ResponsableActivite) request.getSession().getAttribute("ra");

        String matriculestr = request.getParameter("matricule");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String date_naissance = request.getParameter("date_de_naissance");
        String num_voie = request.getParameter("num_voie");
        String adresse = request.getParameter("adresse");
        String code_postalStr = request.getParameter("code_postal");
        String ville = request.getParameter("ville");
        String telephone_professionnel = request.getParameter("telephone_professionnel");
        String telephone_personnel = request.getParameter("telephone_personnel");
        
        try{
            Integer matricule = Integer.parseInt(matriculestr);
            ra.setMatricule(matricule);
        }catch (NumberFormatException e){
            System.err.println("Erreur de conversion " + e.getMessage());
        }
        
        
        
        
// Validation du code postal
        if (code_postalStr == null || code_postalStr.isEmpty()) {
            setError("code_postal", "Le code postal est trop long, 5 chiffres svp.");
        } else if (!code_postalStr.matches("\\d{1,10}")) {
            setError("code_postal", "Le code postal doit contenir 5 chiffres.");
        } else {
            try {
                Integer code_postal = Integer.parseInt(code_postalStr);
                ra.setCode_postal(code_postal);
            } catch (NumberFormatException e) {
                setError("code_postal", "Erreur de conversion pour le champ code_postal : " + e.getMessage());
            }
        }
        
        
         // Validation du numéro de téléphone personnel
        if (telephone_professionnel == null || telephone_professionnel.isEmpty()) {
            setError("telephone_professionnel", "Le numéro de téléphone professionnel est trop long, 10 chiffres svp.");
        } else if (!telephone_personnel.matches("\\d{1,10}")) {
            setError("telephone_professionnel", "Le numéro de téléphone professionnel doit contenir entre 1 et 10 chiffres.");
        } else {
            ra.setTelephone_professionnel(telephone_professionnel);
        }

        // Validation du numéro de téléphone personnel
        if (telephone_personnel == null || telephone_personnel.isEmpty()) {
            setError("telephone_personnel", "Le numéro de téléphone personnel est trop long, 10 chiffres svp.");
        } else if (!telephone_personnel.matches("\\d{1,10}")) {
            setError("telephone_personnel", "Le numéro de téléphone personnel doit contenir entre 1 et 10 chiffres.");
        } else {
            ra.setTelephone_personnel(telephone_personnel);
        }
        
        ra.setNom(nom);
        ra.setPrenom(prenom);
        
        LocalDate date_de_naissance = null;
        if (date_naissance != null && !date_naissance.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adapter le format au besoin
                date_de_naissance = LocalDate.parse(date_naissance, formatter);
                ra.setDate_de_naissance(date_de_naissance);
            } catch (DateTimeParseException e) {
                // Gérer l'erreur de format de date
                setError("date_de_naissance", "Le format de la date de naissance est incorrect.");
            }
        }
        ra.setNumero_voie(num_voie);
        ra.setAdresse(adresse);
         try {
            Integer code_postal = Integer.parseInt(code_postalStr);
            ra.setCode_postal(code_postal);
        } catch (NumberFormatException e) {
            System.err.println("Erreur de conversion pour le champ code_postal : " + e.getMessage());
        }

        ra.setVille(ville);
        ra.setTelephone_professionnel(telephone_professionnel);
        ra.setTelephone_personnel(telephone_personnel);

        if (nom == null || nom.trim().length() == 0) {
            setError(nom, "Ce champs doit être rempli!");
        }

        request.setAttribute("errors", errors);
        request.setAttribute("bean", ra);
        return ra;
    }
}
