package dao;

import entities.ResponsableActivite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author asolanas
 */
public class ResponsableActiviteDao extends Dao<ResponsableActivite> {

    public ResponsableActiviteDao() {
        super("ra");
    }

    @Override
    protected ResponsableActivite createObject(ResultSet rs) throws SQLException {
        ResponsableActivite ra = new ResponsableActivite() {
        };
        ra.setId(rs.getInt("id_" + table));
        ra.setMatricule(rs.getInt("Matricule"));
        ra.setNom(rs.getString("nom"));
        ra.setPrenom(rs.getString("prenom"));
        ra.setDate_de_naissance(rs.getDate("date_de_naissance").toLocalDate());
        ra.setNumero_voie(rs.getString("numero_voie"));
        ra.setAdresse(rs.getString("adresse"));  
        ra.setCode_postal(rs.getInt("code_postal"));
        ra.setVille(rs.getString("ville"));
        ra.setTelephone_professionnel(rs.getString("telephone_professionnel"));
        ra.setTelephone_personnel(rs.getString("telephone_personnel"));

        return ra;
    }

    @Override
    public void create(ResponsableActivite ra) throws SQLException {
        String sql = "INSERT INTO ra(matricule, nom, prenom, date_de_naissance,numero_voie, adresse, code_postal, ville, telephone_professionnel, telephone_personnel) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, ra.getMatricule());
            pstmt.setString(2, ra.getNom());
            pstmt.setString(3, ra.getPrenom());
            LocalDate localDate = ra.getDate_de_naissance();
            if (localDate != null) {
                pstmt.setDate(4, java.sql.Date.valueOf(localDate));
            } else {
                pstmt.setDate(4, null);
            }
            pstmt.setString(5, ra.getNumero_voie());
            pstmt.setString(6, ra.getAdresse());
            pstmt.setInt(7, ra.getCode_postal());
            pstmt.setString(8, ra.getVille());
            pstmt.setString(9, ra.getTelephone_professionnel());
            pstmt.setString(10, ra.getTelephone_personnel());
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
                    ra.setDate_de_naissance(rs.getDate("date_de_naissance").toLocalDate());
                    ra.setNumero_voie(rs.getString("numero_voie"));
                    ra.setAdresse(rs.getString("adresse"));
                    ra.setCode_postal(rs.getInt("code_postal"));
                    ra.setVille(rs.getString("ville"));
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

    @Override
    protected void update(ResponsableActivite obj) {
    }

}