/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Représente une prestation avec ses détails associés.
 * Cette classe contient les informations telles que le sigle de la prestation,
 * le numéro d'affaire, le nom de la prestation, les références de facturation pour le partenaire et Airbus,
 * les identifiants associés au Responsable d'Activité (RA), au partenaire et au collaborateur.
 * Implémente l'interface Identifiable pour identifier de manière unique chaque instance de Prestation.
 * La classe est sérialisable pour permettre sa sauvegarde et sa restauration.
 * 
 * @author cberge
 */
@SuppressWarnings("serial")
public class PrestationPartRaCollab  {

    private Prestation prestation;
    private String nomPartenaire;
    private String nomRa;
    private String nomCollab;

    public Prestation getPrestation() {
        return prestation;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

    public String getNomPartenaire() {
        return nomPartenaire;
    }

    public void setNomPartenaire(String nomPartenaire) {
        this.nomPartenaire = nomPartenaire;
    }

    public String getNomRa() {
        return nomRa;
    }

    public void setNomRa(String nomRa) {
        this.nomRa = nomRa;
    }

    public String getNomCollab() {
        return nomCollab;
    }

    public void setNomCollab(String nomCollab) {
        this.nomCollab = nomCollab;
    }

    
}