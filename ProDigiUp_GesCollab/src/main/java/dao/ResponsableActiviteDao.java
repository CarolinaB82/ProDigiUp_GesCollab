package dao;

import entities.Collaborateur;
import entities.Partenaire;
import entities.Prestation;
import entities.ResponsableActivite;
import historique.CSVUtil;
import static historique.JsonUtil.responsableActiviteToJson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation d'une DAO Gestion du CRUD sur les objets ResponsableActivite
 * dans la base de données relationnelles Connexion a la BDD Constructeur
 * Methodes CRUD Gestion des exceptions Methodes specifiques La classe
 * ResponsableActiviteDao encapsule la logique d'accès aux données pour les
 * objets Ra
 *
 * @author asolanas
 */
public class ResponsableActiviteDao extends Dao<ResponsableActivite> {

    public ResponsableActiviteDao() {
        super("ra");
    }

    /**
     * Crée un objet ResponsableActivite à partir d'un ResultSet.
     *
     * @param rs le ResultSet contenant les données à partir desquelles créer
     * l'objet
     * @return un objet ResponsableActivite avec les données extraites du
     * ResultSet
     * @throws SQLException si une erreur survient lors de l'extraction des
     * données du ResultSet
     */
    @Override
    protected ResponsableActivite createObject(ResultSet rs) throws SQLException {
        ResponsableActivite ra = new ResponsableActivite() {
        };
        ra.setId(rs.getInt("id_" + table));
        ra.setMatricule(rs.getInt("matricule"));
        ra.setNom(rs.getString("nom"));
        ra.setPrenom(rs.getString("prenom"));
        ra.setMail(rs.getString("mail"));
        ra.setTelephone_professionnel(rs.getString("telephone_professionnel"));
        ra.setTelephone_personnel(rs.getString("telephone_personnel"));

        return ra;
    }

    /**
     * Crée un nouvel objet ResponsableActivite dans la base de données.
     *
     * @param ra l'objet ResponsableActivite à créer
     * @throws SQLException si une erreur survient lors de l'insertion dans la
     * base de données
     */
    @Override
    public void create(ResponsableActivite ra) throws SQLException {
        String sql = "INSERT INTO ra(matricule, nom, prenom, mail, telephone_professionnel, telephone_personnel) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, ra.getMatricule());
            pstmt.setString(2, ra.getNom());
            pstmt.setString(3, ra.getPrenom());
            pstmt.setString(4, ra.getMail());
            pstmt.setString(5, ra.getTelephone_professionnel());
            pstmt.setString(6, ra.getTelephone_personnel());
            int nbLines = pstmt.executeUpdate();
            if (nbLines == 1) {
                ResultSet autoGeneratedKeys = pstmt.getGeneratedKeys();
                autoGeneratedKeys.first();
                int id = autoGeneratedKeys.getInt(1);
                ra.setId(id);
                System.out.println("Responsable d'activité créé avec succès!");
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'insertion : " + ex.getMessage());
            throw ex;
        }
    }

    /**
     * Récupère un ResponsableActivite à partir de son ID.
     *
     * @param id l'identifiant du ResponsableActivite à récupérer
     * @return le ResponsableActivite trouvé, ou null s'il n'existe pas
     */
    @Override
    public ResponsableActivite read(Integer id) {
        ResponsableActivite ra = null;
        String sql = "SELECT * FROM ra WHERE id_ra=?";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            System.out.println("Connexion établie : " + (connexion != null));
            pstmt.setInt(1, id);
            System.out.println("Exécution de la requête SQL : " + sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                ra = new ResponsableActivite();
                ra.setId(rs.getInt("id_ra"));
                ra.setMatricule(rs.getInt("matricule"));
                ra.setNom(rs.getString("nom"));
                ra.setPrenom(rs.getString("prenom"));
                ra.setMail(rs.getString("mail"));
                ra.setTelephone_professionnel(rs.getString("telephone_professionnel"));
                ra.setTelephone_personnel(rs.getString("telephone_personnel"));
                System.out.println("ResponsableActivite trouvé : " + ra);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture : " + e.getMessage());
            e.printStackTrace(); // Imprimer la pile d'erreurs pour un meilleur débogage
        }
        return ra;
    }

    /**
     * Met à jour les associations entre un responsable d'activité et des
     * partenaires dans la table "proposer". Supprime tous les enregistrements
     * existants associés au responsable d'activité spécifié et insère de
     * nouveaux enregistrements pour les partenaireIds fournis.
     *
     * @param conn Connexion à la base de données.
     * @param responsableActiviteId Identifiant du responsable d'activité pour
     * lequel les associations sont mises à jour.
     * @param partenaireIds Liste des identifiants des partenaires à associer au
     * responsable d'activité.
     * @throws SQLException Si une erreur survient lors de l'exécution des
     * requêtes SQL.
     */
    private void updatePartenaire(Connection conn, int responsableActiviteId, List<Integer> partenaireIds) throws SQLException {
        // Supprimer tous les enregistrements associés au collaborateur
        if (partenaireIds != null) {
            String deleteSql = "DELETE FROM proposer WHERE id_ra=?";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                deleteStmt.setInt(1, responsableActiviteId);
                deleteStmt.executeUpdate();
            }

            // Insérer les nouveaux enregistrements
            String insertSql = "INSERT INTO proposer (id_ra, id_partenaire) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                for (int partenaireId : partenaireIds) {
                    insertStmt.setInt(1, responsableActiviteId);
                    insertStmt.setInt(2, partenaireId);
                    insertStmt.executeUpdate();
                }
            }
        }
    }

    /**
     * Met à jour les associations entre un responsable d'activité et des
     * collaborateurs dans la table "posseder". Supprime tous les
     * enregistrements existants associés au responsable d'activité spécifié et
     * insère de nouveaux enregistrements pour les collaborateurIds fournis.
     *
     * @param conn Connexion à la base de données.
     * @param responsableActiviteId Identifiant du responsable d'activité pour
     * lequel les associations sont mises à jour.
     * @param collaborateurIds Liste des identifiants des collaborateurs à
     * associer au responsable d'activité.
     * @throws SQLException Si une erreur survient lors de l'exécution des
     * requêtes SQL.
     */
    private void updateCollaborateur(Connection conn, int responsableActiviteId, List<Integer> collaborateurIds) throws SQLException {
        // Supprimer tous les enregistrements associés au collaborateur
        if (collaborateurIds != null) {
            String deleteSql = "DELETE FROM posseder WHERE id_ra=?";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                deleteStmt.setInt(1, responsableActiviteId);
                deleteStmt.executeUpdate();
            }

            // Insérer les nouveaux enregistrements
            String insertSql = "INSERT INTO posseder (id_ra, id_collaborateur) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                for (int collaborateurId : collaborateurIds) {
                    insertStmt.setInt(1, responsableActiviteId);
                    insertStmt.setInt(2, collaborateurId);
                    insertStmt.executeUpdate();
                }
            }
        }

    }

    /**
     * Met à jour les informations d'un responsable d'activité dans la base de
     * données. Actualise les données du responsable d'activité spécifié et met
     * à jour ses associations avec les partenaires et collaborateurs dans les
     * tables associatives correspondantes.
     *
     * @param responsableActivite Objet ResponsableActivite contenant les
     * nouvelles informations à mettre à jour.
     */
    @Override
    public void update(ResponsableActivite responsableActivite) {
        String sql = "UPDATE ra SET matricule=?, nom=?, prenom=?, mail=?, telephone_professionnel=?, telephone_personnel=?"
                + "WHERE id_ra=?";
        String sqlInsertHistorique = "INSERT INTO historique (date_action, action, table_originale, id_element,  ancienne_valeur, nouvelle_valeur) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {

            ResponsableActivite raAvant = getResponsableActivite(connexion, responsableActivite.getId());
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, responsableActivite.getMatricule());
            pstmt.setString(2, responsableActivite.getNom());
            pstmt.setString(3, responsableActivite.getPrenom());
            pstmt.setString(4, responsableActivite.getMail());
            pstmt.setString(5, responsableActivite.getTelephone_professionnel());
            pstmt.setString(6, responsableActivite.getTelephone_personnel());
            pstmt.setInt(7, responsableActivite.getId());

            pstmt.executeUpdate();
            updatePartenaire(connexion, responsableActivite.getId(), responsableActivite.getPartenaireIds());
            updateCollaborateur(connexion, responsableActivite.getId(), responsableActivite.getCollaborateurIds());

            int rowsAffected = pstmt.executeUpdate();

            // Vérifier si la mise à jour a réussi
            if (rowsAffected > 0) {
                
                 PreparedStatement pstmtInsertHistorique = connexion.prepareStatement(sqlInsertHistorique);
                 pstmtInsertHistorique.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmtInsertHistorique.setString(2, "mise à jour");
            
            pstmtInsertHistorique.setString(3, "responsableActivite");
            pstmtInsertHistorique.setInt(4, responsableActivite.getId());
            
                         // Date d'action actuelle

            pstmtInsertHistorique.setString(5, responsableActiviteToJson(raAvant)); // Convertir en JSON ou autre format texte
            pstmtInsertHistorique.setString(6, responsableActiviteToJson(responsableActivite)); // Convertir en JSON ou autre format texte

            pstmtInsertHistorique.executeUpdate();
        
                
                ResponsableActivite raApres = getResponsableActivite(connexion, responsableActivite.getId());
                // Journalisation avant et après la modification
                CSVUtil.writeHistory("Modification de ra", raAvant);
                CSVUtil.writeHistory("Après modification", raApres);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erreur lors de l'update : " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ResponsableActiviteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResponsableActivite getResponsableActivite(Connection connexion, int idResponsableActivite) throws SQLException {
        ResponsableActivite ra = null;
        String sql = "SELECT * FROM ra WHERE id_ra=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, idResponsableActivite);
            rs = pstmt.executeQuery();

            if (rs.next()) {

                ra = new ResponsableActivite();

                ra.setId(rs.getInt("id_ra"));
                ra.setMatricule(rs.getInt("matricule"));
                ra.setNom(rs.getString("nom"));
                ra.setPrenom(rs.getString("prenom"));
                ra.setMail(rs.getString("mail"));

                ra.setTelephone_professionnel(rs.getString("telephone_professionnel"));
                ra.setTelephone_personnel(rs.getString("telephone_personnel"));

                
            
            PartenaireDao partenaireDao = new PartenaireDao();
                    Collection<Partenaire> listPartenaireRa = partenaireDao.listPartenaire(ra.getId());

                    if (!listPartenaireRa.isEmpty()){
                        List<Integer> partenairesIds = new ArrayList<>();
                        for(Partenaire part : listPartenaireRa){
                            partenairesIds.add(part.getId());
                        }
                        
                        ra.setPartenaireIds(partenairesIds);
                    }
                    
                    CollaborateurDao collaborateurDao = new CollaborateurDao();
                    Collection <Collaborateur> listCollaborateurRa = collaborateurDao.listCollaborateur(ra.getId());
                    
                    if (!listCollaborateurRa.isEmpty()){
                        List<Integer> collaborateurIds = new ArrayList<>();
                        for (Collaborateur collab : listCollaborateurRa){
                            collaborateurIds.add(collab.getId());
                        }
                        ra.setCollaborateurIds (collaborateurIds);
                    }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }

        }

        return ra;
    }

    /**
     * Supprime un responsable d'activité de la base de données.
     *
     * @param id Identifiant du responsable d'activité à supprimer.
     * @throws java.io.IOException
     */
    public void delete(Integer id) throws IOException{
        String sql = "DELETE FROM ra WHERE id_ra=?";
        try {
             // Récupérer les informations du collaborateur avant la suppression
            ResponsableActivite ra = getResponsableActivite(connexion, id);
             CSVUtil.writeHistory("Avant suppression du ra", ra);
             
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
            
            // Enregistrer historique dans la BDD
            String sqlInsertHistorique = "INSERT INTO historique (date_action, action, table_originale, id_element,  ancienne_valeur, nouvelle_valeur) "
                + "VALUES ( ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmtInsertHistorique = connexion.prepareStatement(sqlInsertHistorique);
                 pstmtInsertHistorique.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmtInsertHistorique.setString(2, "suppression");
            
            pstmtInsertHistorique.setString(3, "ra");
            pstmtInsertHistorique.setInt(4, ra.getId());
            
                         // Date d'action actuelle

            pstmtInsertHistorique.setString(5, responsableActiviteToJson(ra)); // Convertir en JSON ou autre format texte
            pstmtInsertHistorique.setString(6, null); // Convertir en JSON ou autre format texte

            pstmtInsertHistorique.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'update : " + ex.getMessage());
        }
    }

    /**
     * Vérifie l'existence d'un responsable d'activité dans la base de données à
     * partir de son matricule.
     *
     * @param matricule Matricule du responsable d'activité à vérifier.
     * @return Vrai si un responsable d'activité avec le matricule spécifié
     * existe dans la base de données, sinon faux.
     */
    public boolean exists(int matricule) {
        String sql = "SELECT 1 FROM ra WHERE matricule=?";
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
     * Récupère la liste des responsables d'activité associés à un collaborateur
     * spécifié.
     *
     * @param idCollaborateur Identifiant du collaborateur pour lequel récupérer
     * les responsables d'activité.
     * @return Une collection contenant les responsables d'activité associés au
     * collaborateur spécifié.
     */
    public Collection<ResponsableActivite> listResponsableActivite(int idCollaborateur) {
        String sql = "SELECT id_ra FROM posseder WHERE id_collaborateur=?";
        ArrayList<ResponsableActivite> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, idCollaborateur);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int idRa = rs.getInt("id_ra");
                ResponsableActivite responsableActive = DaoFactory.ResponsableActiviteDao().read(idRa);
                list.add(responsableActive);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la vérification de l'existence : " + ex.getMessage());
        }
        return list;
    }

    /**
     * Récupère la liste des responsables d'activité associés à un partenaire
     * spécifié.
     *
     * @param idPartenaire Identifiant du partenaire pour lequel récupérer les
     * responsables d'activité.
     * @return Une collection contenant les responsables d'activité associés au
     * partenaire spécifié.
     */
    public Collection<ResponsableActivite> listResponsablesActivite(int idPartenaire) {
        String sql = "SELECT id_ra FROM proposer WHERE id_partenaire=?";
        ArrayList<ResponsableActivite> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, idPartenaire);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int idRa = rs.getInt("id_ra");
                ResponsableActivite responsableActive = DaoFactory.ResponsableActiviteDao().read(idRa);
                list.add(responsableActive);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la vérification de l'existence : " + ex.getMessage());
        }
        return list;
    }

    /**
     * Récupère la liste complète des responsables d'activité depuis la base de
     * données.
     *
     * @return Une collection contenant tous les responsables d'activité
     * présents dans la base de données.
     */
    @Override
    public Collection<ResponsableActivite> list() {
        ArrayList<ResponsableActivite> list = new ArrayList<>();
        String sql = "SELECT * FROM ra";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ResponsableActivite ra = new ResponsableActivite();
                ra.setId(rs.getInt("id_ra"));
                ra.setMatricule(rs.getInt("matricule"));
                ra.setNom(rs.getString("nom"));
                ra.setPrenom(rs.getString("prenom"));
                list.add(ra);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du listage : " + e.getMessage());
        }
        return list;
    }

    /**
     * Recherche les responsables d'activité dans la base de données par leur
     * matricule.
     *
     * @param matricule Le matricule à rechercher (peut être partiel ou
     * complet).
     * @return Une liste des responsables d'activité correspondant au matricule
     * spécifié.
     * @throws SQLException Si une erreur survient lors de l'exécution de la
     * requête SQL.
     */
    public List<ResponsableActivite> rechercherRaParMatricule(String matricule) throws SQLException {
        return rechercherRa("SELECT * FROM ra WHERE matricule LIKE ?", matricule);
    }

    /**
     * Recherche les responsables d'activité dans la base de données par leur
     * nom.
     *
     * @param nom Le nom à rechercher (peut être partiel ou complet).
     * @return Une liste des responsables d'activité correspondant au nom
     * spécifié.
     * @throws SQLException Si une erreur survient lors de l'exécution de la
     * requête SQL.
     */
    public List<ResponsableActivite> rechercherRaParNom(String nom) throws SQLException {
        return rechercherRa("SELECT * FROM ra WHERE nom LIKE ?", nom);
    }

    /**
     * Recherche les responsables d'activité dans la base de données par leur
     * prénom.
     *
     * @param prenom Le prénom à rechercher (peut être partiel ou complet).
     * @return Une liste des responsables d'activité correspondant au prénom
     * spécifié.
     * @throws SQLException Si une erreur survient lors de l'exécution de la
     * requête SQL.
     */
    public List<ResponsableActivite> rechercherRaParPrenom(String prenom) throws SQLException {
        return rechercherRa("SELECT * FROM ra WHERE prenom LIKE ?", prenom);
    }

    /**
     * Recherche des responsables d'activité dans la base de données en fonction
     * d'une requête SQL avec un paramètre de recherche.
     *
     * @param sql La requête SQL à exécuter pour récupérer les responsables
     * d'activité.
     * @param param Le paramètre à utiliser dans la requête SQL pour la
     * recherche (peut être partiel ou complet).
     * @return Une liste des responsables d'activité correspondant au critère de
     * recherche spécifié.
     * @throws SQLException Si une erreur survient lors de l'exécution de la
     * requête SQL.
     */
    private List<ResponsableActivite> rechercherRa(String sql, String param) throws SQLException {
        List<ResponsableActivite> responsablesActivites = new ArrayList<>();
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, "%" + param + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ResponsableActivite ra = new ResponsableActivite();
                    ra.setId(rs.getInt("id_ra"));
                    ra.setMatricule(rs.getInt("matricule"));
                    ra.setNom(rs.getString("nom"));
                    ra.setPrenom(rs.getString("prenom"));
                    responsablesActivites.add(ra);
                }
            }
        }
        return responsablesActivites;
    }

    /**
     * Récupère l'identifiant maximum (id_ra) parmi les responsables d'activité
     * existants dans la base de données.
     *
     * @return L'identifiant maximum des responsables d'activité créés jusqu'à
     * présent. Retourne 0 si aucun responsable d'activité n'existe.
     */
    public int getLastIdCreated() {
        String sql = "SELECT MAX(id_ra) AS max_id FROM ra";
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
    
    public Collection<Prestation> listPrestationResponsableActivite(int idResponsableActivite) {
        String sql = "SELECT * FROM prestation WHERE id_ra=?";
        ArrayList<Prestation> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, idResponsableActivite);
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
    
//     public Collection<ResponsableActivite> listPartenaireResponsableActivite(int idPartenaire) {
//        String sql = "SELECT ra.id_ra, ra.nom " +
//                 "FROM ResponsableActivite ra " +
//                 "JOIN proposer p ON ra.id_ra = p.id_ra " +
//                 "WHERE p.id_partenaire = ?";
//          List<ResponsableActivite> list = new ArrayList<>();
//    try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
//        pstmt.setInt(1, idPartenaire);
//        try (ResultSet rs = pstmt.executeQuery()) {
//            while (rs.next()) {
//                ResponsableActivite ra = new ResponsableActivite();
//                ra.setId(rs.getInt("id_ra"));
//                ra.setNom(rs.getString("nom"));
//                list.add(ra);
//            }
//        }
//    } catch (SQLException ex) {
//        System.err.println("Erreur lors de la récupération des responsables : " + ex.getMessage());
//    }
//    return list;
//}
     

}
