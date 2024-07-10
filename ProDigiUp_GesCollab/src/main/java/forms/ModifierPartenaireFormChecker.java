/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package forms;

import entities.Partenaire;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author cberge
 */
public class ModifierPartenaireFormChecker extends FormChecker<Partenaire> {

    public ModifierPartenaireFormChecker(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Partenaire checkForm() {
        Partenaire obj = new Partenaire();

        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr); // Assurez-vous que id est bien une valeur numérique

        // Récupérer les valeurs des champs modifiables
        String nom = request.getParameter("nom");

        String numero_voieStr = request.getParameter("numero_voie");
        int numero_voie = Integer.parseInt(numero_voieStr);

        String adresse = request.getParameter("adresse");

        String code_postalStr = request.getParameter("code_postal");
        int code_postal = Integer.parseInt(code_postalStr);

        String ville = request.getParameter("ville");

        // Set id dans l'objet Collaborateur
        obj.setId(id);
// Set des autres champs dans l'objet Collaborateur
        obj.setNom(nom);

        // Vérification et conversion du matricule
        try {
            obj.setNumero_voie(numero_voie);
        } catch (NumberFormatException e) {
            setError("numero_voie", "Le numero de voie ne peut pas contenir de caractères alphanumériques");
        }
//        // Validation du numéro de numero_voie
//        if (numero_voie != null && !numero_voie.isEmpty()) {
//            String numeroPattern = "[0-9]*";
//            if (!numero_voie.matches(numeroPattern)) {
//                setError("numero_voie", "Le format du numero_voie est incorrect.");
//            }
//        }

        obj.setAdresse(adresse);

        // Vérification et conversion du matricule
        try {
            obj.setCode_postal(code_postal);
        } catch (NumberFormatException e) {
            setError("code_postal", "Le code postal ne peut pas contenir de caractères alphanumériques");
        }

//        // Validation du numéro de code_postal
//        if (code_postal != null && !code_postal.isEmpty()) {
//            String codePattern = "\"\\d{5}\" 5";
//            if (!code_postal.matches(codePattern)) {
//                setError("code_postal", "Le format du code_postal est incorrect.");
//            }
//        }
        obj.setVille(ville);

        // Définir l'objet Collaborateur dans l'attribut de la requête pour l'affichage
        request.setAttribute("partenaire", obj);

        return obj;
    }
}
