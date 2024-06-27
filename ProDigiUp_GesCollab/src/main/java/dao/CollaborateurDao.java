/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dao;

import entities.Collaborateur;
import entities.CollaborateurRaPartenaire;
import entities.ResponsableActivite;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.DaoFactory;
import entities.Partenaire;
import entities.ResponsableActivitePartenaire;
import java.util.List;


/**
 *
 * @author cberge
 */
public class CollaborateurDao extends Dao<Collaborateur> {

    public CollaborateurDao() {
        super("Collaborateur");
    }

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

    @Override
    protected Collaborateur createObject(ResultSet rs) throws SQLException {
        Collaborateur obj = new Collaborateur();
        obj.setId(rs.getInt("id_" + table));
        obj.setMatricule(rs.getInt("matricule"));
        obj.setNom(rs.getString("nom"));
        obj.setPrenom(rs.getString("prenom"));
        obj.setTelephone_personnel(rs.getString("telephone_personnel"));
        obj.setStatut(rs.getString("statut"));
        obj.setCategorie(rs.getString("categorie"));
        obj.setGenre(rs.getString("genre"));
        obj.setRqth(rs.getString("rqth"));
        obj.setDate_de_renouvellement(rs.getDate("date_de_renouvellement").toLocalDate());
        obj.setMetier(rs.getString("metier"));

        return obj;
    }

     @Override
    public void create(Collaborateur obj) throws SQLException{
        String sql = "INSERT INTO  collaborateur (matricule, nom, prenom, telephone_personnel, statut, categorie, genre, rqth, date_de_renouvellement, metier)"
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, obj.getMatricule());

            pstmt.setString(2, obj.getNom());
            pstmt.setString(3, obj.getPrenom());
            pstmt.setString(4, obj.getTelephone_personnel());
            pstmt.setString(5, obj.getStatut());
            pstmt.setString(6, obj.getCategorie());
            pstmt.setString(7, obj.getGenre());
            pstmt.setString(8, obj.getRqth());
             LocalDate localDate = obj.getDate_de_renouvellement();
            if (localDate != null) {
                pstmt.setDate(9, java.sql.Date.valueOf(localDate));
            } else {
                pstmt.setDate(9, null);
            }


            pstmt.setString(10, obj.getMetier());
            int nbLines = pstmt.executeUpdate();
            if (nbLines == 1) {
                ResultSet autoGeneratedKeys = pstmt.getGeneratedKeys();
                autoGeneratedKeys.first();
                int id = autoGeneratedKeys.getInt(1);
                obj.setId(id);

            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'insertion : " + ex.getMessage());
            throw ex;
            
        }
    }

    @Override
    public void update(Collaborateur obj) {
        String sql = "UPDATE collaborateur SET matricule=?, nom=?, prenom=?, telephone_personnel=?, statut=?, categorie=?, genre=?, rqth=?, date_de_renouvellement=?, metier=?"
                + "WHERE id_collaborateur=?";

        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getMatricule());
            pstmt.setString(2, obj.getNom());
            pstmt.setString(3, obj.getPrenom());
            pstmt.setString(4, obj.getTelephone_personnel());
            pstmt.setString(5, obj.getStatut());
            pstmt.setString(6, obj.getCategorie());
            pstmt.setString(7, obj.getGenre());
            pstmt.setString(8, obj.getRqth());
            LocalDate localDate = obj.getDate_de_renouvellement();
            if (localDate != null) {
                pstmt.setDate(9, java.sql.Date.valueOf(localDate));
            } else {
                pstmt.setDate(9, null);
            }

            pstmt.setString(10, obj.getMetier());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'update : " + ex.getMessage());
        }
                }
    
    public Collection<CollaborateurRaPartenaire>listCollaborateurRaPartenaire(){
        Collection<Collaborateur> listeCollaborateur = list();
        // 1. je recupere la liste des collaborateurs
        ArrayList<CollaborateurRaPartenaire> listCollaborateurRaPartenaire = new ArrayList<>();
        // 2. j'instancie ma liste CollaborateurRaPartenaire à vide
        for(Collaborateur collab : listeCollaborateur){
            // 3. je boucle sur chaque collaborateur dans ma liste des collaborateurs 
            CollaborateurRaPartenaire collabRaPart = new  CollaborateurRaPartenaire();
            // 4. j'instancie un nouvel objet de type CollaborateurRaPartenaire pour chaque collaborateur que j'apelle collabRaPart (variable)
            // mon but etant de remplir cet objet 
            collabRaPart.setCollaborateur(collab);
            // 5. pour le remplir on utilise le setCollaborateur (collab);
            Collection<ResponsableActivite> listResponsableActivite = listResponsableActivite(collab.getId());
            // 6. recuperation de la liste Responsable Activite associe au collaborateur courant (collab)
            ArrayList<ResponsableActivitePartenaire> listRaPartenaire = new ArrayList<>();
            // 7. j'instancie ma liste ResponsableActivitePartenaire à vide
            for(ResponsableActivite responsable: listResponsableActivite){
                // 8. je boucle sur chaque responsable activite dans ma liste des ra
                ResponsableActivitePartenaire respPart = new  ResponsableActivitePartenaire();
                // 9. j'instancie un nouvel objet de type ResponsableActivitePartenaire pour chaque responsable d'activite que j'apelle respPart (variable)
                // mon but etant de remplir cet objet 
                respPart.setRa(responsable);
                // 10. pour le remplir on utilise le setRa (responsable)
                Collection<Partenaire> listPartenaires = listPartenaire(responsable.getId());
                // 11. recuperation de la  liste Partenaire associe au responsable d'activite courant(responsable)
                respPart.setPartenaires(listPartenaires);
                // 12. pour remplir l'objet respPart on utilise la liste des partenaires donc : setPartenaires(listPartenaires)
                listRaPartenaire.add(respPart);
                // 13. pour remplir ma listRaPartenaire qui etait instancier a vide au .7 
                //j'y ajoute les infos du respPart 
                // but que mon ra et mon partenaire soient liés
            }
            collabRaPart.setRaPartenaires(listRaPartenaire);
            // 14. Remplissage de l'objet CollaborateurRaPartenaire avec la liste des ResponsableActivitePartenaire
            // but : pour remplir l'objet collabRaPart on utilise le setRaPartenaires (listRaPartenaire) qui associe les ra aux partenaires
            listCollaborateurRaPartenaire.add(collabRaPart);
            // 15. Ajout de l'objet CollaborateurRaPartenaire à la liste des CollaborateurRaPartenaire
            //pour remplir ma listCollaborateurRaPartenaire qui etait instancier a vide au .2 j'y ajoute mes infos
                // du (collabRaPart) pour que mon collab , mon ra et mon partenaire soient liés
        }
        return listCollaborateurRaPartenaire;
        // 16. je retourne cette liste
    }
    
    public Collection<ResponsableActivite> listResponsableActivite(int idCollaborateur){
        String sql = "SELECT id_ra FROM posseder WHERE id_collaborateur=?";
        ArrayList<ResponsableActivite> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, idCollaborateur);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int idRa = rs.getInt("id_ra");
                ResponsableActivite responsableActive = DaoFactory.ResponsableActiviteDao().read(idRa);
                list.add(responsableActive);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la vérification de l'existence : " + ex.getMessage());
        }
        return list;
    }
    
    public Collection<Partenaire> listPartenaire(int idRa){
        String sql = "SELECT id_partenaire FROM proposer WHERE id_ra=?";
        ArrayList<Partenaire> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, idRa);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int idPartenaire = rs.getInt("id_partenaire");
                Partenaire partenaire = DaoFactory.getPartenaireDao().read(idPartenaire);
                list.add(partenaire);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la vérification de l'existence : " + ex.getMessage());
        }
        return list;
    }
    
    // rajout test
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

                list.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("Erreur lors du listage : " + ex.getMessage());
        }
        return list;
    }

    public List<Collaborateur> rechercherParNom(String nom) throws SQLException {
    return rechercher("SELECT * FROM collaborateur WHERE nom LIKE ?", nom);
}

public List<Collaborateur> rechercherParPrenom(String prenom) throws SQLException {
    return rechercher("SELECT * FROM collaborateur WHERE prenom LIKE ?", prenom);
}

public List<Collaborateur> rechercherParMatricule(String matricule) throws SQLException {
    return rechercher("SELECT * FROM collaborateur WHERE matricule LIKE ?", matricule);
}

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
//                collaborateur.setTelephone_personnel(rs.getString("telephone_personnel"));
                collaborateur.setStatut(rs.getString("statut"));
//                collaborateur.setCategorie(rs.getString("categorie"));
//                collaborateur.setGenre(rs.getString("genre"));
//                collaborateur.setRqth(rs.getString("rqth"));
//                collaborateur.setDate_de_renouvellement(rs.getDate("date_de_renouvellement").toLocalDate());
//                collaborateur.setMetier(rs.getString("metier"));
                collaborateurs.add(collaborateur);
            }
        }
    }
    return collaborateurs;
}


}