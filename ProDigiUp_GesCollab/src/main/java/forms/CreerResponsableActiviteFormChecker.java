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
public class CreerResponsableActiviteFormChecker extends FormChecker<ResponsableActivite> {

    public CreerResponsableActiviteFormChecker(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ResponsableActivite checkForm() {

        ResponsableActivite ra = new ResponsableActivite();
        //ResponsableActivite responsableActivite = (ResponsableActivite) request.getSession().getAttribute("ra");

        String matriculestr = request.getParameter("matricule");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String telephone_professionnel = request.getParameter("telephone_professionnel");
        String telephone_personnel = request.getParameter("telephone_personnel");

        try {
            Integer matricule = Integer.parseInt(matriculestr);
            ra.setMatricule(matricule);
        } catch (NumberFormatException e) {
            System.err.println("Erreur de conversion " + e.getMessage());
        }
        ra.setNom(nom);
        ra.setPrenom(prenom);
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
