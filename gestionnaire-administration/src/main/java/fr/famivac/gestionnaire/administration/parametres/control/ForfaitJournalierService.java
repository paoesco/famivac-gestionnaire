package fr.famivac.gestionnaire.administration.parametres.control;

import fr.famivac.gestionnaire.administration.parametres.entity.ForfaitJournalier;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import net.bull.javamelody.MonitoringInterceptor;

/**
 * @author paoesco
 */
@Stateless
@Interceptors({MonitoringInterceptor.class})
public class ForfaitJournalierService {

    @Inject
    private EntityManager entityManager;

    public Long create(ForfaitJournalier entity) {
        entityManager.persist(entity);
        return entity.getId();
    }

    public List<ForfaitJournalier> retrieve() {
        return entityManager.createNamedQuery(ForfaitJournalier.QUERY_LISTE_ALL).getResultList();
    }

    public void update(ForfaitJournalier entity) {
        entityManager.merge(entity);
    }

    public void delete(Long id) {
        ForfaitJournalier entity = entityManager.find(ForfaitJournalier.class, id);
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException("Le tarif n'existe pas");
        }
        entityManager.remove(entity);
    }

    public Optional<BigDecimal> getCurrentMontant(Date date) {
        try {
            return Optional.of(entityManager
                    .createNamedQuery(ForfaitJournalier.QUERY_GET_CURRENT, ForfaitJournalier.class)
                    .setParameter("date", date)
                    .getSingleResult()
                    .getMontant());
        } catch (NoResultException | NonUniqueResultException ex) {
            return Optional.empty();
        }
    }

}
