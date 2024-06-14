
package entities;

import java.io.Serializable;

import java.util.Objects;
import java.time.LocalDate;

/**
 *
 * @author cberge
 */
@SuppressWarnings("serial")
public class Collaborateur implements Identifiable, Serializable {
    
    private Integer id;
    private Integer matricule;
    private String nom;
    private String prenom;
    private String telephone_personnel;
    private String statut;
    private String categorie;
    private String genre;
    private String rqth;
    private String metier;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Collaborateur{");
        sb.append("id=").append(id);
        sb.append(", matricule=").append(matricule);
        sb.append(", nom=").append(nom);
        sb.append(", prenom=").append(prenom);
        sb.append(", telephone_personnel=").append(telephone_personnel);
        sb.append(", statut=").append(statut);
        sb.append(", categorie=").append(categorie);
        sb.append(", genre=").append(genre);
        sb.append(", rqth=").append(rqth);
        sb.append(", metier=").append(metier);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.matricule);
        hash = 89 * hash + Objects.hashCode(this.nom);
        hash = 89 * hash + Objects.hashCode(this.prenom);
        hash = 89 * hash + Objects.hashCode(this.telephone_personnel);
        hash = 89 * hash + Objects.hashCode(this.statut);
        hash = 89 * hash + Objects.hashCode(this.categorie);
        hash = 89 * hash + Objects.hashCode(this.genre);
        hash = 89 * hash + Objects.hashCode(this.rqth);
        hash = 89 * hash + Objects.hashCode(this.metier);
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
        final Collaborateur other = (Collaborateur) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.statut, other.statut)) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        if (!Objects.equals(this.rqth, other.rqth)) {
            return false;
        }
        if (!Objects.equals(this.metier, other.metier)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.matricule, other.matricule)) {
            return false;
        }
        return Objects.equals(this.telephone_personnel, other.telephone_personnel);
    }

   

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
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

   
    public String getTelephone_personnel() {
        return telephone_personnel;
    }

    public void setTelephone_personnel(String telephone_personnel) {
        this.telephone_personnel = telephone_personnel;
    }

    

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRqth() {
        return rqth;
    }

    public void setRqth(String rqth) {
        this.rqth = rqth;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }
}
