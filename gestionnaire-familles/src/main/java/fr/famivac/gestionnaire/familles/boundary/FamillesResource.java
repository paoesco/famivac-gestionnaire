package fr.famivac.gestionnaire.familles.boundary;

import fr.famivac.gestionnaire.familles.entity.Famille;
import fr.famivac.gestionnaire.familles.entity.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
    private EntityManager entityManager;

    @GET
    @Path("/import")
    public Response getToImport(@HeaderParam("X-FAMIVAC-API-KEY") String paramApiKey) {
        if (!verifyApiKey(paramApiKey)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        List<Famille> familles = entityManager.createNamedQuery(Famille.QUERY_LISTE_ALL, Famille.class).getResultList();
        List<FamilleToImportDTO> responseBody = familles
                .stream()
                .filter(f -> {
                    return Objects.nonNull(f.getMembreReferent())
                            && Objects.nonNull(f.getMembreReferent().getCoordonnees())
                            && Objects.nonNull(f.getMembreReferent().getCoordonnees().getEmail())
                            && !f.getMembreReferent().getCoordonnees().getEmail().isBlank()
                            && Status.ACTIVE.equals(f.getStatus());
                })
                .map(f -> {
                    return new FamilleToImportDTO(f);
                })
                .collect(Collectors.toUnmodifiableList());
        return Response.ok(responseBody).build();
    }

    private boolean verifyApiKey(String paramApiKey) {
        String apiKey = System.getProperty("famivac.api.key");
        return apiKey.equals(paramApiKey);
    }

}
