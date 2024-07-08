package forms;

import dao.DaoFactory;
import entities.Prestation;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asolanas
 */
public class ModifierPrestationFormChecker extends FormChecker<Prestation>{
    
    private final String NEW_SIGLUM_PRESTA = "new_siglum_presta";
    private final String NEW_NUM_AFFAIRE = "new_num_affaire";
    private final String NEW_NOM_PRESTA = "new_nom_presta";
    private final String NEW_REF_FACT_PARTENAIRE = "new_ref_fact_partenaire";
    private final String NEW_MAIL_PARTENAIRE = "new_mail_partenaire";
    private final String NEW_REF_FACT_AIRBUS = "new_ref_fact_airbus";
    private final String NEW_MAIL_AIRBUS = "new_mail_airbus";
        

    public ModifierPrestationFormChecker(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Prestation checkForm() {
        String new_siglum_presta = getParameter(NEW_SIGLUM_PRESTA);
        String new_num_affaire = getParameter(NEW_NUM_AFFAIRE);
        String new_nom_presta = getParameter(NEW_NOM_PRESTA);
        String new_ref_fact_partenaire = getParameter(NEW_REF_FACT_PARTENAIRE);
        String new_mail_partenaire = getParameter(NEW_MAIL_PARTENAIRE);
        String new_ref_fact_airbus = getParameter(NEW_REF_FACT_AIRBUS);
        String new_mail_airbus = getParameter(NEW_MAIL_AIRBUS);
        
        Prestation prestation = (Prestation) request.getSession().getAttribute("prestation");
        if (!prestation.getSiglum_presta().equals(new_siglum_presta)){
            setError(NEW_SIGLUM_PRESTA, "Siglum inchangé");
        }
        if (!prestation.getNum_affaire().equals(new_num_affaire)){
            setError(NEW_NUM_AFFAIRE, "numéro affaire inchangé");
        }
        if (!prestation.getNom_presta().equals(new_nom_presta)){
            setError(NEW_NOM_PRESTA, "nom prestation inchangé");
        }
        if (!prestation.getRef_fact_partenaire().equals(new_ref_fact_partenaire)){
            setError(NEW_REF_FACT_PARTENAIRE, "référent partenaire inchangé");
        }
        if (!prestation.getMail_partenaire().equals(new_mail_partenaire)){
            setError(NEW_MAIL_PARTENAIRE, "mail partenaire inchangé");
        }
        if (!prestation.getRef_fact_airbus().equals(new_ref_fact_airbus)){
            setError(NEW_REF_FACT_AIRBUS, "référent airbus inchangé");
        }
        if (!prestation.getMail_airbus().equals(new_mail_airbus)){
            setError(NEW_MAIL_AIRBUS, "mail airbus inchangé");
        }
        if (errors.isEmpty()){
            prestation.setSiglum_presta(new_siglum_presta);
            prestation.setNum_affaire(new_num_affaire);
            prestation.setNom_presta(new_nom_presta);
            prestation.setRef_fact_partenaire(new_ref_fact_partenaire);
            prestation.setMail_partenaire(new_mail_partenaire);
            prestation.setRef_fact_airbus(new_ref_fact_airbus);
            prestation.setMail_airbus(new_mail_airbus);
            
            try {
                DaoFactory.getPrestationDao().save(prestation);
            } catch (SQLException ex) {
                Logger.getLogger(ModifierPrestationFormChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.setAttribute("errors", errors);
        request.setAttribute("bean", prestation);
        return prestation;
    }
    
     public static boolean isFormValid(Prestation prestation) {
        if (prestation.getSiglum_presta() == null || prestation.getSiglum_presta().isEmpty()) {
            return false;
        }
        if (prestation.getNum_affaire() == null || prestation.getNum_affaire().isEmpty()) {
            return false;
        }
        if (prestation.getNom_presta() == null || prestation.getNom_presta().isEmpty()) {
            return false;
        }
        if (prestation.getRef_fact_partenaire() == null || prestation.getRef_fact_partenaire().isEmpty()) {
            return false;
        }
        if (prestation.getMail_partenaire() == null || prestation.getMail_partenaire().isEmpty()) {
            return false;
        }
        if (prestation.getRef_fact_airbus() == null || prestation.getRef_fact_airbus().isEmpty()) {
            return false;
        }
        if (prestation.getMail_airbus() == null || prestation.getMail_airbus().isEmpty()) {
            return false;
        }
        if (prestation.getId_ra() <= 0) {
            return false;
        }
        if (prestation.getId_collaborateur() <= 0) {
            return false;
        }
        if (prestation.getId_partenaire() <= 0) {
            return false;
        }
        return true;
    }
}
    

