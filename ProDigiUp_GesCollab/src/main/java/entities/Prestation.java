/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author cberge
 */
@SuppressWarnings("serial")
public class Prestation implements Serializable, Identifiable {

    private Integer id;
    private String siglum_presta;
    private String num_affaire;
    private String nom_presta;
    private String ref_fact_partenaire;
    private String mail_partenaire;
    private String ref_fact_airbus;
    private String mail_airbus;
    private Integer id_ra;
    private Integer id_partenaire;
    private Integer id_collaborateur;

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

    public String getNum_affaire() {
        return num_affaire;
    }

    public void setNum_affaire(String num_affaire) {
        this.num_affaire = num_affaire;
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

    public String getMail_partenaire() {
        return mail_partenaire;
    }

    public void setMail_partenaire(String mail_partenaire) {
        this.mail_partenaire = mail_partenaire;
    }

    public String getRef_fact_airbus() {
        return ref_fact_airbus;
    }

    public void setRef_fact_airbus(String ref_fact_airbus) {
        this.ref_fact_airbus = ref_fact_airbus;
    }

    public String getMail_airbus() {
        return mail_airbus;
    }

    public void setMail_airbus(String mail_airbus) {
        this.mail_airbus = mail_airbus;
    }

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

    public Integer getId_collaborateur() {
        return id_collaborateur;
    }

    public void setId_collaborateur(Integer id_collaborateur) {
        this.id_collaborateur = id_collaborateur;
    }

    @Override
    public String toString() {
        return "Prestation{" + "id=" + id + ", siglum_presta=" + siglum_presta + ", nom_presta=" + nom_presta + ", ref_fact_partenaire=" + ref_fact_partenaire + ", ref_fact_airbus=" + ref_fact_airbus + ", id_ra=" + id_ra + ", id_partenaire=" + id_partenaire + ", id_collaborateur=" + id_collaborateur + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.siglum_presta);
        hash = 31 * hash + Objects.hashCode(this.nom_presta);
        hash = 31 * hash + Objects.hashCode(this.ref_fact_partenaire);
        hash = 31 * hash + Objects.hashCode(this.ref_fact_airbus);
        hash = 31 * hash + Objects.hashCode(this.id_ra);
        hash = 31 * hash + Objects.hashCode(this.id_partenaire);
        hash = 31 * hash + Objects.hashCode(this.id_collaborateur);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.id_ra, other.id_ra)) {
            return false;
        }
        if (!Objects.equals(this.id_partenaire, other.id_partenaire)) {
            return false;
        }
        return Objects.equals(this.id_collaborateur, other.id_collaborateur);
    }

    private List<Integer> responsableIds;

    public List<Integer> getResponsablesIds() {
        return responsableIds;
    }

    public void setResponsableIds(List<Integer> responsableIds) {
        this.responsableIds = responsableIds;
    }

    private List<Integer> collaborateurIds;

    public List<Integer> getCollaborateurIds() {
        return collaborateurIds;
    }

    public void setCollaborateurIds(List<Integer> collaborateurIds) {
        this.collaborateurIds = collaborateurIds;

    }
       private  List<Integer> partenaireIds;
     
    public  List<Integer> getPartenaireIds(){
        return partenaireIds;
    }
    public void setPartenaireIds( List<Integer> partenaireIds){
            this.partenaireIds = partenaireIds;
    
    }

    public String getNom() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
