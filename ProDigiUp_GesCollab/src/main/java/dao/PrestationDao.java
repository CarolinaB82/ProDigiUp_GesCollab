/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dao;

import entities.Prestation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cberge
 */
public class PrestationDao extends Dao<Prestation> {

    public PrestationDao() {
        super("Prestation");
    }

    @Override
    protected Prestation createObject(ResultSet rs) throws SQLException {
        Prestation prestation = new Prestation() {
        };
        prestation.setId(rs.getInt("id_" + table));
        prestation.setSiglum_presta(rs.getString("siglum_presta"));
        prestation.setNom_presta(rs.getString("nom_presta"));
        prestation.setRef_fact_partenaire(rs.getString("ref_fact_partenaire"));
        prestation.setRef_fact_airbus(rs.getString("ref_fact_airbus"));
        prestation.setId_ra(rs.getInt("id_ra"));
        prestation.setId_collaborateur(rs.getInt("id_collaborateur"));
        prestation.setId_partenaire(rs.getInt("id_partenaire"));

        return prestation;
    }

    @Override
    protected void create(Prestation obj) throws SQLException {
    }

    @Override
    public Prestation read(Integer id) {
        Prestation obj = null;
        String sql = "SELECT * FROM prestation WHERE id_prestation=?";
        PreparedStatement pstmt;
        try {
            pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                obj = new Prestation();
                obj.setId(rs.getInt("id_prestation"));
                obj.setSiglum_presta(rs.getString("siglum_presta"));
                obj.setNom_presta(rs.getString("nom_presta"));
                obj.setRef_fact_partenaire(rs.getString("ref_fact_partenaire"));
                obj.setRef_fact_airbus(rs.getString("ref_fact_airbus"));
                obj.setId_ra(rs.getInt("id_ra"));
                obj.setId_collaborateur(rs.getInt("id_collaborateur"));
                obj.setId_partenaire(rs.getInt("id_partenaire"));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la lecture : " + ex.getMessage());
        }
        return obj;
    }

    @Override
    protected void update(Prestation obj) {
    }

    public Collection<Prestation> listByNom(String nom_presta) {
        ArrayList<Prestation> listNom = new ArrayList<>();
        String sql = "SELECT * FROM " + table + " WHERE nom_presta =?";

        try (
                PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, nom_presta);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Prestation obj = createObject(rs);
                listNom.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listNom;
    }

    public Collection<Prestation> listPrestation() {
        ArrayList<Prestation> list = new ArrayList<>();
        String sql = "SELECT * FROM prestation";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Prestation c = new Prestation();
                c.setId(rs.getInt("id_collaborateur"));
                c.setSiglum_presta(rs.getString("singlum_presta"));
                c.setNom_presta(rs.getString("nom_presta"));
                c.setRef_fact_partenaire(rs.getString("ref_fact_partenaire"));
                c.setRef_fact_airbus(rs.getString("ref_fact_airbus"));

                list.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("Erreur lors du listage : " + ex.getMessage());
        }
        return list;
    }
}
