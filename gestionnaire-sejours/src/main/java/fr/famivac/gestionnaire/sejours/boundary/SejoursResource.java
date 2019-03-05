package fr.famivac.gestionnaire.sejours.boundary;

import fr.famivac.gestionnaire.sejours.entity.SejourRepository;
import fr.famivac.gestionnaire.sejours.entity.views.SejoursFamilleDTO;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
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
    private SejourRepository sejourRepository;

    @GET
    @Path("/")
    public Response getToImport(@QueryParam("familleId") Long familleId) {
        List<SejoursFamilleDTO> responseBody = sejourRepository.getSejoursFamille(familleId);
        return Response.ok(responseBody).build();
    }
}
