/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package forms;

import entities.Collaborateur;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe est utilisée pour valider et traiter les données soumises depuis
 * un formulaire de modification d'un collaborateur. Elle hérite de la classe
 * abstraite FormChecker et implémente la méthode checkForm pour effectuer la
 * validation des champs du formulaire et créer un objet Collaborateur avec les
 * données validées.
 *
 * @author cberge
 */
public class ModifierCollaborateurFormChecker extends FormChecker<Collaborateur> {

    /**
     * Constructeur de la classe ModifierCollaborateurFormChecker. Initialise la
     * classe parente avec la requête HTTP.
     *
     * @param request La requête HTTP contenant les données du formulaire à
     * valider.
     */
    public ModifierCollaborateurFormChecker(HttpServletRequest request) {
        super(request);
    }
    private Map<String, String> errors = new HashMap<>();

    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * Méthode héritée de la classe FormChecker. Vérifie et traite les données
     * soumises depuis le formulaire de modification d'un collaborateur.
     *
     * @return Un objet Collaborateur contenant les données validées du
     * formulaire.
     */
    @Override
    public Collaborateur checkForm() {
        Collaborateur obj = new Collaborateur();

        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr); // Assurez-vous que id est bien une valeur numérique

        // Récupérer les valeurs des champs modifiables
        String matriculeStr = request.getParameter("matricule");
        int matricule = Integer.parseInt(matriculeStr);

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String mail_1 = request.getParameter("mail_1");
        String mail_2 = request.getParameter("mail_2");
        String telephone_personnel = request.getParameter("telephone_personnel");
        String statut = request.getParameter("statut");
        String categorie = request.getParameter("categorie");
        String genre = request.getParameter("genre");
        String rqth = request.getParameter("rqth");
        String dateDeRenouvellement = request.getParameter("date_de_renouvellement");
        String type_rqth = request.getParameter("type_rqth");
        String metier = request.getParameter("metier");
        String materiel = request.getParameter("materiel");
        // Set id dans l'objet Collaborateur
        obj.setId(id);

        // Vérification et conversion du matricule
        try {
            obj.setMatricule(matricule);
        } catch (NumberFormatException e) {
            setError("matricule", "Le matricule ne peut pas contenir de caractères alphanumériques");
        }

        // Vérification et conversion de la date de renouvellement
        LocalDate date_de_renouvellement = null;
        if (dateDeRenouvellement != null && !dateDeRenouvellement.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date_de_renouvellement = LocalDate.parse(dateDeRenouvellement, formatter);
                obj.setDate_de_renouvellement(date_de_renouvellement);
            } catch (DateTimeParseException e) {
                setError("date_de_renouvellement", "Erreur de conversion pour le champ date_de_renouvellement : " + e.getMessage());
            }
        }

        // Validation du numéro de téléphone
        if (telephone_personnel != null && !telephone_personnel.isEmpty()) {
            String telephonePattern = "\\+?[0-9\\(\\)\\- ]{1,30}";
            if (!telephone_personnel.matches(telephonePattern)) {
                setError("telephone_personnel", "Le format du téléphone est incorrect.");
            }
        }

        // Set des autres champs dans l'objet Collaborateur
        obj.setNom(nom);
        obj.setPrenom(prenom);
        obj.setMail_1(mail_1);
        obj.setMail_2(mail_2);
        obj.setTelephone_personnel(telephone_personnel);
        obj.setStatut(statut);
        obj.setCategorie(categorie);
        obj.setGenre(genre);
        obj.setRqth(rqth);
        obj.setType_rqth(type_rqth);
        obj.setMetier(metier);
        obj.setMateriel(materiel);
        // Si aucune erreur, faire la mise à jour dans la base de données
        /*if (errors.isEmpty()) {
            // Appeler votre DAO pour effectuer la mise à jour
            CollaborateurDao collaborateurDao = new CollaborateurDao();
            collaborateurDao.update(obj);
        }*/
        // Définir l'objet Collaborateur dans l'attribut de la requête pour l'affichage
        request.setAttribute("collaborateur", obj);

        return obj;
    }
}
