package fr.famivac.gestionnaire.commons.api;

import javax.ws.rs.core.Response.Status;

/**
 *
 * @author paoesco
 */
public class ApiAuthenticationError {

    private Integer status;

    private String message;

    public ApiAuthenticationError() {
        this.status = Status.FORBIDDEN.getStatusCode();
        this.message = "Forbidden";
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
