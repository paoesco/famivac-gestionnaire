package fr.famivac.gestionnaire.administration.parametres.control;

import fr.famivac.gestionnaire.administration.parametres.entity.Tarif;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import net.bull.javamelody.MonitoringInterceptor;

/**
 * @author paoesco
 */
@Stateless
@Interceptors({MonitoringInterceptor.class})
public class TarifsService {

    @Inject
    private EntityManager entityManager;

    public Long create(Tarif entity) {
        entityManager.persist(entity);
        return entity.getId();
    }

    public List<Tarif> retrieve() {
        return entityManager.createNamedQuery(Tarif.QUERY_LISTE_ALL).getResultList();
    }

    public void update(Tarif entity) {
        entityManager.merge(entity);
    }

    public void delete(Long id) {
        Tarif entity = entityManager.find(Tarif.class, id);
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException("Le tarif n'existe pas");
        }
        entityManager.remove(entity);
    }

}
