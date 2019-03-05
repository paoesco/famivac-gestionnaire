package fr.famivac.gestionnaire.familles.boundary;

import fr.famivac.gestionnaire.familles.entity.FamilleRepository;
import fr.famivac.gestionnaire.familles.entity.views.FamilleToImportDTO;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
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


    @GET
    @Path("/import")
    public Response getToImport() {
        List<FamilleToImportDTO> responseBody = familleRepository.getFamillesToImport();
        return Response.ok(responseBody).build();
    }

}
