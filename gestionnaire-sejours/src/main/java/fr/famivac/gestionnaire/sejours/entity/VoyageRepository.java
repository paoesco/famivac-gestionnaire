package fr.famivac.gestionnaire.sejours.entity;

import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

/**
 *
 * @author paoesco
 */
@ApplicationScoped
public class VoyageRepository {

    @Inject
    private EntityManager entityManager;

    public List<Voyage> prochainsVoyages(Date date) {
        return entityManager
                .createNamedQuery(Voyage.QUERY_VOYAGES_PROCHAINS, Voyage.class)
                .setParameter("date", date, TemporalType.DATE)
                .getResultList();

    }

}
