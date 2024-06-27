/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package forms;

import entities.Prestation;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author cberge
 */
public class CreerPrestationFormChecker extends FormChecker<Prestation> {

    public CreerPrestationFormChecker(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Prestation checkForm() {
        Prestation obj = new Prestation();

        String siglum_presta = request.getParameter("siglum_presta");
        String nom_presta = request.getParameter("nom_presta");
        String ref_fact_partenaire = request.getParameter("ref_fact_partenaire");
        String ref_fact_airbus = request.getParameter("ref_fact_airbus");

        // Traiter les autres champs String
        obj.setSiglum_presta(siglum_presta);
        obj.setNom_presta(nom_presta);
        obj.setRef_fact_partenaire(ref_fact_partenaire);
        obj.setRef_fact_airbus(ref_fact_airbus);

        if (errors.isEmpty()) {
            //DaoFactory.getCollaborateurDao().save(obj);
        }
        request.setAttribute("bean", obj);
        return obj;
    }

}
