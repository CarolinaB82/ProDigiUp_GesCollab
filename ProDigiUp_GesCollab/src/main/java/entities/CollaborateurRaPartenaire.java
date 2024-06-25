
package entities;

import java.util.Collection;

/**
 *
 * @author cberge
 */
@SuppressWarnings("serial")
public class CollaborateurRaPartenaire {
    
    private Collaborateur collaborateur;
    private Collection<ResponsableActivitePartenaire> raPartenaires;

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
