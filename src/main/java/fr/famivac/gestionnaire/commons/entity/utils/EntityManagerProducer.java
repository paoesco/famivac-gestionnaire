package fr.famivac.gestionnaire.commons.entity.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author paoesco
 */
@ApplicationScoped
public class EntityManagerProducer {

    @Produces
    @PersistenceContext(name = "gestionnairePU")
    private EntityManager entityManager;

}
