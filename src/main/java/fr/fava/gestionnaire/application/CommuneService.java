package fr.fava.gestionnaire.application;

import fr.fava.gestionnaire.domain.common.Commune;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author paoesco
 */
@Stateless
public class CommuneService {

    @Inject
    private EntityManager entityManager;

    public Commune create(Commune entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void delete(String code) {
        Commune entity = entityManager.find(Commune.class, code);
        entityManager.remove(entity);
    }

    public void update(Commune request) {
        Commune entity = entityManager.find(Commune.class, request.getCode());
        entity.setVille(request.getVille());
        entityManager.merge(entity);
    }

    public Commune retrieve(String code) {
        return entityManager.find(Commune.class, code);
    }

    public List<Commune> retrieve() {
        return entityManager.createNamedQuery(Commune.QUERY_RETRIEVE_ALL, Commune.class).getResultList();
    }

}
