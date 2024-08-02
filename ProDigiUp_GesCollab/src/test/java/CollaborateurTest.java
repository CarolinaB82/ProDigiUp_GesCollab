/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import entities.Collaborateur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author cberge
 */
public class CollaborateurTest {

    static Collaborateur matricule;
    
    public CollaborateurTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Exécution avant tous les tests (configurations globales).");
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Exécution après tous les tests (nettoyage global).");
    }

    @BeforeEach
    public void setUp() {
        
       
        System.out.println("Exécution avant chaque test (configuration).");

    }

    @AfterEach
    public void tearDown() {
        System.out.println("Exécution après chaque test (nettoyage).");
    }

    @Test
    public void testMatriculeValide() {
        String matriculeValide = "12345";
        System.out.println("Début du test pour un matricule valide: " + matriculeValide);
        assertTrue(verifierMatricule(matriculeValide));
        System.out.println("Le matricule " + matriculeValide + " est valide.");
    }

    @Test
    public void testMatriculeInvalideAvecLettres() {
        String matriculeInvalide = "ABCDE";
        System.out.println("Début du test pour un matricule invalide avec des caractères: " + matriculeInvalide);
        assertFalse(verifierMatricule(matriculeInvalide));
        System.out.println("Le matricule " + matriculeInvalide + " est invalide.");

    }

    @Test
    public void testMatriculeInvalideLongueurDifferente() {
        String matriculeInvalide = "123456";
        System.out.println("Début du test pour un matricule invalide avec une taille incorrecte: " + matriculeInvalide);
        assertFalse(verifierMatricule(matriculeInvalide));
        System.out.println("Le matricule " + matriculeInvalide + " est invalide.");

    }

    // Méthode à tester
    public boolean verifierMatricule(String matricule) {
        // Vérification que le matricule contient exactement 5 chiffres
        return matricule.matches("\\d{1,5}");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {} 
    
    
    
}