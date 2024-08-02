///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package dao;
//
//import entities.Collaborateur;
//import entities.CollaborateurPrestationPartenaireRa;
//import entities.Prestation;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.Collection;
//import java.util.List;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// *
// * @author cberge
// */
//public class CollaborateurDaoTest {
//    
//    public CollaborateurDaoTest() {
//    }
//    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
//
//    /**
//     * Test of createObject method, of class CollaborateurDao.
//     */
//    @Test
//    public void testCreateObject() throws Exception {
//        System.out.println("createObject");
//        ResultSet rs = null;
//        CollaborateurDao instance = new CollaborateurDao();
//        Collaborateur expResult = null;
//        Collaborateur result = instance.createObject(rs);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of create method, of class CollaborateurDao.
//     */
//    @Test
//    public void testCreate() throws Exception {
////        System.out.println("create");
////        Collaborateur obj = null;
////        CollaborateurDao instance = new CollaborateurDao();
////        instance.create(obj);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
// Collaborateur collaborateur = new Collaborateur();
//    collaborateur.setNom("Nom Test");
//
//    // Assurez-vous que le champ 'nom' est correctement défini
//    assertNotNull(collaborateur.getNom());
//    assertFalse(collaborateur.getNom().trim().isEmpty());
//
//    CollaborateurDao dao = new CollaborateurDao();
//    dao.create(collaborateur);
//
//    // Vérifiez que le collaborateur a été correctement inséré
//    Collaborateur retrieved = dao.read(collaborateur.getId());
//    assertNotNull(retrieved);
//    assertEquals("Nom Test", retrieved.getNom());
//}
//
//    /**
//     * Test of read method, of class CollaborateurDao.
//     */
//    @Test
//    public void testRead() {
//        System.out.println("read");
//        Integer id = null;
//        CollaborateurDao instance = new CollaborateurDao();
//        Collaborateur expResult = null;
//        Collaborateur result = instance.read(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of update method, of class CollaborateurDao.
//     */
//    @Test
//    public void testUpdate() throws Exception {
//        System.out.println("update");
//        
//            // Initialiser l'objet Collaborateur avec des valeurs appropriées
//         Collaborateur collaborateur = new Collaborateur();
//    collaborateur.setId(1); // Assurez-vous de définir un ID valide
//        collaborateur.setMatricule(12345);
//    collaborateur.setNom("Nom Test");
//    collaborateur.setPrenom("Prenom Test");
//        collaborateur.setMail_1("Mail_1 Test");
//            collaborateur.setMail_2("Mail_2 Test");
//    collaborateur.setTelephone_personnel("Telephone_personnel Test");
//        collaborateur.setStatut("Statut Test");
//        collaborateur.setCategorie("Categorie Test");
//        collaborateur.setGenre("Genre Test");
//        collaborateur.setRqth("Rqth Test");
//                
//        // Utiliser LocalDate pour définir la date de renouvellement
//    LocalDate dateDeRenouvellement = LocalDate.of(2022, 1, 1); // 1er janvier 2022
//    collaborateur.setDate_de_renouvellement(dateDeRenouvellement);
//    
//                collaborateur.setType_rqth("Type_rqth Test");
//                        collaborateur.setMetier("Metier Test");
//        collaborateur.setMateriel("Materiel Test");
//        
//        CollaborateurDao instance = new CollaborateurDao();
//
//    // Appeler la méthode update
//    instance.update(collaborateur);
//    
//     // Ajouter des assertions pour vérifier que l'objet a été mis à jour correctement
//    Collaborateur updatedCollaborateur = instance.read(collaborateur.getId());
//    assertNotNull(updatedCollaborateur);
//     assertEquals(12345, updatedCollaborateur.getMatricule());
//    assertEquals("Nom Test", updatedCollaborateur.getNom());
//    assertEquals("Prenom Test", updatedCollaborateur.getPrenom());
//     assertEquals("Mail_1 Test", updatedCollaborateur.getMail_1());
//          assertEquals("Mail_2 Test", updatedCollaborateur.getMail_2());
//                    assertEquals("Telephone_personnel Test", updatedCollaborateur.getTelephone_personnel());
//          assertEquals("Statut Test", updatedCollaborateur.getStatut());
//          assertEquals("Categorie Test", updatedCollaborateur.getCategorie());
//          assertEquals("Genre Test", updatedCollaborateur.getGenre());
//          assertEquals("Rqth Test", updatedCollaborateur.getRqth());
//         
//           // Utiliser la méthode utilitaire pour convertir la date en entier
//    int expectedDate = 20220101; // Format AAAAMMJJ
//    int actualDate = convertLocalDateToInt(updatedCollaborateur.getDate_de_renouvellement());
//    assertEquals(expectedDate, actualDate);
//    
//          assertEquals("Type_rqth Test", updatedCollaborateur.getType_rqth());
//          assertEquals("Metier Test", updatedCollaborateur.getMetier());
//          assertEquals("Materiel Test", updatedCollaborateur.getMateriel());
//
//
//           
//
//}
//       private int convertLocalDateToInt(LocalDate date) {
//    return date.getYear() * 10000 + date.getMonthValue() * 100 + date.getDayOfMonth();
//} 
//    
//
//    /**
//     * Test of getCollaborateur method, of class CollaborateurDao.
//     */
//    @Test
//    public void testGetCollaborateur() throws Exception {
//        System.out.println("getCollaborateur");
//        Connection connexion = null;
//        int idCollaborateur = 0;
//        CollaborateurDao instance = new CollaborateurDao();
//        Collaborateur expResult = null;
//        Collaborateur result = instance.getCollaborateur(connexion, idCollaborateur);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete method, of class CollaborateurDao.
//     */
//    @Test
//    public void testDelete() throws Exception {
//        System.out.println("delete");
//        Integer collaborateurId = null;
//        CollaborateurDao instance = new CollaborateurDao();
//        instance.delete(collaborateurId);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listCollaborateurPrestationPartenaireRa method, of class CollaborateurDao.
//     */
//    @Test
//    public void testListCollaborateurPrestationPartenaireRa() {
//        System.out.println("listCollaborateurPrestationPartenaireRa");
//        CollaborateurDao instance = new CollaborateurDao();
//        
//   
//      
//        Collection<CollaborateurPrestationPartenaireRa> result = instance.listCollaborateurPrestationPartenaireRa();
//        
//        // Vérifiez que la liste n'est pas nulle
//        assertNotNull(result, "La liste ne doit pas être nulle");
//        
//        // Vérifiez que la liste contient les éléments attendus
//        assertFalse(result.isEmpty(), "La liste ne doit pas être vide");
//        
//        // Vérifiez les éléments de la liste si nécessaire
//        for (CollaborateurPrestationPartenaireRa item : result) {
//            assertNotNull(item);
//            // Ajouter des assertions spécifiques aux propriétés de l'objet si nécessaire
//        }
//    }
//
//    /**
//     * Test of listCollaborateur method, of class CollaborateurDao.
//     */
//    @Test
//    public void testListCollaborateur() {
//        System.out.println("listCollaborateur");
//        int idRa = 0;
//        CollaborateurDao instance = new CollaborateurDao();
//        Collection<Collaborateur> expResult = null;
//        Collection<Collaborateur> result = instance.listCollaborateur(idRa);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listPrestationCollaborateur method, of class CollaborateurDao.
//     */
//    @Test
//    public void testListPrestationCollaborateur() {
//        System.out.println("listPrestationCollaborateur");
//        int idCollaborateur = 0;
//        CollaborateurDao instance = new CollaborateurDao();
//        Collection<Prestation> expResult = null;
//        Collection<Prestation> result = instance.listPrestationCollaborateur(idCollaborateur);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of exists method, of class CollaborateurDao.
//     */
//    @Test
//    public void testExists() {
//        System.out.println("exists");
//        int matricule = 0;
//        CollaborateurDao instance = new CollaborateurDao();
//        boolean expResult = false;
//        boolean result = instance.exists(matricule);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of existsForOtherCollab method, of class CollaborateurDao.
//     */
//    @Test
//    public void testExistsForOtherCollab() throws SQLException {
////        System.out.println("existsForOtherCollab");
////        int matricule = 0;
////        int idCollaborateur = 0;
////        CollaborateurDao instance = new CollaborateurDao();
////        boolean expResult = false;
////        boolean result = instance.existsForOtherCollab(matricule, idCollaborateur);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
// System.out.println("existsForOtherCollab");
//    
//    // Initialiser les valeurs de test
//    int matricule = 12345;
//    int idCollaborateur = 1;
//    
//    // Initialiser l'instance de CollaborateurDao
//    CollaborateurDao instance = new CollaborateurDao();
//    
//    // Ajouter un collaborateur avec les valeurs de test (simulé)
//    Collaborateur collaborateur = new Collaborateur();
//    collaborateur.setId(idCollaborateur);
//    collaborateur.setMatricule(matricule); // Passer la chaîne de caractères
//
//    // Ajouter le collaborateur à la base de données (simulé)
//    instance.create(collaborateur);
//    
//    // Appeler la méthode existsForOtherCollab
//        boolean result = instance.existsForOtherCollab(matricule, idCollaborateur); // Convertir la chaîne en entier pour l'assertion
//
//      
//    // Vérifier que le collaborateur existe
//    assertTrue(result, "Le collaborateur devrait exister pour l'autre collaborateur.");
//
//    // Vérifier que le collaborateur n'existe pas pour un autre ID
//    boolean resultForDifferentId = instance.existsForOtherCollab(matricule, idCollaborateur + 1);
//    assertFalse(resultForDifferentId, "Le collaborateur ne devrait pas exister pour un autre ID de collaborateur.");
//}
//
//    /**
//     * Test of list method, of class CollaborateurDao.
//     */
//    @Test
//    public void testList() {
//        System.out.println("list");
//        CollaborateurDao instance = new CollaborateurDao();
//        Collection<Collaborateur> expResult = null;
//        Collection<Collaborateur> result = instance.list();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of rechercherParNom method, of class CollaborateurDao.
//     */
//    @Test
//    public void testRechercherParNom() throws Exception {
//        System.out.println("rechercherParNom");
//        String nom = "";
//        CollaborateurDao instance = new CollaborateurDao();
//        List<Collaborateur> expResult = null;
//        List<Collaborateur> result = instance.rechercherParNom(nom);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of rechercherParPrenom method, of class CollaborateurDao.
//     */
//    @Test
//    public void testRechercherParPrenom() throws Exception {
//        System.out.println("rechercherParPrenom");
//        String prenom = "";
//        CollaborateurDao instance = new CollaborateurDao();
//        List<Collaborateur> expResult = null;
//        List<Collaborateur> result = instance.rechercherParPrenom(prenom);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of rechercherParMatricule method, of class CollaborateurDao.
//     */
//    @Test
//    public void testRechercherParMatricule() throws Exception {
//        System.out.println("rechercherParMatricule");
//        String matricule = "";
//        CollaborateurDao instance = new CollaborateurDao();
//        List<Collaborateur> expResult = null;
//        List<Collaborateur> result = instance.rechercherParMatricule(matricule);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLastIdCreated method, of class CollaborateurDao.
//     */
//    @Test
//    public void testGetLastIdCreated() {
//        System.out.println("getLastIdCreated");
//        CollaborateurDao instance = new CollaborateurDao();
//        int expResult = 0;
//        int result = instance.getLastIdCreated();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//    @Test
//public void testInsertCollaborateur() throws Exception {
//    System.out.println("insertCollaborateur");
//
//    // Initialiser l'objet Collaborateur avec des valeurs appropriées
//    Collaborateur collaborateur = new Collaborateur();
//    collaborateur.setId(1);
//    collaborateur.setNom("Nom Test"); // Assurez-vous que ce champ n'est pas null ou vide
//    // Initialiser d'autres champs...
//
//    // Assurez-vous que 'nom' est non-null et non-vide
//    if (collaborateur.getNom() == null || collaborateur.getNom().trim().isEmpty()) {
//        throw new IllegalArgumentException("Le champ 'nom' ne peut pas être vide.");
//    }
//
//    CollaborateurDao instance = new CollaborateurDao();
//
//    // Appeler la méthode pour insérer ou mettre à jour
//    instance.create(collaborateur);
//
//    // Ajouter des assertions pour vérifier que l'objet a été inséré correctement
//    Collaborateur insertedCollaborateur = instance.read(collaborateur.getId());
//    assertNotNull(insertedCollaborateur);
//    assertEquals("Nom Test", insertedCollaborateur.getNom());
//    // Ajouter d'autres assertions si nécessaire
//}
//}
