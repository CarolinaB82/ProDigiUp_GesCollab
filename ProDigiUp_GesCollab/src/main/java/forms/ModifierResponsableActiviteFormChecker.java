/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package forms;

import entities.ResponsableActivite;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author cberge
 */
public class ModifierResponsableActiviteFormChecker extends FormChecker<ResponsableActivite>{

     public ModifierResponsableActiviteFormChecker(HttpServletRequest request) {
        super(request);
    }
     @Override
    public ResponsableActivite checkForm() {
        ResponsableActivite obj = new ResponsableActivite();

        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr); // Assurez-vous que id est bien une valeur numérique
String matriculeStr = request.getParameter("matricule");
        int matricule = Integer.parseInt (matriculeStr);
        // Récupérer les valeurs des champs modifiables
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


