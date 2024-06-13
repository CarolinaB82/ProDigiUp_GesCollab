package forms;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author asolanas
 */
    public abstract class FormChecker<T> {

        protected Map<String, String> errors;
        protected HttpServletRequest request;

        public FormChecker(HttpServletRequest request) {
            this.errors = new HashMap<>();
            this.request = request;
        }

        public abstract T checkForm();

        // public Map<String, String> getErrors();
        // return errors.put(key, value);
        public Map<String, String> getErrors() {
            return errors;
        }

        public void setError(String key, String value) {
            this.errors.put(key, value);
        }

        protected String getParameter(String key) {
            return request.getParameter(key)
                    == null ? "" : request.getParameter(key);
        }

    }

