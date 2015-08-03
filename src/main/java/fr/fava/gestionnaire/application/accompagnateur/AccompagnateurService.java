package fr.fava.gestionnaire.application.accompagnateur;

import fr.fava.gestionnaire.domain.sejour.Accompagnateur;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import net.bull.javamelody.MonitoringInterceptor;

/**
 *
 * @author paoesco
 */
@Stateless
@Interceptors({MonitoringInterceptor.class})
public class AccompagnateurService {

    @Inject
    private EntityManager entityManager;

    public Long create(Accompagnateur bean) {
        entityManager.persist(bean);
        return bean.getId();
    }

    public void update(Accompagnateur bean) {
        entityManager.merge(bean);
    }

    public void delete(Long id) {
        entityManager.remove(get(id));
    }

    public Accompagnateur get(Long id) {
        return entityManager.find(Accompagnateur.class, id);
    }

}
