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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author cberge
 */
public class CSVUtil {
////
//////    private static final String FILE_NAME = "historique.csv";
//////    private static final String DESKTOP_PATH = System.getProperty("user.home") + "\\Desktop\\" + FILE_NAME;
//////    
//////    public static void writeHistory(String action, String collaborateurNom, String collaborateurId){
//////        
//////        
//////        try(CSVWriter writer = new CSVWriter(new FileWriter(DESKTOP_PATH, true))){
//////            String[] record = {LocalDateTime.now().toString(), action, collaborateurNom, collaborateurId};
//////            writer.writeNext(record);
//////        }catch (IOException e){ e.printStackTrace();
//////        
//////        }
//////    }}

    // méthode pour avoir un seul fichier et les infos séparées par un titre
//    private static final String FILE_NAME = "historique.csv";
//
//    private static final String DESKTOP_PATH = System.getProperty("user.home") + "\\Desktop\\" + FILE_NAME;
//    private static void writeSectionName(CSVWriter writer, String sectionName) throws IOException {
//        writer.writeNext(new String[]{sectionName});
//
//    }
//     public static void writeHistory(String action, Prestation prestation) throws IOException {
//        {
//            
//            
//            
//            try (CSVWriter writer = new CSVWriter(new FileWriter(DESKTOP_PATH, true))) {
//                String sectionName = "Prestation";
//                // Écrire le nom de la section
////                writeSectionName(writer, sectionName);
//                // Créer les DAOs
//              
//                ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
//                PartenaireDao partenaireDao = new PartenaireDao();
//                CollaborateurDao collaborateurDao = new CollaborateurDao();
//
//// Récupérer les noms correspondants
//                ResponsableActivite responsable = responsableActiviteDao.read(prestation.getId_ra());
//                Partenaire partenaire = partenaireDao.read(prestation.getId_partenaire());
//                Collaborateur collaborateur = collaborateurDao.read(prestation.getId_collaborateur());
//
//                // Préparer les données à écrire dans le fichier CSV
//                String[] record = {
//                    LocalDateTime.now().toString(),
//                    action,
//                    prestation.getSiglum_presta(),
//                    prestation.getNum_affaire(),
//                    prestation.getNom_presta(),
//                    prestation.getRef_fact_partenaire(),
//                    prestation.getMail_partenaire(),
//                    prestation.getRef_fact_airbus(),
//                    prestation.getMail_airbus(),
//                    responsable != null ? responsable.getNom() : "Unknown",
//                    partenaire != null ? partenaire.getNom() : "Unknown",
//                    collaborateur != null ? collaborateur.getNom() : "Unknown"};
//                writer.writeNext(record);
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            }
//        }
//    }
//    public static void writeHistory(String action, Collaborateur collaborateur) throws IOException {
//        {
//            try (CSVWriter writer = new CSVWriter(new FileWriter(DESKTOP_PATH, true))) {
//                
//                String sectionName = "Collaborateur";
//// Écrire le nom de la section
////                writeSectionName(writer, sectionName);
//
//String[] headers = {
//                "Timestamp", "Action", "Matricule", "Nom", "Prenom", "Mail 1",
//                "Mail 2", "Telephone Personnel", "Statut", "Categorie", "Genre",
//                "RQTH", "Date de Renouvellement", "Type RQTH", "Metier",
//                "Materiel", "ID", "Responsables"
//            };
//            writeSectionHeader(writer, sectionName, headers);
//
//                
//LocalDate date_de_renouvellement = collaborateur.getDate_de_renouvellement();
//                List<Integer> respIds = collaborateur.getResponsablesIds();
//                List<String> nomRespIds = new ArrayList<>();
//                String respNomString = "";
//                ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
//                if (respIds != null) {
//                    for (Integer idResp : respIds) {
//                        ResponsableActivite resp = responsableActiviteDao.read(idResp);
//                        nomRespIds.add(resp.getNom());
//                    }
//                    respNomString = nomRespIds.stream()
//                            .map(String::valueOf) // Convertir chaque Integer en String
//                            .collect(Collectors.joining(","));
//                }
//                // Préparer les données à écrire dans le fichier CSV
//                String[] record = {
//                    LocalDateTime.now().toString(),
//                    action,
//                    String.valueOf(collaborateur.getMatricule()),
//                    collaborateur.getNom(),
//                    collaborateur.getPrenom(),
//                    collaborateur.getMail_1(),
//                    collaborateur.getMail_2(),
//                    collaborateur.getTelephone_personnel(),
//                    collaborateur.getStatut(),
//                    collaborateur.getCategorie(),
//                    collaborateur.getGenre(),
//                    collaborateur.getRqth(),
//                    date_de_renouvellement != null ? date_de_renouvellement.toString() : "",
//                    //            collaborateur.getDate_de_renouvellement() != null ? collaborateur.getDate_de_renouvellement().toString() : "",
//                    collaborateur.getType_rqth(),
//                    collaborateur.getMetier(),
//                    collaborateur.getMateriel(),
//                    //String.valueOf(collaborateur.getId()),
//                    respNomString};
//                writer.writeNext(record);
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            }
//        }
//    }
//
//    public static void writeHistory(String action, Partenaire partenaire) throws IOException {
//        {
//            try (CSVWriter writer = new CSVWriter(new FileWriter(DESKTOP_PATH, true))) {
//                String sectionName = "Partenaire";
//// Écrire le nom de la section
////                writeSectionName(writer, sectionName);
//
//String[] headers = {
//                "Timestamp", "Action", "Nom", "Numero Voie", "Adresse",
//                "Code Postal", "Ville", "ID", "Responsables"
//            };
//            writeSectionHeader(writer, sectionName, headers);
//
//
//                List<Integer> respIds = partenaire.getResponsablesIds();
//                List<String> nomRespIds = new ArrayList<>();
//                String respNomString = "";
//                ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
//                if (respIds != null) {
//                    for (Integer idResp : respIds) {
//                        ResponsableActivite resp = responsableActiviteDao.read(idResp);
//                        nomRespIds.add(resp.getNom());
//                    }
//                    respNomString = nomRespIds.stream()
//                            .map(String::valueOf) // Convertir chaque Integer en String
//                            .collect(Collectors.joining(","));
//                }
//                // Préparer les données à écrire dans le fichier CSV
//                String[] record = {
//                    LocalDateTime.now().toString(),
//                    action,
//                    String.valueOf(partenaire.getNom()),
//                    String.valueOf(partenaire.getNumero_voie()),
//                    partenaire.getAdresse(),
//                    String.valueOf(partenaire.getCode_postal()),
//                    partenaire.getVille(),
//                    //String.valueOf(partenaire.getId()),
//                    respNomString};
//                writer.writeNext(record);
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            }
//        }
//    }
//
//    public static void writeHistory(String action, ResponsableActivite ra) throws IOException {
//        {
//            try (CSVWriter writer = new CSVWriter(new FileWriter(DESKTOP_PATH, true))) {
//                String sectionName = "Responsable activité";
//// Écrire le nom de la section
////                writeSectionName(writer, sectionName);
//
// String[] headers = {
//                "Timestamp", "Action", "Matricule", "Nom", "Prenom", "Mail",
//                "Telephone Professionnel", "Telephone Personnel", "ID",
//                "Partenaires", "Collaborateurs"
//            };
//            writeSectionHeader(writer, sectionName, headers);
//
//
//                List<Integer> partIds = ra.getPartenaireIds();
//                List<String> nomPartIds = new ArrayList<>();
//                String partNomString = "";
//                PartenaireDao partenaireDao = new PartenaireDao();
//                if (partIds != null) {
//                    for (Integer idPart : partIds) {
//                        Partenaire part = partenaireDao.read(idPart);
//                        nomPartIds.add(part.getNom());
//                    }
//                    partNomString = nomPartIds.stream()
//                            .map(String::valueOf)
//                            .collect(Collectors.joining(","));
//                }
//                List<Integer> collabIds = ra.getCollaborateurIds();
//                List<String> nomCollabIds = new ArrayList<>();
//                String collabNomString = "";
//                CollaborateurDao collaborateurDao = new CollaborateurDao();
//                if (collabIds != null) {
//                    for (Integer idCollab : collabIds) {
//                        Collaborateur collab = collaborateurDao.read(idCollab);
//                        nomCollabIds.add(collab.getNom());
//                    }
//                    collabNomString = nomCollabIds.stream()
//                            .map(String::valueOf)
//                            .collect(Collectors.joining(","));
//                }
//                // Préparer les données à écrire dans le fichier CSV
//                String[] record = {
//                    LocalDateTime.now().toString(),
//                    action,
//                    String.valueOf(ra.getMatricule()),
//                    ra.getNom(),
//                    ra.getPrenom(),
//                    ra.getMail(),
//                    ra.getTelephone_professionnel(),
//                    ra.getTelephone_personnel(),
//                    //String.valueOf(ra.getId()),
//                    partNomString,
//                    collabNomString};
//                writer.writeNext(record);
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            }
//        }
//    }
//}
    // methode pour avoir 4 fichiers csv distinct
    private static void writeSectionHeader(CSVWriter writer, String[] headers) throws IOException {
        //writer.writeNext(new String[]{sectionName});
        writer.writeNext(headers);
    }

    private static boolean isFileEmpty(String path) throws IOException {
        File file = new File(path);
        return !file.exists() || file.length() == 0;
    }

    public static void writeHistory(String action, Prestation prestation) throws IOException {
        {
            String fileNamePresta = "historiquePrestation.csv";
            String pathPresta = System.getProperty("user.home") + "\\Desktop\\" + fileNamePresta;

            try (CSVWriter writer = new CSVWriter(new FileWriter(pathPresta, true))) {

                String[] headers = {
                    "Timestamp", "Action", "Siglum Presta", "Num Affaire", "Nom Presta",
                    "Ref Fact Partenaire", "Mail Partenaire", "Ref Fact Airbus", "Mail Airbus",
                    "ID RA", "ID Partenaire", "ID Collaborateur"
                };
                if (isFileEmpty(pathPresta)) {
                    writeSectionHeader(writer, headers);
                }

                ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
                PartenaireDao partenaireDao = new PartenaireDao();
                CollaborateurDao collaborateurDao = new CollaborateurDao();

// Récupérer les noms correspondants
                ResponsableActivite responsable = responsableActiviteDao.read(prestation.getId_ra());
                Partenaire partenaire = partenaireDao.read(prestation.getId_partenaire());
                Collaborateur collaborateur = collaborateurDao.read(prestation.getId_collaborateur());

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
                    responsable != null ? responsable.getNom() : "Unknown",
                    partenaire != null ? partenaire.getNom() : "Unknown",
                    collaborateur != null ? collaborateur.getNom() : "Unknown"};
                writer.writeNext(record);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public static void writeHistory(String action, Collaborateur collaborateur) throws IOException {
        {

            String fileNameCollaborateur = "historiqueCollaborateur.csv";
            String pathCollab = System.getProperty("user.home") + "\\Desktop\\" + fileNameCollaborateur;
            try (CSVWriter writer = new CSVWriter(new FileWriter(pathCollab, true))) {

                String[] headers = {
                    "Timestamp", "Action", "Matricule", "Nom", "Prenom", "Mail 1",
                    "Mail 2", "Telephone Personnel", "Statut", "Categorie", "Genre",
                    "RQTH", "Date de Renouvellement", "Type RQTH", "Metier",
                    "Materiel", "Responsables"
                };
                if (isFileEmpty(pathCollab)) {
                    writeSectionHeader(writer, headers);
                }

                LocalDate date_de_renouvellement = collaborateur.getDate_de_renouvellement();
                List<Integer> respIds = collaborateur.getResponsablesIds();
                List<String> nomRespIds = new ArrayList<>();
                String respNomString = "";
                ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
                if (respIds != null) {
                    for (Integer idResp : respIds) {
                        ResponsableActivite resp = responsableActiviteDao.read(idResp);
                        nomRespIds.add(resp.getNom());
                    }
                    respNomString = nomRespIds.stream()
                            .map(String::valueOf) // Convertir chaque Integer en String
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
                    collaborateur.getType_rqth(),
                    collaborateur.getMetier(),
                    collaborateur.getMateriel(),
                    //String.valueOf(collaborateur.getId()),
                    respNomString};
                writer.writeNext(record);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public static void writeHistory(String action, Partenaire partenaire) throws IOException {
        {
            String fileNamePartenaire = "historiquePartenaire.csv";
            String pathPart = System.getProperty("user.home") + "\\Desktop\\" + fileNamePartenaire;

            try (CSVWriter writer = new CSVWriter(new FileWriter(pathPart, true))) {

                String[] headers = {
                    "Timestamp", "Action", "Nom", "Numero Voie", "Adresse",
                    "Code Postal", "Ville", "Responsables"
                };
                if (isFileEmpty(pathPart)) {
                    writeSectionHeader(writer, headers);
                }

                List<Integer> respIds = partenaire.getResponsablesIds();
                List<String> nomRespIds = new ArrayList<>();
                String respNomString = "";
                ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
                if (respIds != null) {
                    for (Integer idResp : respIds) {
                        ResponsableActivite resp = responsableActiviteDao.read(idResp);
                        nomRespIds.add(resp.getNom());
                    }
                    respNomString = nomRespIds.stream()
                            .map(String::valueOf) // Convertir chaque Integer en String
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
                    partenaire.getVille(),
                    //String.valueOf(partenaire.getId()),
                    respNomString};
                writer.writeNext(record);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public static void writeHistory(String action, ResponsableActivite ra) throws IOException {
        {
            String fileNameResponsable = "historiqueResponsable.csv";
            String pathRa = System.getProperty("user.home") + "\\Desktop\\" + fileNameResponsable;

            try (CSVWriter writer = new CSVWriter(new FileWriter(pathRa, true))) {

                String[] headers = {
                    "Timestamp", "Action", "Matricule", "Nom", "Prenom", "Mail",
                    "Telephone Professionnel", "Telephone Personnel",
                    "Partenaires", "Collaborateurs"
                };
                if (isFileEmpty(pathRa)) {
                    writeSectionHeader(writer, headers);
                }

                List<Integer> partIds = ra.getPartenaireIds();
                List<String> nomPartIds = new ArrayList<>();
                String partNomString = "";
                PartenaireDao partenaireDao = new PartenaireDao();
                if (partIds != null) {
                    for (Integer idPart : partIds) {
                        Partenaire part = partenaireDao.read(idPart);
                        nomPartIds.add(part.getNom());
                    }
                    partNomString = nomPartIds.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(","));
                }
                List<Integer> collabIds = ra.getCollaborateurIds();
                List<String> nomCollabIds = new ArrayList<>();
                String collabNomString = "";
                CollaborateurDao collaborateurDao = new CollaborateurDao();
                if (collabIds != null) {
                    for (Integer idCollab : collabIds) {
                        Collaborateur collab = collaborateurDao.read(idCollab);
                        nomCollabIds.add(collab.getNom());
                    }
                    collabNomString = nomCollabIds.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(","));
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
                    //String.valueOf(ra.getId()),
                    partNomString,
                    collabNomString};
                writer.writeNext(record);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}
