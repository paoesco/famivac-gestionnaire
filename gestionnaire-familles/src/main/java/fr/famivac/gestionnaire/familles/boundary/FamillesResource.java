package fr.famivac.gestionnaire.familles.boundary;

import fr.famivac.gestionnaire.commons.api.ApiAuthentication;
import fr.famivac.gestionnaire.familles.entity.FamilleRepository;
import fr.famivac.gestionnaire.familles.entity.views.FamilleToImportDTO;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * @author paoesco
 */
@Path("/familles")
@Produces("application/json")
public class FamillesResource {

    @Inject
    private FamilleRepository familleRepository;

    @Inject
    private ApiAuthentication apiAuthentication;

    @GET
    @Path("/import")
    public Response getToImport(@HeaderParam("X-FAMIVAC-API-KEY") String paramApiKey) {
        if (!apiAuthentication.verifyApiKey(paramApiKey)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        List<FamilleToImportDTO> responseBody = familleRepository.getFamillesToImport();
        return Response.ok(responseBody).build();
    }

}
