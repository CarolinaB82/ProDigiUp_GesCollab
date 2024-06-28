/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dao;

import entities.Proposer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cberge
 */
public class ProposerDao extends Dao<Proposer>{

    public ProposerDao() {
        super("Proposer");
    }

    @Override
    protected Proposer createObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(Proposer proposer) throws SQLException {
        String sql = "INSERT INTO proposer(id_ra, id_partenaire)"
                + "VALUES (?, ?)";
         try (PreparedStatement pstmt = connexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, proposer.getId_ra());
            pstmt.setInt(2, proposer.getId_partenaire());
            int nbLines = pstmt.executeUpdate();
                
                System.out.println("Responsable d'activité créé avec succès!");
            
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'insertion : " + ex.getMessage());
            throw ex;
        }
    }
        
        
        
        
    

    @Override
    protected void update(Proposer obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
