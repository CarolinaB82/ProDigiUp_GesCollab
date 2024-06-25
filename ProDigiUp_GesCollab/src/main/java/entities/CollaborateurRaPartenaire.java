
package entities;

import java.io.Serializable;

import java.util.Objects;
import java.time.LocalDate;
import java.util.Collection;

/**
 *
 * @author cberge
 */
@SuppressWarnings("serial")
public class CollaborateurRaPartenaire {
    
    private Collaborateur collaborateur;
    private Collection<ResponsableActivitePartenaire> raPartenaires;
    private boolean prestationActive;

    public boolean isPrestationActive() {
        return prestationActive;
    }

    public void setPrestationActive(boolean prestationActive) {
        this.prestationActive = prestationActive;
    }

    public Collaborateur getCollaborateur() {
        return collaborateur;
    }

    public void setCollaborateur(Collaborateur collaborateur) {
        this.collaborateur = collaborateur;
    }

    public Collection<ResponsableActivitePartenaire> getRaPartenaires() {
        return raPartenaires;
    }

    public void setRaPartenaires(Collection<ResponsableActivitePartenaire> raPartenaires) {
        this.raPartenaires = raPartenaires;
    }
    
    

   
}
