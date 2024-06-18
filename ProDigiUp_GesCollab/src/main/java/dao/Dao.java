package dao;

import entities.Identifiable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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

       protected abstract void create(T obj)throws SQLException;
       
       protected abstract void update(T obj);
       
       public void save (T obj) throws SQLException{
           if (obj.getId() == null) {
           create(obj);
       }else{
               update(obj);
               }
       }
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
   }

public Collection<T> list(){
        ArrayList<T> list = new ArrayList<>();
        String sql = "SELECT * FROM " + table;
        try 
            (PreparedStatement pstmt = connexion.prepareStatement(sql))
                
        {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                T obj = createObject(rs);
                list.add(obj);
            }
        }catch (SQLException ex){
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, "Erreur lors du listage : " + ex.getMessage());
                
            }
        return list;
    }  }
       
   
   

