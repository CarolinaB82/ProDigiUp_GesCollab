package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Amélie Solanas Pruvost <future CDA>
 */
@SuppressWarnings("serial")
public class ResponsableActivite implements Identifiable, Serializable {

    private Integer id;
    private int matricule;
    private String nom;
    private String prenom;
    private String telephone_professionnel;
    private String telephone_personnel;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ResponsableActivite{");
        sb.append("id=").append(id);
        sb.append(", matricule=").append(matricule);
        sb.append(", nom=").append(nom);
        sb.append(", prenom=").append(prenom);
        sb.append(", tel_pro=").append(telephone_professionnel);
        sb.append(", tel_perso=").append(telephone_personnel);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + this.matricule;
        hash = 97 * hash + Objects.hashCode(this.nom);
        hash = 97 * hash + Objects.hashCode(this.prenom);
        hash = 97 * hash + Objects.hashCode(this.telephone_professionnel);
        hash = 97 * hash + Objects.hashCode(this.telephone_personnel);
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
        final ResponsableActivite other = (ResponsableActivite) obj;
        if (this.matricule != other.matricule) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.telephone_professionnel, other.telephone_professionnel)) {
            return false;
        }
        if (!Objects.equals(this.telephone_personnel, other.telephone_personnel)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

       

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone_professionnel() {
        return telephone_professionnel;
    }

    public void setTelephone_professionnel(String telephone_professionnel) {
        this.telephone_professionnel = telephone_professionnel;
    }

    public String getTelephone_personnel() {
        return telephone_personnel;
    }

    public void setTelephone_personnel(String telephone_personnel) {
        this.telephone_personnel = telephone_personnel;
    }

}
