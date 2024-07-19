package forms;

import entities.ResponsableActivite;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Cette classe est responsable de la validation et du traitement des données
 * soumises depuis un formulaire de création ou de mise à jour d'un responsable
 * d'activité. Elle utilise les données récupérées à partir d'une requête HTTP
 * pour créer un objet ResponsableActivite avec les champs validés et convertis
 * au besoin.
 *
 * Elle étend la classe FormChecker générique pour le type ResponsableActivite.
 *
 * @author asolanas
 */
public class CreerResponsableActiviteFormChecker extends FormChecker<ResponsableActivite> {

    public CreerResponsableActiviteFormChecker(HttpServletRequest request) {
        super(request);
    }

    /**
     * Vérifie et traite les données soumises depuis un formulaire de création
     * ou de mise à jour d'un responsable d'activité. Les données sont
     * récupérées à partir de la requête HTTP.
     *
     * @return Un objet ResponsableActivite contenant les données validées du
     * formulaire.
     */
    @Override
    public ResponsableActivite checkForm() {

        ResponsableActivite ra = new ResponsableActivite();
        //ResponsableActivite responsableActivite = (ResponsableActivite) request.getSession().getAttribute("ra");

        String matriculestr = request.getParameter("matricule");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String mail = request.getParameter("mail");
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
        ra.setMail(mail);
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
