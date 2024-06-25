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
public class Prestation implements Serializable, Identifiable{
    
    private Integer id;
    private String siglum_presta;
    private String nom_presta;
    private String ref_fact_partenaire;
    private String ref_fact_airbus;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiglum_presta() {
        return siglum_presta;
    }

    public void setSiglum_presta(String siglum_presta) {
        this.siglum_presta = siglum_presta;
    }

    public String getNom_presta() {
        return nom_presta;
    }

    public void setNom_presta(String nom_presta) {
        this.nom_presta = nom_presta;
    }

    public String getRef_fact_partenaire() {
        return ref_fact_partenaire;
    }

    public void setRef_fact_partenaire(String ref_fact_partenaire) {
        this.ref_fact_partenaire = ref_fact_partenaire;
    }

    public String getRef_fact_airbus() {
        return ref_fact_airbus;
    }

    public void setRef_fact_airbus(String ref_fact_airbus) {
        this.ref_fact_airbus = ref_fact_airbus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prestation{");
        sb.append("id=").append(id);
        sb.append(", siglum_presta=").append(siglum_presta);
        sb.append(", nom_presta=").append(nom_presta);
        sb.append(", ref_fact_partenaire=").append(ref_fact_partenaire);
        sb.append(", ref_fact_airbus=").append(ref_fact_airbus);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.siglum_presta);
        hash = 47 * hash + Objects.hashCode(this.nom_presta);
        hash = 47 * hash + Objects.hashCode(this.ref_fact_partenaire);
        hash = 47 * hash + Objects.hashCode(this.ref_fact_airbus);
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
        final Prestation other = (Prestation) obj;
        if (!Objects.equals(this.siglum_presta, other.siglum_presta)) {
            return false;
        }
        if (!Objects.equals(this.nom_presta, other.nom_presta)) {
            return false;
        }
        if (!Objects.equals(this.ref_fact_partenaire, other.ref_fact_partenaire)) {
            return false;
        }
        if (!Objects.equals(this.ref_fact_airbus, other.ref_fact_airbus)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

   
   

}
