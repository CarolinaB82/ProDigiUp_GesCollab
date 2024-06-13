/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package forms;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cberge
 */
public abstract class FormChecker <T>{

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

}
