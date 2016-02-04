package fr.fava.gestionnaire.domain.sejour;

import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

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

    public long countActifs() {
        String jpql = "select count(s.id) from Sejour s where s.dateDebut <= :date and :date <= s.dateFin";
        Query q = entityManager.createQuery(jpql);
        q.setParameter("date", new Date(), TemporalType.DATE);
        return (long) q.getSingleResult();
    }

}
