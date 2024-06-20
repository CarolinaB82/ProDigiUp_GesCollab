package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 *
 * @author Amélie Solanas Pruvost <future CDA>
 */
@SuppressWarnings("serial")
public class ResponsableActivitePartenaire {

    private ResponsableActivite ra;
    private Collection<Partenaire> partenaires;

    public ResponsableActivite getRa() {
        return ra;
    }

    public void setRa(ResponsableActivite ra) {
        this.ra = ra;
    }

    public Collection<Partenaire> getPartenaires() {
        return partenaires;
    }

    public void setPartenaires(Collection<Partenaire> partenaires) {
        this.partenaires = partenaires;
    }
    
}
