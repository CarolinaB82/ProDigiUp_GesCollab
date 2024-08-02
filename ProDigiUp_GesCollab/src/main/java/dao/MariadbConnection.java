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
 * La classe MariadbConnection que vous avez présentée est une implémentation
 * d'un Singleton pour gérer une connexion à une base de données MariaDB (MySQL)
 * Attributs statiques Constructeur privé Methode getInstance() Methode
 * closeConnexion()
 * @author cberge
 */
public class MariadbConnection {

    private static Connection connection;

    /**
     * Constructeur privé de la classe MariadbConnection. Ce constructeur est
     * privé pour empêcher l'instanciation directe de la classe depuis
     * l'extérieur. La classe MariadbConnection gère la connexion à la base de
     * données MariaDB en utilisant le pattern Singleton, où l'unique instance
     * est accessible via des méthodes statiques.
     */
    private MariadbConnection() {
    }
    /**
     * Retourne l'instance unique de la connexion à la base de données MariaDB.
     * Si aucune connexion n'a été établie précédemment, cette méthode établit
     * une nouvelle connexion en utilisant les informations de connexion
     * prédéfinies.
     * @return l'instance de connexion à la base de données MariaDB
     */
    public static Connection getInstance() {
        if (connection == null) {
            String url = String.format("%s://%s:%s/%s", "jdbc:mariadb", "localhost", "3306", "bd_gescollab");
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                //Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(url, "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(MariadbConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(2);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MariadbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }

    /**
     * Ferme la connexion à la base de données MariaDB si elle est ouverte. Si
     * la connexion est fermée ou si une erreur survient lors de la fermeture,
     * un message approprié est enregistré dans les logs.
     */
    public static void closeConnection() {
        if (connection != null) {
            Logger.getLogger(MariadbConnection.class.getName()).log(Level.INFO, "Fermeture de la connexion.");
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(MariadbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
