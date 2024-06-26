/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dao;

import entities.Posseder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cberge
 */
public class PossederDao extends Dao<Posseder>{

    public PossederDao() {
        super("Posseder");
    }

    @Override
    protected Posseder createObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(Posseder posseder) throws SQLException {
        String sql = "INSERT INTO posseder(id_ra, id_collaborateur)"
                + "VALUES (?, ?)";
         try (PreparedStatement pstmt = connexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, posseder.getId_ra());
            pstmt.setInt(2, posseder.getId_collaborateur());
            int nbLines = pstmt.executeUpdate();
                
                System.out.println("Responsable d'activité créé avec succès!");
            
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'insertion : " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    protected void update(Posseder obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
