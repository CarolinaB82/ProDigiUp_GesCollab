package forms;

import entities.Prestation;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author asolanas
 */


public class ModifierPrestationFormChecker {
    private Map<String, String> errors = new HashMap<>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public Prestation checkForm(HttpServletRequest request) {
        String siglum_presta = getFieldValue(request, "siglum_presta");
        String num_affaire = getFieldValue(request, "num_affaire");
        String nom_presta = getFieldValue(request, "nom_presta");
        String ref_fact_partenaire = getFieldValue(request, "ref_fact_partenaire");
        String mail_partenaire = getFieldValue(request, "mail_partenaire");
        String ref_fact_airbus = getFieldValue(request, "ref_fact_airbus");
        String mail_airbus = getFieldValue(request, "mail_airbus");
        int id_ra = getIntFieldValue(request, "id_ra");
        int id_collaborateur = getIntFieldValue(request, "id_collaborateur");
        int id_partenaire = getIntFieldValue(request, "id_partenaire");

        Prestation prestation = new Prestation();

        try {
            validateSiglumPresta(siglum_presta);
            prestation.setSiglum_presta(siglum_presta);
        } catch (Exception e) {
            setError("siglum_presta", e.getMessage());
        }

        try {
            validateNumAffaire(num_affaire);
            prestation.setNum_affaire(num_affaire);
        } catch (Exception e) {
            setError("num_affaire", e.getMessage());
        }

        try {
            validateNomPresta(nom_presta);
            prestation.setNom_presta(nom_presta);
        } catch (Exception e) {
            setError("nom_presta", e.getMessage());
        }

        prestation.setRef_fact_partenaire(ref_fact_partenaire);
        prestation.setMail_partenaire(mail_partenaire);
        prestation.setRef_fact_airbus(ref_fact_airbus);
        prestation.setMail_airbus(mail_airbus);
        prestation.setId_ra(id_ra);
        prestation.setId_collaborateur(id_collaborateur);
        prestation.setId_partenaire(id_partenaire);

        return prestation;
    }

    private void validateSiglumPresta(String siglum_presta) throws Exception {
        if (siglum_presta == null || siglum_presta.trim().isEmpty()) {
            throw new Exception("Le siglum de la prestation est obligatoire.");
        }
    }

    private void validateNumAffaire(String num_affaire) throws Exception {
        if (num_affaire == null || num_affaire.trim().isEmpty()) {
            throw new Exception("Le numéro d'affaire est obligatoire.");
        }
    }

    private void validateNomPresta(String nom_presta) throws Exception {
        if (nom_presta == null || nom_presta.trim().isEmpty()) {
            throw new Exception("Le nom de la prestation est obligatoire.");
        }
    }

    private void setError(String field, String message) {
        errors.put(field, message);
    }

    private String getFieldValue(HttpServletRequest request, String fieldName) {
        String value = request.getParameter(fieldName);
        return (value == null || value.trim().isEmpty()) ? null : value.trim();
    }

    private int getIntFieldValue(HttpServletRequest request, String fieldName) {
        String value = getFieldValue(request, fieldName);
        try {
            return (value != null) ? Integer.parseInt(value) : 0;
        } catch (NumberFormatException e) {
            setError(fieldName, "La valeur doit être un nombre.");
            return 0;
        }
    }
}

    

