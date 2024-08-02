/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package historique;

/**
 *
 * @author carol
 */
public class ValidationUtil {

    /**
     * Nettoie et valide le matricule en conservant uniquement les chiffres.
     *
     * @param matricule La chaîne de caractères à nettoyer.
     * @return Le matricule nettoyé ou null si invalide.
     */
    public static String sanitizeMatricule(String matricule) {
        if (matricule == null) {
            return null;
        }
        String sanitizedMatricule = matricule.replaceAll("[^0-9]", "");
        return sanitizedMatricule.isEmpty() ? null : sanitizedMatricule;
    }

    /**
     * Nettoie et valide le nom en conservant uniquement les lettres et les
     * espaces.
     *
     * @param nom La chaîne de caractères à nettoyer.
     * @return Le nom nettoyé ou null si invalide.
     */
    public static String sanitizeNom(String nom) {
        if (nom == null) {
            return null;
        }
        String sanitizedNom = nom.replaceAll("[^a-zA-Z ]", "");
        return sanitizedNom.isEmpty() ? null : sanitizedNom;
    }

}
