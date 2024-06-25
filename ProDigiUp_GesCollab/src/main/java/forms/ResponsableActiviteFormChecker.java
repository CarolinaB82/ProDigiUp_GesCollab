package forms;

import dao.DaoFactory;
import entities.ResponsableActivite;
import jakarta.servlet.http.HttpServletRequest;


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
