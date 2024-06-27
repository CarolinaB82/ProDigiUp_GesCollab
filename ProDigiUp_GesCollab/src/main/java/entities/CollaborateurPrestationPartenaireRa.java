/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package entities;

import java.util.Collection;

/**
 *
 * @author cberge
 */
public class CollaborateurPrestationPartenaireRa {

    private Collaborateur collaborateur;
    private Collection<PrestationRaPartenaire> prestRaPart;
    private boolean prestationActive;

    public Collaborateur getCollaborateur() {
        return collaborateur;
    }

    public void setCollaborateur(Collaborateur collaborateur) {
        this.collaborateur = collaborateur;
    }

    public Collection<PrestationRaPartenaire> getPrestRaPart() {
        return prestRaPart;
    }

    public void setPrestRaPart(Collection<PrestationRaPartenaire> prestRaPart) {
        this.prestRaPart = prestRaPart;
    }

    public boolean isPrestationActive() {
        return prestationActive;
    }

    public void setPrestationActive(boolean prestationActive) {
        this.prestationActive = prestationActive;
    }

}
