package fr.famivac.interfaces.web.utils;

import fr.famivac.gestionnaire.administration.control.CommuneService;
import fr.famivac.gestionnaire.commons.entity.Commune;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author paoesco
 */
public interface CompleteCommune {

    CommuneService getCommuneService();

    default List<Commune> getCommunes() {
        return getCommuneService().retrieve();
    }

    default List<Commune> completeCommune(String query) {
        List<Commune> communes = getCommunes();
        if (query == null || query.isEmpty()) {
            return communes;
        } else {
            return communes.stream().filter((Commune t) -> {
                return t.getLabel().toLowerCase().trim().contains(query.toLowerCase().trim());
            }).collect(Collectors.toList());
        }
    }

}
