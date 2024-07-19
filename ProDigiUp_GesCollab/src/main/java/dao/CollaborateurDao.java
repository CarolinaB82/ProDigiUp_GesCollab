package dao;

import entities.Collaborateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import entities.CollaborateurPrestationPartenaireRa;
import entities.Prestation;
import entities.PrestationRaPartenaire;
import entities.ResponsableActivite;
import historique.CSVUtil;
import static historique.JsonUtil.collaborateurToJson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static historique.JsonUtil.collaborateurToJson;

/**
 * Implementation d'une DAO Gestion du CRUD sur les objets Collaborateur dans la
 * base de données relationnelles Connexion a la BDD Constructeur Methodes CRUD
 * Gestion des exceptions Methodes specifiques La classe CollaborateurDao
 * encapsule la logique d'accès aux données pour les objets Collaborateur
 *
 *
 * @author cberge
 */
public class CollaborateurDao extends Dao<Collaborateur> {

    public CollaborateurDao() {
        super("Collaborateur");
    }

    /**
     * Crée un objet Collaborateur à partir des données d'un ResultSet.
     *
     * @param rs le ResultSet contenant les données du collaborateur à extraire
     * @return un objet Collaborateur initialisé avec les données du ResultSet
     * @throws SQLException si une erreur SQL survient lors de l'extraction des
     * données
     */
    @Override
    protected Collaborateur createObject(ResultSet rs) throws SQLException {
        Collaborateur obj = new Collaborateur();
        obj.setId(rs.getInt("id_" + table));
        obj.setMatricule(rs.getInt("matricule"));
        obj.setNom(rs.getString("nom"));
        obj.setPrenom(rs.getString("prenom"));
        obj.setMail_1(rs.getString("mail_1"));
        obj.setTelephone_personnel(rs.getString("telephone_personnel"));
        obj.setStatut(rs.getString("statut"));
        obj.setCategorie(rs.getString("categorie"));
        obj.setGenre(rs.getString("genre"));
        obj.setRqth(rs.getString("rqth"));
        obj.setDate_de_renouvellement(rs.getDate("date_de_renouvellement").toLocalDate());
        obj.setMetier(rs.getString("metier"));

        return obj;
    }

    /**
     * Crée un nouveau collaborateur dans la base de données à partir des
     * informations fournies dans l'objet passé en paramètre Après l'insertion
     * réussie, l'identifiant généré est assigné à l'objet collaborateur
     *
     * @param obj l'objet Collaborateur à insérer dans la base de données
     * @throws SQLException si une erreur survient lors de l'exécution de la
     * requête SQL
     */
    @Override
    public void create(Collaborateur obj) throws SQLException {
        String sql = "INSERT INTO  collaborateur (matricule, nom, prenom, mail_1, mail_2, telephone_personnel, statut, categorie, genre, rqth, date_de_renouvellement, metier)"
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, obj.getMatricule());
            pstmt.setString(2, obj.getNom());
            pstmt.setString(3, obj.getPrenom());
            pstmt.setString(4, obj.getMail_1());
            pstmt.setString(5, obj.getMail_2());
            pstmt.setString(6, obj.getTelephone_personnel());
            pstmt.setString(7, obj.getStatut());
            pstmt.setString(8, obj.getCategorie());
            pstmt.setString(9, obj.getGenre());
            pstmt.setString(10, obj.getRqth());
            LocalDate localDate = obj.getDate_de_renouvellement();
            if (localDate != null) {
                pstmt.setDate(11, java.sql.Date.valueOf(localDate));
            } else {
                pstmt.setDate(11, null);
            }
            pstmt.setString(12, obj.getMetier());
            int nbLines = pstmt.executeUpdate();
            if (nbLines == 1) {
                ResultSet autoGeneratedKeys = pstmt.getGeneratedKeys();
                autoGeneratedKeys.first();
                int id = autoGeneratedKeys.getInt(1);
                obj.setId(id);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'insertion : " + ex.getMessage());
            ex.printStackTrace();
            throw ex;

        }
    }

    /**
     * Récupère un collaborateur depuis la base de données en fonction de son
     * identifiant
     *
     * @param id l'identifiant du collaborateur à récupérer
     * @return l'objet Collaborateur correspondant à l'identifiant donné, ou
     * null si aucun collaborateur correspondant n'est trouvé
     */
    @Override
    public Collaborateur read(Integer id) {
        Collaborateur obj = null;
        String sql = "SELECT * FROM collaborateur WHERE id_collaborateur=?";
        PreparedStatement pstmt;
        try {
            pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                obj = new Collaborateur();
                obj.setId(rs.getInt("id_collaborateur"));
                obj.setMatricule(rs.getInt("matricule"));
                obj.setNom(rs.getString("nom"));
                obj.setPrenom(rs.getString("prenom"));
                obj.setMail_1(rs.getString("mail_1"));
                obj.setMail_2(rs.getString("mail_2"));
                obj.setTelephone_personnel(rs.getString("telephone_personnel"));
                obj.setStatut(rs.getString("statut"));
                obj.setCategorie(rs.getString("categorie"));
                obj.setGenre(rs.getString("genre"));
                obj.setRqth(rs.getString("rqth"));
                if (rs.getDate("date_de_renouvellement") != null) {
                    obj.setDate_de_renouvellement(rs.getDate("date_de_renouvellement").toLocalDate());
                }
                obj.setMetier(rs.getString("metier"));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la lecture : " + ex.getMessage());
        }
        return obj;
    }

    /**
     * Met à jour les informations d'un collaborateur dans la base de données
     *
     * @param collaborateur l'objet Collaborateur contenant les nouvelles
     * informations à mettre à jour
     * @throws SQLException si une erreur SQL survient lors de l'exécution de la
     * mise à jour
     */
//    @Override
//    public void update(Collaborateur collaborateur) throws SQLException {
//
//        String sql = "UPDATE collaborateur SET matricule=?, nom=?, prenom=?, mail_1=?, mail_2=?, telephone_personnel=?, statut=?, categorie=?, genre=?, rqth=?, date_de_renouvellement=?, metier=?"
//                + "WHERE id_collaborateur=?";
//        
//
//        try {
//            PreparedStatement pstmt = connexion.prepareStatement(sql);
//            pstmt.setInt(1, collaborateur.getMatricule());
//            pstmt.setString(2, collaborateur.getNom());
//            pstmt.setString(3, collaborateur.getPrenom());
//            pstmt.setString(4, collaborateur.getMail_1());
//            pstmt.setString(5, collaborateur.getMail_2());
//            pstmt.setString(6, collaborateur.getTelephone_personnel());
//            pstmt.setString(7, collaborateur.getStatut());
//            pstmt.setString(8, collaborateur.getCategorie());
//            pstmt.setString(9, collaborateur.getGenre());
//            pstmt.setString(10, collaborateur.getRqth());
//            LocalDate localDate = collaborateur.getDate_de_renouvellement();
//            if (localDate != null) {
//                pstmt.setDate(11, java.sql.Date.valueOf(localDate));
//            } else {
//                pstmt.setDate(11, null);
//            }
//
//            pstmt.setString(12, collaborateur.getMetier());
//            pstmt.setInt(13, collaborateur.getId());
//
//            pstmt.executeUpdate();
//
////             Mettre à jour la table des responsables d'activité associés au collaborateur
//            updateResponsablesActivite(connexion, collaborateur.getId(), collaborateur.getResponsablesIds());
//
//        } catch (SQLException ex) {
//            System.out.println("Erreur lors de l'update : " + ex.getMessage());
//        }
//    }
//    public void update(Collaborateur collaborateur) throws SQLException {
//        String sql = "UPDATE collaborateur SET matricule=?, nom=?, prenom=?, mail_1=?, mail_2=?, telephone_personnel=?, statut=?, categorie=?, genre=?, rqth=?, date_de_renouvellement=?, metier=?"
//                + "WHERE id_collaborateur=?";
//
//        try {
//// Récupérer les informations du collaborateur avant la mise à jour
//            Collaborateur collaborateurAvant = getCollaborateur(connexion, collaborateur.getId());
//            // Préparer la requête d'update
//            PreparedStatement pstmt = connexion.prepareStatement(sql);
//            pstmt.setInt(1, collaborateur.getMatricule());
//            pstmt.setString(2, collaborateur.getNom());
//            pstmt.setString(3, collaborateur.getPrenom());
//            pstmt.setString(4, collaborateur.getMail_1());
//            pstmt.setString(5, collaborateur.getMail_2());
//            pstmt.setString(6, collaborateur.getTelephone_personnel());
//            pstmt.setString(7, collaborateur.getStatut());
//            pstmt.setString(8, collaborateur.getCategorie());
//            pstmt.setString(9, collaborateur.getGenre());
//            pstmt.setString(10, collaborateur.getRqth());
//
//            LocalDate localDate = collaborateur.getDate_de_renouvellement();
//            if (localDate != null) {
//                pstmt.setDate(11, java.sql.Date.valueOf(localDate));
//            } else {
//                pstmt.setDate(11, null);
//            }
//
//            pstmt.setString(12, collaborateur.getMetier());
//            pstmt.setInt(13, collaborateur.getId());
//
//            // Exécuter la mise à jour
//            int rowsAffected = pstmt.executeUpdate();
//
//            // Vérifier si la mise à jour a réussi
//            if (rowsAffected > 0) {
//                // Mettre à jour les autres tables ou informations si nécessaire
//                updateResponsablesActivite(connexion, collaborateur.getId(), collaborateur.getResponsablesIds());
//
//                // Récupérer le collaborateur après la mise à jour (éventuellement mis à jour par des triggers, etc.)
//                Collaborateur collaborateurApres = getCollaborateur(connexion, collaborateur.getId());
//
//                // Journalisation avant et après la modification
//                CSVUtil.writeHistory("Modification de collaborateur", collaborateurAvant);
//                CSVUtil.writeHistory("Après modification", collaborateurApres);
//
//            }
//
//        } catch (SQLException ex) {
//            System.out.println("Erreur lors de l'update : " + ex.getMessage());
//        }
//    }
    public void update(Collaborateur collaborateur) throws SQLException {
        String sql = "UPDATE collaborateur SET matricule=?, nom=?, prenom=?, mail_1=?, mail_2=?, telephone_personnel=?, statut=?, categorie=?, genre=?, rqth=?, date_de_renouvellement=?, metier=?"
                + "WHERE id_collaborateur=?";
        String sqlInsertHistorique = "INSERT INTO historique (date_action, action, table_originale, id_element,  ancienne_valeur, nouvelle_valeur) "
                + "VALUES ( ?, ?, ?, ?, ?, ?)";

        try {

// Récupérer les informations du collaborateur avant la mise à jour
            Collaborateur collaborateurAvant = getCollaborateur(connexion, collaborateur.getId());
            // Préparer la requête d'update
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, collaborateur.getMatricule());
            pstmt.setString(2, collaborateur.getNom());
            pstmt.setString(3, collaborateur.getPrenom());
            pstmt.setString(4, collaborateur.getMail_1());
            pstmt.setString(5, collaborateur.getMail_2());
            pstmt.setString(6, collaborateur.getTelephone_personnel());
            pstmt.setString(7, collaborateur.getStatut());
            pstmt.setString(8, collaborateur.getCategorie());
            pstmt.setString(9, collaborateur.getGenre());
            pstmt.setString(10, collaborateur.getRqth());

            LocalDate localDate = collaborateur.getDate_de_renouvellement();
            if (localDate != null) {
                pstmt.setDate(11, java.sql.Date.valueOf(localDate));
            } else {
                pstmt.setDate(11, null);
            }

            pstmt.setString(12, collaborateur.getMetier());
            pstmt.setInt(13, collaborateur.getId());
//  Mettre à jour les autres tables ou informations si nécessaire
            updateResponsablesActivite(connexion, collaborateur.getId(), collaborateur.getResponsablesIds());
            // Exécuter la mise à jour
            int rowsAffected = pstmt.executeUpdate();

            // Vérifier si la mise à jour a réussi
            if (rowsAffected > 0) {

                 PreparedStatement pstmtInsertHistorique = connexion.prepareStatement(sqlInsertHistorique);
                 pstmtInsertHistorique.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmtInsertHistorique.setString(2, "mise à jour");
            
            pstmtInsertHistorique.setString(3, "collaborateur");
            pstmtInsertHistorique.setInt(4, collaborateur.getId());
            
                         // Date d'action actuelle

            pstmtInsertHistorique.setString(5, collaborateurToJson(collaborateurAvant)); // Convertir en JSON ou autre format texte
            pstmtInsertHistorique.setString(6, collaborateurToJson(collaborateur)); // Convertir en JSON ou autre format texte

            pstmtInsertHistorique.executeUpdate();
        
//               
                // Récupérer le collaborateur après la mise à jour (éventuellement mis à jour par des triggers, etc.)
                Collaborateur collaborateurApres = getCollaborateur(connexion, collaborateur.getId());

                // Journalisation avant et après la modification
                CSVUtil.writeHistory("Modification de collaborateur", collaborateurAvant);
                CSVUtil.writeHistory("Après modification", collaborateurApres);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erreur lors de l'update : " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(CollaborateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Collaborateur getCollaborateur(Connection connexion, int idCollaborateur) throws SQLException {
        Collaborateur collaborateur = null;
        String sql = "SELECT * FROM collaborateur WHERE id_collaborateur=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, idCollaborateur);
            rs = pstmt.executeQuery();

            if (rs.next()) {

                collaborateur = new Collaborateur();

                collaborateur.setId(rs.getInt("id_collaborateur"));
                collaborateur.setMatricule(rs.getInt("matricule"));
                collaborateur.setNom(rs.getString("nom"));
                collaborateur.setPrenom(rs.getString("prenom"));
                collaborateur.setMail_1(rs.getString("mail_1"));
                collaborateur.setMail_2(rs.getString("mail_2"));
                collaborateur.setTelephone_personnel(rs.getString("telephone_personnel"));
                collaborateur.setStatut(rs.getString("statut"));
                collaborateur.setCategorie(rs.getString("categorie"));
                collaborateur.setGenre(rs.getString("genre"));
                collaborateur.setRqth(rs.getString("rqth"));

                if (rs.getDate("date_de_renouvellement") != null) {
                    collaborateur.setDate_de_renouvellement(rs.getDate("date_de_renouvellement").toLocalDate());
                }

                collaborateur.setMetier(rs.getString("metier"));
            }
            
            ResponsableActiviteDao responsableActiviteDao = new ResponsableActiviteDao();
            Collection<ResponsableActivite> listResponsableActiviteCollaborateur = responsableActiviteDao.listResponsableActivite(collaborateur.getId());
            
            if (collaborateur != null && !listResponsableActiviteCollaborateur.isEmpty()) {
                List<Integer> responsablesIds = new ArrayList<>();
                for(ResponsableActivite resp : listResponsableActiviteCollaborateur){
                    responsablesIds.add(resp.getId());
                }

                collaborateur.setResponsableIds(responsablesIds);
            }
            
            

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }

        }

        return collaborateur;
    }

    /**
     * Met à jour la table des responsables d'activité associés à un
     * collaborateur dans la base de données.
     *
     * @param conn la connexion à la base de données
     * @param collaborateurId l'identifiant du collaborateur pour lequel les
     * responsables d'activité sont mis à jour
     * @param responsableIds la liste des identifiants des responsables
     * d'activité à associer au collaborateur
     * @throws SQLException si une erreur SQL survient lors de l'exécution des
     * opérations de mise à jour
     */
// Méthode pour mettre à jour la table des responsables d'activité associés au collaborateur
    private void updateResponsablesActivite(Connection conn, int collaborateurId, List<Integer> responsableIds) throws SQLException {
        // Supprimer tous les enregistrements associés au collaborateur
        if (responsableIds != null) {
            String deleteSql = "DELETE FROM posseder WHERE id_collaborateur=?";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                deleteStmt.setInt(1, collaborateurId);
                deleteStmt.executeUpdate();
            }

            // Insérer les nouveaux enregistrements
            String insertSql = "INSERT INTO posseder (id_collaborateur, id_ra) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                for (int responsableId : responsableIds) {
                    insertStmt.setInt(1, collaborateurId);
                    insertStmt.setInt(2, responsableId);
                    insertStmt.executeUpdate();
                }
            }
        }
    }

//    
//    public void desactiver(Integer collaborateurId) {
//        // Logique pour désactiver le collaborateur dans la base de données
//        // Par exemple :
//        String sql = "UPDATE collaborateur SET statut = 'désactivé' WHERE id_collaborateur = ?";
//        try {
//            PreparedStatement pstmt = connexion.prepareStatement(sql);
//            pstmt.setInt(1, collaborateurId);
//            pstmt.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("Erreur lors de l'update : " + ex.getMessage());
//        }
//    }
    /**
     * Supprime un collaborateur de la base de données en fonction de son
     * identifiant.
     *
     *
     * @param collaborateurId l'identifiant du collaborateur à supprimer
     */
    public void delete(Integer collaborateurId) throws IOException {
        String sql = "DELETE FROM collaborateur WHERE id_collaborateur=?";
        try {
            // Récupérer les informations du collaborateur avant la suppression
            Collaborateur collaborateur = getCollaborateur(connexion, collaborateurId);
           

            

            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, collaborateurId);
            pstmt.executeUpdate();
            
            
            // Enregistrer historique dans la BDD
            String sqlInsertHistorique = "INSERT INTO historique (date_action, action, table_originale, id_element,  ancienne_valeur, nouvelle_valeur) "
                + "VALUES ( ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmtInsertHistorique = connexion.prepareStatement(sqlInsertHistorique);
                 pstmtInsertHistorique.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmtInsertHistorique.setString(2, "suppression");
            
            pstmtInsertHistorique.setString(3, "collaborateur");
            pstmtInsertHistorique.setInt(4, collaborateur.getId());
            
                         // Date d'action actuelle

            pstmtInsertHistorique.setString(5, collaborateurToJson(collaborateur)); // Convertir en JSON ou autre format texte
            pstmtInsertHistorique.setString(6, null); // Convertir en JSON ou autre format texte

            pstmtInsertHistorique.executeUpdate();
            
            
            
            // Enregistrer les informations dans le fichier CSV
            CSVUtil.writeHistory("Avant suppression de collaborateur", collaborateur);

            System.out.println("Collaborateur supprimé avec succès.");

        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'update : " + ex.getMessage());
        }
    }

    /**
     * Renvoie une connexion à la base de données.
     *
     * @return une connexion à la base de données
     */
    private Connection getConnection() {
        return null;
    }

    /**
     * Récupère une collection de données combinées de Collaborateur,
     * Prestation, Responsable d'activité et Partenaire associés.
     *
     * @return une collection de CollaborateurPrestationPartenaireRa contenant
     * des informations détaillées sur chaque collaborateur et ses prestations
     * associées
     */
    public Collection<CollaborateurPrestationPartenaireRa> listCollaborateurPrestationPartenaireRa() {
        ArrayList<CollaborateurPrestationPartenaireRa> list = new ArrayList<>();
        Collection<Collaborateur> listeCollaborateur = list();
        for (Collaborateur collab : listeCollaborateur) {
            CollaborateurPrestationPartenaireRa collabPrestPartRa = new CollaborateurPrestationPartenaireRa();
            collabPrestPartRa.setCollaborateur(collab);

            Collection<Prestation> prestations = listPrestationCollaborateur(collab.getId());
            ArrayList<PrestationRaPartenaire> listPrestationsRaPart = new ArrayList<>();
            for (Prestation presta : prestations) {
                PrestationRaPartenaire prestRaPart = new PrestationRaPartenaire();
                prestRaPart.setPrestation(presta);
                prestRaPart.setResp(DaoFactory.ResponsableActiviteDao().read(presta.getId_ra()));
                prestRaPart.setPart(DaoFactory.getPartenaireDao().read(presta.getId_partenaire()));
                listPrestationsRaPart.add(prestRaPart);
            }
            collabPrestPartRa.setPrestRaPart(listPrestationsRaPart);
            collabPrestPartRa.setPrestationActive(!listPrestationCollaborateur(collab.getId()).isEmpty());
            list.add(collabPrestPartRa);
        }
        return list;
    }

    /**
     * Récupère une collection de collaborateurs associés à un responsable
     * d'activité donné.
     *
     * @param idRa l'identifiant du responsable d'activité pour lequel récupérer
     * les collaborateurs associés
     * @return une collection de Collaborateur représentant les collaborateurs
     * associés au responsable d'activité spécifié
     */
    public Collection<Collaborateur> listCollaborateur(int idRa) {
        String sql = "SELECT id_collaborateur FROM posseder WHERE id_ra=?";
        ArrayList<Collaborateur> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, idRa);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int idCollaborateur = rs.getInt("id_collaborateur");
                Collaborateur collaborateur = DaoFactory.getCollaborateurDao().read(idCollaborateur);
                list.add(collaborateur);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la vérification de l'existence : " + ex.getMessage());
        }
        return list;
    }

    /**
     * Récupère une collection de prestations associées à un collaborateur donné
     *
     * @param idCollaborateur l'identifiant du collaborateur pour lequel
     * récupérer les prestations associées
     * @return une collection de Prestation représentant les prestations
     * associées au collaborateur spécifié
     */
    public Collection<Prestation> listPrestationCollaborateur(int idCollaborateur) {
        String sql = "SELECT * FROM prestation WHERE id_collaborateur=?";
        ArrayList<Prestation> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, idCollaborateur);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int idPrestation = rs.getInt("id_prestation");
                Prestation prestation = DaoFactory.getPrestationDao().read(idPrestation);
                list.add(prestation);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la vérification de l'existence : " + ex.getMessage());
        }
        return list;
    }

    /**
     * Vérifie si un collaborateur avec le matricule spécifié existe dans la
     * base de données.
     *
     * @param matricule le matricule du collaborateur à vérifier
     * @return true si un collaborateur avec le matricule spécifié existe, false
     * sinon
     */
    public boolean exists(int matricule) {
        String sql = "SELECT 1 FROM collaborateur WHERE matricule=?";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, matricule);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la vérification de l'existence : " + ex.getMessage());
        }
        return false;
    }

    /**
     * Vérifie si un collaborateur avec le matricule spécifié existe dans la
     * base de données, à l'exception du collaborateur avec l'ID spécifié
     *
     * @param matricule le matricule du collaborateur à vérifier
     * @param idCollaborateur l'ID du collaborateur à exclure de la vérification
     * @return true si un autre collaborateur avec le matricule spécifié existe,
     * false sinon
     */
    public boolean existsForOtherCollab(int matricule, int idCollaborateur) {
        String sql = "SELECT 1 FROM collaborateur WHERE matricule=? and id_collaborateur != ?";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, matricule);
            pstmt.setInt(2, idCollaborateur);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la vérification de l'existence : " + ex.getMessage());
        }
        return false;
    }

    /**
     * Récupère la liste complète des collaborateurs depuis la base de données.
     *
     * @return une collection contenant tous les collaborateurs existants
     */
    @Override
    public Collection<Collaborateur> list() {
        ArrayList<Collaborateur> list = new ArrayList<>();
        String sql = "SELECT * FROM collaborateur";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Collaborateur c = new Collaborateur();
                c.setId(rs.getInt("id_collaborateur"));
                c.setMatricule(rs.getInt("matricule"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setStatut(rs.getString("statut"));
                c.setMetier(rs.getString("metier"));

                list.add(c);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors du listage : " + ex.getMessage());
        }
        return list;
    }

    /**
     * Recherche les collaborateurs dont le nom correspond partiellement au nom
     * spécifié.
     *
     * @param nom le nom partiel à rechercher
     * @return une liste des collaborateurs correspondants trouvés
     * @throws SQLException si une erreur SQL survient pendant l'exécution de la
     * requête
     */
    public List<Collaborateur> rechercherParNom(String nom) throws SQLException {
        return rechercher("SELECT * FROM collaborateur WHERE nom LIKE ?", nom);
    }

    /**
     * Recherche les collaborateurs dont le prénom correspond partiellement au
     * prénom spécifié.
     *
     * @param prenom le prénom partiel à rechercher
     * @return une liste des collaborateurs correspondants trouvés
     * @throws SQLException si une erreur SQL survient pendant l'exécution de la
     * requête
     */
    public List<Collaborateur> rechercherParPrenom(String prenom) throws SQLException {
        return rechercher("SELECT * FROM collaborateur WHERE prenom LIKE ?", prenom);
    }

    /**
     * Recherche les collaborateurs dont le matricule correspond partiellement
     * au matricule spécifié.
     *
     * @param matricule le matricule partiel à rechercher
     * @return une liste des collaborateurs correspondants trouvés
     * @throws SQLException si une erreur SQL survient pendant l'exécution de la
     * requête
     */
    public List<Collaborateur> rechercherParMatricule(String matricule) throws SQLException {
        return rechercher("SELECT * FROM collaborateur WHERE matricule LIKE ?", matricule);
    }

    /**
     * Effectue une recherche de collaborateurs basée sur une requête SQL
     * paramétrée.
     *
     * @param sql la requête SQL paramétrée utilisée pour la recherche
     * @param param la valeur du paramètre à utiliser dans la requête SQL (pour
     * la clause LIKE)
     * @return une liste de collaborateurs correspondant aux critères de
     * recherche
     * @throws SQLException si une erreur SQL survient pendant l'exécution de la
     * requête
     */
    private List<Collaborateur> rechercher(String sql, String param) throws SQLException {
        List<Collaborateur> collaborateurs = new ArrayList<>();
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, "%" + param + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Collaborateur collaborateur = new Collaborateur();
                    collaborateur.setId(rs.getInt("id_collaborateur"));
                    collaborateur.setMatricule(rs.getInt("matricule"));
                    collaborateur.setNom(rs.getString("nom"));
                    collaborateur.setPrenom(rs.getString("prenom"));
//                    collaborateur.setMail_1(rs.getString("mail_1"));
//                    collaborateur.setMail_2(rs.getString("mail_2"));
//                collaborateur.setTelephone_personnel(rs.getString("telephone_personnel"));
                    collaborateur.setStatut(rs.getString("statut"));
//                collaborateur.setCategorie(rs.getString("categorie"));
//                collaborateur.setGenre(rs.getString("genre"));
//                collaborateur.setRqth(rs.getString("rqth"));
//             collaborateur.setDate_de_renouvellement(rs.getDate("date_de_renouvellement").toLocalDate());
                    collaborateur.setMetier(rs.getString("metier"));
                    collaborateurs.add(collaborateur);
                }
            }
        }
        return collaborateurs;
    }

    /**
     * Récupère l'identifiant le plus élevé parmi ceux créés dans la table des
     * collaborateurs.
     *
     * @return l'identifiant le plus élevé (MAX) présent dans la table des
     * collaborateurs, ou 0 si aucun enregistrement n'est trouvé
     */
    public int getLastIdCreated() {
        String sql = "SELECT MAX(id_collaborateur) AS max_id FROM collaborateur";
        int maxId = 0;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt("max_id");
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'exécution de la requête : " + ex.getMessage());
        }
        return maxId;
    }

}