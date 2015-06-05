package fr.fava.gestionnaire.domain.sejour;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author paoesco
 */
@ApplicationScoped
public class SejourRepository {

    @Inject
    private EntityManager entityManager;

    public List<Sejour> sejoursFamille(long familleId) {
        return entityManager
                .createNamedQuery(Sejour.QUERY_SEJOURS_DE_LA_FAMILLE, Sejour.class)
                .setParameter("familleId", familleId)
                .getResultList();
    }

    public Sejour get(long id) {
        return entityManager.find(Sejour.class, id);
    }

}
