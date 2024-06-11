/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dao;

import entities.Identifiable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cberge
 */
public abstract class Dao<T extends Identifiable> {
    
    protected Connection connexion;
    protected String table;
    protected static Properties config;
    
    public Dao(String table){
        connexion = MariadbConnection.getInstance();
        this.table = table;
    }
    
       protected abstract T createObject (ResultSet rs) throws SQLException;


   public T read (Integer id){
       T obj = null;
       String sql = "SELECT * FROM " + table + "WHERE id_" + table + "=?";
       
       try( PreparedStatement pstmt = connexion.prepareStatement(sql)){
      // try{ PreparedStatement pstmt = connexion.prepareStatement(sql)){
           pstmt.setInt (1, id);
           ResultSet rs = pstmt.executeQuery();
           if (rs.first()) {
               obj= createObject(rs);
           }
       } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
       return obj;
   }}
       
   
   

