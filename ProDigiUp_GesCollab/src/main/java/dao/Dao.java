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
 * Classe abstraite Dao pour la gestion des interactions avec la base de
 * données.
 *
 * @param <T> le type d'objet géré par cette classe Dao, qui doit implémenter
 * l'interface Identifiable
 *
 * @since 2023
 * @author cberge
 * @see Identifiable
 */
public abstract class Dao<T extends Identifiable> {

    protected Connection connexion;
    protected String table;
    protected static Properties config;

    /**
     * Initialise un objet Dao avec une connexion à la base de données et
     * spécifie la table concernée.
     *
     * @param table le nom de la table dans la base de données avec laquelle
     * interagir
     */
    public Dao(String table) {
        connexion = MariadbConnection.getInstance();
        this.table = table;
    }

    /**
     * Crée un objet de type T à partir d'un ResultSet.
     *
     * @param rs le ResultSet contenant les données de la base de données
     * @return un objet de type T initialisé avec les données du ResultSet
     * @throws SQLException si une erreur survient lors de l'accès aux données
     * du ResultSet
     */
    protected abstract T createObject(ResultSet rs) throws SQLException;

    /**
     * Crée un nouvel enregistrement dans la base de données pour l'objet
     * spécifié.
     *
     * @param obj l'objet de type T à enregistrer dans la base de données
     * @throws SQLException si une erreur survient lors de l'insertion dans la
     * base de données
     */
    protected abstract void create(T obj) throws SQLException;

    /**
     * Met à jour un enregistrement existant dans la base de données pour
     * l'objet spécifié.
     *
     * @param obj l'objet de type T contenant les nouvelles valeurs à
     * enregistrer
     * @throws SQLException si une erreur survient lors de la mise à jour dans
     * la base de données
     */
    protected abstract void update(T obj) throws SQLException;

    /**
     * Sauvegarde l'objet spécifié dans la base de données. Si l'objet n'a pas
     * encore d'identifiant, il est créé. Sinon, il est mis à jour.
     *
     * @param obj l'objet de type T à sauvegarder
     * @throws SQLException si une erreur survient lors de la sauvegarde dans la
     * base de données
     */
    public void save(T obj) throws SQLException {
        if (obj.getId() == null) {
            create(obj);
        } else {
            update(obj);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public T read(Integer id) {
        T obj = null;
        String sql = "SELECT * FROM " + table + "WHERE id_" + table + "=?";

        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            // try{ PreparedStatement pstmt = connexion.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                obj = createObject(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return obj;
    }

    /**
     * Lit et retourne un objet de type T depuis la base de données en fonction
     * de son identifiant
     *
     * @param id l'identifiant de l'objet à lire
     * @return l'objet de type T correspondant à l'identifiant spécifié, ou null
     * si aucun objet n'est trouvé
     */
    public Collection<T> list() {
        ArrayList<T> list = new ArrayList<>();
        String sql = "SELECT * FROM " + table;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                T obj = createObject(rs);
                list.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, "Erreur lors du listage : " + ex.getMessage());

        }
        return list;
    }
}
