package fr.famivac.gestionnaire.sejours.boundary;

import fr.famivac.gestionnaire.commons.api.ApiAuthentication;
import fr.famivac.gestionnaire.sejours.entity.SejourRepository;
import fr.famivac.gestionnaire.sejours.entity.views.SejoursFamilleDTO;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author paoesco
 */
@Path("/sejours")
@Produces("application/json")
public class SejoursResource {

    @Inject
    private ApiAuthentication apiAuthentication;

    @Inject
    private SejourRepository sejourRepository;

    @GET
    @Path("/")
    public Response getToImport(@QueryParam("familleId") Long familleId, @HeaderParam("X-FAMIVAC-API-KEY") String paramApiKey) {
        if (!apiAuthentication.verifyApiKey(paramApiKey)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        List<SejoursFamilleDTO> responseBody = sejourRepository.getSejoursFamille(familleId);
        return Response.ok(responseBody).build();
    }
}
