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
        String tel_pro = request.getParameter("tel_pro");
        String tel_perso = request.getParameter("tel_perso");
        
        try{
            Integer matricule = Integer.parseInt(matriculestr);
            ra.setMatricule(matricule);
        }catch (NumberFormatException e){
            System.err.println("Erreur de conversion " + e.getMessage());
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
        ra.setTelephone_professionnel(tel_pro);
        ra.setTelephone_personnel(tel_perso);

        if (nom == null || nom.trim().length() == 0) {
            setError(nom, "Ce champs doit être rempli!");
        }

        request.setAttribute("errors", errors);
        request.setAttribute("bean", ra);
        return ra;
    }
}
