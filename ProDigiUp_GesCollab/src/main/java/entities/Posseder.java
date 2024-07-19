/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Représente l'association entre un Responsable d'Activité (RA) et un
 * Collaborateur. Cette classe permet de lier un RA à un Collaborateur par leur
 * identifiant respectif. Implémente l'interface Identifiable pour identifier de
 * manière unique chaque instance de Posseder. La classe est sérialisable pour
 * permettre sa sauvegarde et sa restauration.
 *
 * @author cberge
 */
@SuppressWarnings("serial")
public class Posseder implements Serializable, Identifiable {

    private Integer id_ra;
    private Integer id_collaborateur;

    public Integer getId_ra() {
        return id_ra;
    }

    public void setId_ra(Integer id_ra) {
        this.id_ra = id_ra;
    }

    public Integer getId_collaborateur() {
        return id_collaborateur;
    }

    public void setId_collaborateur(Integer id_collaborateur) {
        this.id_collaborateur = id_collaborateur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id_ra);
        hash = 97 * hash + Objects.hashCode(this.id_collaborateur);
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
        final Posseder other = (Posseder) obj;
        if (!Objects.equals(this.id_ra, other.id_ra)) {
            return false;
        }
        return Objects.equals(this.id_collaborateur, other.id_collaborateur);
    }

    @Override
    public String toString() {
        return "Posseder{" + "id_ra=" + id_ra + ", id_collaborateur=" + id_collaborateur + '}';
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {
    }

}
