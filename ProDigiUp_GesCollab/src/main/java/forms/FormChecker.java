package forms;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe abstraite est utilisée pour la validation et le traitement des
 * données soumises depuis un formulaire. Elle définit des méthodes et des
 * fonctionnalités communes pour vérifier les champs du formulaire et gérer les
 * erreurs éventuelles.
 *
 * Elle est conçue pour être étendue par des classes spécialisées selon le type
 * d'objet à valider et à traiter.
 *
 * @author cberge
 * @param <T> Le type d'objet que ce validateur de formulaire doit gérer. Par
 * exemple : Collaborateur, Partenaire, ResponsableActivite, etc.
 */
public abstract class FormChecker<T> {

    protected Map<String, String> errors;
    protected HttpServletRequest request;

    public FormChecker(HttpServletRequest request) {
        this.errors = new HashMap<>();
        this.request = request;
    }

    public abstract T checkForm();

    public Map<String, String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    protected void setError(String key, String value) {
        this.errors.put(key, value);
    }

    protected String getParameter(String key) {
        return request.getParameter(key)
                == null ? "" : request.getParameter(key);
    }

    public void addError(String key, String value) {
        this.errors.put(key, value);
    }

}
