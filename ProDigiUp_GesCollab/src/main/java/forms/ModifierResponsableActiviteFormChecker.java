/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package forms;

import entities.ResponsableActivite;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Cette classe est utilisée pour valider et traiter les données soumises depuis
 * un formulaire de modification d'un responsable d'activité. Elle encapsule la
 * logique de validation des champs et la création d'un objet
 * ResponsableActivite avec les données validées.
 *
 * @author cberge
 */
public class ModifierResponsableActiviteFormChecker extends FormChecker<ResponsableActivite> {

    /**
     * Constructeur de la classe ModifierResponsableActiviteFormChecker.
     * Initialise l'objet avec la requête HTTP à partir de laquelle les données
     * du formulaire seront récupérées.
     *
     * @param request La requête HTTP contenant les données du formulaire à
     * valider.
     */
    public ModifierResponsableActiviteFormChecker(HttpServletRequest request) {
        super(request);
    }

    /**
     * Méthode principale pour valider et traiter les données soumises depuis le
     * formulaire de modification d'un responsable d'activité.
     *
     * @return Un objet ResponsableActivite contenant les données validées du
     * formulaire.
     */
    @Override
    public ResponsableActivite checkForm() {
        ResponsableActivite obj = new ResponsableActivite();

        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr); // on vérifie que id est bien une valeur numérique
        String matriculeStr = request.getParameter("matricule");
        int matricule = Integer.parseInt(matriculeStr);
        
        // on récupère les valeurs des champs modifiables
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String mail = request.getParameter("mail");
        String telephone_professionnel = request.getParameter("telephone_professionnel");
        String telephone_personnel = request.getParameter("telephone_personnel");

        // Set id dans l'objet Collaborateur
        obj.setId(id);
        obj.setMatricule(matricule);
        // Set des autres champs dans l'objet Collaborateur
        obj.setNom(nom);
        obj.setPrenom(prenom);
        obj.setMail(mail);
        obj.setTelephone_professionnel(telephone_professionnel);
        obj.setTelephone_personnel(telephone_personnel);

        // Définir l'objet Collaborateur dans l'attribut de la requête pour l'affichage
        request.setAttribute("ra", obj);

        return obj;
    }
}
