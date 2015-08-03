package fr.fava.gestionnaire.application;

import fr.fava.gestionnaire.domain.Entity;
import java.lang.reflect.ParameterizedType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author paoesco
 * @param <E>
 */
public abstract class CrudService<E extends Entity> {

    private final Class<E> persistentClass;

    @Inject
    private EntityManager entityManager;

    public CrudService() {
        persistentClass = (Class<E>) ((ParameterizedType) ((Class) getClass().getGenericSuperclass()).getGenericSuperclass()).getActualTypeArguments()[0];
        throw new UnsupportedOperationException("Not working");
    }

    public Long create(E bean) {
        entityManager.persist(bean);
        return bean.getId();
    }

    public void update(E bean) {
        entityManager.merge(bean);
    }

    public void delete(Long id) {
        entityManager.remove(get(id));
    }

    public E get(Long id) {
        return entityManager.find(persistentClass, id);
    }

}
