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
        Prestation prestation = new Prestation();

        String siglum_presta = request.getParameter("siglum_presta");
        String num_affaire = request.getParameter("num_affaire");
        String nom_presta = request.getParameter("nom_presta");
        String ref_fact_partenaire = request.getParameter("ref_fact_partenaire");
        String mail_partenaire = request.getParameter("mail_partenaire");
        String ref_fact_airbus = request.getParameter("ref_fact_airbus");
        String mail_airbus = request.getParameter("mail_airbus");
        String id_raStr = request.getParameter("id_ra");
        String id_collaborateurStr = request.getParameter("id_collaborateur");
        String id_partenaireStr = request.getParameter("id_partenaire");


        // Traiter les autres champs String
        prestation.setSiglum_presta(siglum_presta);
        prestation.setNum_affaire(num_affaire);
        prestation.setNom_presta(nom_presta);
        prestation.setRef_fact_partenaire(ref_fact_partenaire);
        prestation.setMail_partenaire(mail_partenaire);
        prestation.setRef_fact_airbus(ref_fact_airbus);
        prestation.setMail_airbus(mail_airbus);
        
         try {
            Integer id_ra = Integer.parseInt(id_raStr);
            prestation.setId_ra(id_ra);
        } catch (NumberFormatException e) {
            System.err.println("Erreur de conversion " + e.getMessage());
        } 
         try {
            Integer id_collaborateur = Integer.parseInt(id_collaborateurStr);
            prestation.setId_collaborateur(id_collaborateur);
        } catch (NumberFormatException e) {
            System.err.println("Erreur de conversion " + e.getMessage());
        } 
         try {
            Integer id_partenaire = Integer.parseInt(id_partenaireStr);
            prestation.setId_partenaire(id_partenaire);
        } catch (NumberFormatException e) {
            System.err.println("Erreur de conversion " + e.getMessage());
        }
        

        if (errors.isEmpty()) {
            //DaoFactory.getCollaborateurDao().save(obj);
        }
        request.setAttribute("bean", prestation);
        return prestation;
    }

}
