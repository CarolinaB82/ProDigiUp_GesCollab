/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dao;

import entities.Proposer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * conçue pour gérer les associations entre les identifiants de responsables d'activité (id_ra) 
 * et les identifiants de partenaires (id_partenaire)
 * Constructeur
 * Methode 
 * Methode spécifique
 * Gestion des exceptions
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
         // Méthode pour récupérer les identifiants des responsables d'activité associés à un collaborateur
    public List<Integer> getResponsablesIds(int partenaireId) {
        List<Integer> responsableIds = new ArrayList<>();
        
        String sql = "SELECT id_ra FROM proposer WHERE id_partenaire = ?";
        try (
             PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            
            pstmt.setInt(1, partenaireId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int responsableId = rs.getInt("id_ra");
                responsableIds.add(responsableId);
            }
            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des responsables d'activité : " + e.getMessage());
        }
        
        return responsableIds;
    
    
    }
        
         public List<Integer> getPartenaireIds(int responsableActiviteId) {
        List<Integer> partenaireIds = new ArrayList<>();
        
        String sql = "SELECT id_partenaire FROM proposer WHERE id_ra = ?";
        try (
             PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            
            pstmt.setInt(1, responsableActiviteId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int partenaireId = rs.getInt("id_partenaire");
                partenaireIds.add(partenaireId);
            }
            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des collaborateurs : " + e.getMessage());
        }
        
        return partenaireIds;
    
    }
        
        
    

    @Override
    protected void update(Proposer obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
