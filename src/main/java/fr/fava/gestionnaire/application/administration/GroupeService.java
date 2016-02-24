package fr.fava.gestionnaire.application.administration;

import fr.fava.gestionnaire.domain.administration.Groupe;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author paoesco
 */
@Stateless
public class GroupeService {

    @Inject
    private EntityManager entityManager;

    public List<Groupe> retrieve() {
        return entityManager.createNamedQuery(Groupe.QUERY_LISTE_ALL).getResultList();
    }

    public Groupe retrieve(String nom) {
        return entityManager.find(Groupe.class, nom);
    }

}
