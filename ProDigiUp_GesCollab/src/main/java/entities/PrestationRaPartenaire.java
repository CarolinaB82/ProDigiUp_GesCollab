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
public class PrestationRaPartenaire {

    private Prestation prestation;
    private ResponsableActivite resp;
    private Partenaire part;

    public Prestation getPrestation() {
        return prestation;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

    public ResponsableActivite getResp() {
        return resp;
    }

    public void setResp(ResponsableActivite resp) {
        this.resp = resp;
    }

    public Partenaire getPart() {
        return part;
    }

    public void setPart(Partenaire part) {
        this.part = part;
    }

}
