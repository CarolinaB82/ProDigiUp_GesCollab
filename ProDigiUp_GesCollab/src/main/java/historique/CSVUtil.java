/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package historique;

import com.opencsv.CSVWriter;
import dao.CollaborateurDao;
import dao.PartenaireDao;
import dao.ResponsableActiviteDao;
import entities.Collaborateur;
import entities.Partenaire;
import entities.Prestation;
import entities.ResponsableActivite;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author cberge
 */
public class CSVUtil {

//    private static final String FILE_NAME = "historique.csv";
//    private static final String DESKTOP_PATH = System.getProperty("user.home") + "\\Desktop\\" + FILE_NAME;
//    
//    public static void writeHistory(String action, String collaborateurNom, String collaborateurId){
//        
//        
//        try(CSVWriter writer = new CSVWriter(new FileWriter(DESKTOP_PATH, true))){
//            String[] record = {LocalDateTime.now().toString(), action, collaborateurNom, collaborateurId};
//            writer.writeNext(record);
//        }catch (IOException e){ e.printStackTrace();
//        
//        }
//    }}
    private static final String FILE_NAME = "historique.csv";
        

    private static final String DESKTOP_PATH = System.getProperty("user.home") + "\\Desktop\\" + FILE_NAME;
    
    public static void writeHistory(String action, Prestation prestation) throws IOException {
        {
            try (CSVWriter writer = new CSVWriter(new FileWriter(DESKTOP_PATH, true))) {

                // Préparer les données à écrire dans le fichier CSV
                String[] record = {
                    LocalDateTime.now().toString(),
                    action,
                    prestation.getSiglum_presta(),
                    prestation.getNum_affaire(),
                    prestation.getNom_presta(),
                    prestation.getRef_fact_partenaire(),
                    prestation.getMail_partenaire(),
                    prestation.getRef_fact_airbus(),
                    prestation.getMail_airbus(),
                    String.valueOf(prestation.getId_ra()),
                    String.valueOf(prestation.getId_partenaire()),
                    String.valueOf(prestation.getId_collaborateur())};
                writer.writeNext(record);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
        


    public static void writeHistory(String action, Collaborateur collaborateur) throws IOException {
        {
            try (CSVWriter writer = new CSVWriter(new FileWriter(DESKTOP_PATH, true))) {
                LocalDate date_de_renouvellement = collaborateur.getDate_de_renouvellement();
                
                List<Integer> respIds = collaborateur.getResponsablesIds();
                List<String> nomRespIds = new ArrayList<>();
                String respNomString = "";
                ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
                if(respIds != null){
                    for(Integer idResp : respIds){
                        ResponsableActivite resp = responsableActiviteDao.read(idResp);
                        nomRespIds.add(resp.getNom());
                    }
                    respNomString = nomRespIds.stream()
                                      .map(String::valueOf)  // Convertir chaque Integer en String
                                      .collect(Collectors.joining(","));
                }
                

                // Préparer les données à écrire dans le fichier CSV
                String[] record = {
                    LocalDateTime.now().toString(),
                    action,
                    String.valueOf(collaborateur.getMatricule()),
                    collaborateur.getNom(),
                    collaborateur.getPrenom(),
                    collaborateur.getMail_1(),
                    collaborateur.getMail_2(),
                    collaborateur.getTelephone_personnel(),
                    collaborateur.getStatut(),
                    collaborateur.getCategorie(),
                    collaborateur.getGenre(),
                    collaborateur.getRqth(),
                    date_de_renouvellement != null ? date_de_renouvellement.toString() : "",
                    //            collaborateur.getDate_de_renouvellement() != null ? collaborateur.getDate_de_renouvellement().toString() : "",
                    collaborateur.getMetier(),
                    String.valueOf(collaborateur.getId()),
                    respNomString};
                writer.writeNext(record);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
     public static void writeHistory(String action, Partenaire partenaire) throws IOException {
        {
            try (CSVWriter writer = new CSVWriter(new FileWriter(DESKTOP_PATH, true))) {
                
                
                List<Integer> respIds = partenaire.getResponsablesIds();
                List<String> nomRespIds = new ArrayList<>();
                String respNomString = "";
                ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
                if(respIds != null){
                    for(Integer idResp : respIds){
                        ResponsableActivite resp = responsableActiviteDao.read(idResp);
                        nomRespIds.add(resp.getNom());
                    }
                    respNomString = nomRespIds.stream()
                                      .map(String::valueOf)  // Convertir chaque Integer en String
                                      .collect(Collectors.joining(","));
                }
                

                // Préparer les données à écrire dans le fichier CSV
                String[] record = {
                    LocalDateTime.now().toString(),
                    action,
                    String.valueOf(partenaire.getNom()),
                    String.valueOf(partenaire.getNumero_voie()),
                    partenaire.getAdresse(),
                    String.valueOf(partenaire.getCode_postal()),
                    partenaire.getVille(),String.valueOf(partenaire.getId()),
                    respNomString};
                writer.writeNext(record);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
    
     public static void writeHistory(String action, ResponsableActivite ra) throws IOException {
        {
            try (CSVWriter writer = new CSVWriter(new FileWriter(DESKTOP_PATH, true))) {
               
                List<Integer>partIds = ra.getPartenaireIds();
                List<String> nomPartIds = new ArrayList<>();
                String partNomString = "";
                PartenaireDao partenaireDao = new PartenaireDao();
                if (partIds !=null) {
                    for(Integer idPart : partIds){
                    Partenaire part = partenaireDao.read(idPart);
                    nomPartIds.add(part.getNom());
                }
partNomString = nomPartIds.stream()
        .map (String:: valueOf)
        .collect (Collectors.joining (","));
                }
                 List<Integer>collabIds = ra.getCollaborateurIds();
                List<String> nomCollabIds = new ArrayList<>();
                String collabNomString = "";
                CollaborateurDao collaborateurDao = new CollaborateurDao();
                if (collabIds !=null) {
                    for(Integer idCollab : collabIds){
                    Collaborateur collab = collaborateurDao.read(idCollab);
                    nomCollabIds.add(collab.getNom());
                }
collabNomString = nomCollabIds.stream()
        .map (String:: valueOf)
        .collect (Collectors.joining (","));
                }
                
                // Préparer les données à écrire dans le fichier CSV
                String[] record = {
                    LocalDateTime.now().toString(),
                    action,
                    String.valueOf(ra.getMatricule()),
                    ra.getNom(),
                    ra.getPrenom(),
                    ra.getMail(),
                    ra.getTelephone_professionnel(),
                    ra.getTelephone_personnel(),
                    String.valueOf(ra.getId()),
                partNomString,
                collabNomString};
                writer.writeNext(record);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}
