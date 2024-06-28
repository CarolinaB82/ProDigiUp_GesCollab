/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author asolanas
 */
@SuppressWarnings("serial")
public class Partenaire implements Serializable, Identifiable {

    private Integer id;
    private String nom;
    private int numero_voie;
    private String adresse;
    private int code_postal;
    private String ville;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Partenaire{");
        sb.append("id=").append(id);
        sb.append(", nom=").append(nom);
        sb.append(", numero_voie=").append(numero_voie);
        sb.append(", adresse=").append(adresse);
        sb.append(", code_postal=").append(code_postal);
        sb.append(", ville=").append(ville);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumero_voie() {
        return numero_voie;
    }

    public void setNumero_voie(int numero_voie) {
        this.numero_voie = numero_voie;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.nom);
        hash = 67 * hash + this.numero_voie;
        hash = 67 * hash + Objects.hashCode(this.adresse);
        hash = 67 * hash + this.code_postal;
        hash = 67 * hash + Objects.hashCode(this.ville);
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
        final Partenaire other = (Partenaire) obj;
        if (this.numero_voie != other.numero_voie) {
            return false;
        }
        if (this.code_postal != other.code_postal) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

}
