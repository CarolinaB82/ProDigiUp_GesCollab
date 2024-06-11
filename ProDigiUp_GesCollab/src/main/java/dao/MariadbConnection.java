/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cberge
 */
public class MariadbConnection {
    
    private static Connection connection;
    private MariadbConnection () {
    }
        public static Connection getInstance (){
            if (connection == null){
String url = String.format("%s://%s:%s/%s","jdbc:mariadb", "localhost", "3306", "bd_gescollab");
try{
Class.forName("org.mariadb.jdbc.Driver");
connection = DriverManager.getConnection(url, "root", "");
} catch (SQLException ex){
Logger.getLogger(MariadbConnection.class.getName()).log(Level.SEVERE, null, ex);
System.exit(2);
}catch (ClassNotFoundException ex){
Logger.getLogger(MariadbConnection.class.getName()).log(Level.SEVERE, null, ex);
}
}
return connection;
}
public static void closeConnection(){
    if(connection !=null){
Logger.getLogger(MariadbConnection.class.getName()).log(Level.INFO, "Fermeture de la connexion.");
try{
    connection.close();
}catch (SQLException ex){
Logger.getLogger(MariadbConnection.class.getName()).log(Level.SEVERE, null, ex);
}
}
}
    }
    
