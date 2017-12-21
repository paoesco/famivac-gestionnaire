package fr.famivac.gestionnaire.administration.parametres.control;

import fr.famivac.gestionnaire.administration.parametres.entity.FraisSejourJournalier;
import fr.famivac.gestionnaire.commons.utils.DateUtils;
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
public class FraisSejourJournalierService {

    @Inject
    private EntityManager entityManager;

    public Long create(FraisSejourJournalier entity) {
        entityManager.persist(entity);
        return entity.getId();
    }

    public List<FraisSejourJournalier> retrieve() {
        return entityManager.createNamedQuery(FraisSejourJournalier.QUERY_LISTE_ALL).getResultList();
    }

    public void update(FraisSejourJournalier entity) {
        entityManager.merge(entity);
    }

    public void delete(Long id) {
        FraisSejourJournalier entity = entityManager.find(FraisSejourJournalier.class, id);
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException("Le tarif n'existe pas");
        }
        entityManager.remove(entity);
    }

    public Optional<BigDecimal> getCurrentMontant(Date date) {
        try {
            return Optional.of(entityManager
                    .createNamedQuery(FraisSejourJournalier.QUERY_GET_CURRENT, FraisSejourJournalier.class)
                    .setParameter("date", date)
                    .getSingleResult()
                    .getMontant());
        } catch (NoResultException | NonUniqueResultException ex) {
            return Optional.empty();
        }
    }

}
