/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

package forms;

import entities.Prestation;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author asolanas
 */
public class ModifierPrestationFormChecker {
    private HttpServletRequest request;
    private Map<String, String> errors = new HashMap<>();

    public ModifierPrestationFormChecker(HttpServletRequest request) {
        this.request = request;
    }

    public Prestation checkForm() {
        Prestation obj = new Prestation();
        String idStr = request.getParameter("id");
        int id = parseIntWithDefault(idStr, 0);

        String siglum_presta = request.getParameter("siglum_presta");
        String num_affaire = request.getParameter("num_affaire");
        String nom_presta = request.getParameter("nom_presta");
        String ref_fact_partenaire = request.getParameter("ref_fact_partenaire");
        String mail_partenaire = request.getParameter("mail_partenaire");
        String ref_fact_airbus = request.getParameter("ref_fact_airbus");
        String mail_airbus = request.getParameter("mail_airbus");
        String idRaStr = request.getParameter("id_ra");
        String idCollaborateurStr = request.getParameter("id_collaborateur");
        String idPartenaireStr = request.getParameter("id_partenaire");

        // Convertir les chaînes de caractères en entiers
        int idRa = parseIntWithDefault(idRaStr, 0);
        int idCollaborateur = parseIntWithDefault(idCollaborateurStr, 0);
        int idPartenaire = parseIntWithDefault(idPartenaireStr, 0);

        obj.setId(id);
        obj.setSiglum_presta(siglum_presta);
        obj.setNum_affaire(num_affaire);
        obj.setNom_presta(nom_presta);
        obj.setRef_fact_partenaire(ref_fact_partenaire);
        obj.setMail_partenaire(mail_partenaire);
        obj.setRef_fact_airbus(ref_fact_airbus);
        obj.setMail_airbus(mail_airbus);
        obj.setId_ra(idRa);
        obj.setId_collaborateur(idCollaborateur);
        obj.setId_partenaire(idPartenaire);

        if (errors.isEmpty()) {
            request.setAttribute("prestation", obj);
        } else {
            request.setAttribute("errors", errors);
        }

        return obj;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    private int parseIntWithDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
