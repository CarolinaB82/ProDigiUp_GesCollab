package entities;

import java.io.Serializable;

import java.util.Objects;
import java.time.LocalDate;
import java.util.List;

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
    private String mail_1;
    private String mail_2;
    private String telephone_personnel;
    private String statut;
    private String categorie;
    private String genre;
    private String rqth;
    private LocalDate date_de_renouvellement;
    private String metier;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Collaborateur{");
        sb.append("id=").append(id);
        sb.append(", matricule=").append(matricule);
        sb.append(", nom=").append(nom);
        sb.append(", prenom=").append(prenom);
        sb.append(", mail_1=").append(mail_1);
        sb.append(", mail_2=").append(mail_2);
        sb.append(", telephone_personnel=").append(telephone_personnel);
        sb.append(", statut=").append(statut);
        sb.append(", categorie=").append(categorie);
        sb.append(", genre=").append(genre);
        sb.append(", rqth=").append(rqth);
        sb.append(", date_de_renouvellement=").append(date_de_renouvellement);
        sb.append(", metier=").append(metier);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.matricule);
        hash = 83 * hash + Objects.hashCode(this.nom);
        hash = 83 * hash + Objects.hashCode(this.prenom);
        hash = 83 * hash + Objects.hashCode(this.mail_1);
        hash = 83 * hash + Objects.hashCode(this.mail_2);
        hash = 83 * hash + Objects.hashCode(this.telephone_personnel);
        hash = 83 * hash + Objects.hashCode(this.statut);
        hash = 83 * hash + Objects.hashCode(this.categorie);
        hash = 83 * hash + Objects.hashCode(this.genre);
        hash = 83 * hash + Objects.hashCode(this.rqth);
        hash = 83 * hash + Objects.hashCode(this.date_de_renouvellement);
        hash = 83 * hash + Objects.hashCode(this.metier);
        return hash;
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
    
     public String getMail_1() {
        return mail_1;
    }

    public void setMail_1(String mail_1) {
        this.mail_1 = mail_1;
    }
    
      public String getMail_2() {
        return mail_2;
    }

    public void setMail_2(String mail_2) {
        this.mail_2 = mail_2;
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

    public LocalDate getDate_de_renouvellement() {
        return date_de_renouvellement;
    }

    public void setDate_de_renouvellement(LocalDate date_de_renouvellement) {
        this.date_de_renouvellement = date_de_renouvellement;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    // Nouveau champ pour la prestation
    private String nomPrestation;

    // Getters et setters existants
    public void setNomPrestation(String nomPrestation) {
        this.nomPrestation = nomPrestation;
    }

    public String getNomPrestation() {
        return nomPrestation;
    }
    
    
    
     private  List<Integer> responsableIds;
     
    public  List<Integer> getResponsablesIds(){
        return responsableIds;
    }
    public void setResponsableIds( List<Integer> responsableIds){
            this.responsableIds = responsableIds;
}
}