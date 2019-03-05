package fr.famivac.gestionnaire.commons.api;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author paoesco
 */
@Provider
public class ApiAuthenticationFilter implements ContainerRequestFilter {
    
    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        String paramApiKey = ctx.getHeaderString("X-FAMIVAC-API-KEY");
        if (!verifyApiKey(paramApiKey)) {
            ctx.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(new ApiAuthenticationError())
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .build());
        }
    }
    
    private boolean verifyApiKey(String paramApiKey) {
        String apiKey = System.getProperty("famivac.api.key");
        return apiKey.equals(paramApiKey);
    }
    
}
