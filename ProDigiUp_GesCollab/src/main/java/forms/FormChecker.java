package forms;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cberge
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
