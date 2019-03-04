package fr.famivac.gestionnaire.commons.api;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author paoesco
 */
@ApplicationScoped
public class ApiAuthentication {

    public boolean verifyApiKey(String paramApiKey) {
        String apiKey = System.getProperty("famivac.api.key");
        return apiKey.equals(paramApiKey);
    }

}
