/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author cberge
 */
@SuppressWarnings("serial")
public class Proposer implements Serializable, Identifiable {

    private Integer id_ra;
    private Integer id_partenaire;

    
    public Integer getId_ra() {
        return id_ra;
    }

    public void setId_ra(Integer id_ra) {
        this.id_ra = id_ra;
    }

    public Integer getId_partenaire() {
        return id_partenaire;
    }

    public void setId_partenaire(Integer id_partenaire) {
        this.id_partenaire = id_partenaire;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id_ra);
        hash = 53 * hash + Objects.hashCode(this.id_partenaire);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proposer other = (Proposer) obj;
        if (!Objects.equals(this.id_ra, other.id_ra)) {
            return false;
        }
        return Objects.equals(this.id_partenaire, other.id_partenaire);
    }

    @Override
    public String toString() {
        return "Proposer{" + "id_ra=" + id_ra + ", id_partenaire=" + id_partenaire + '}';
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {
    }
    
    
    
}
