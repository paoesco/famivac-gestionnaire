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
public class AccompagnateurRepository {

    @Inject
    private EntityManager entityManager;

    public List<Accompagnateur> get() {
        return entityManager
                .createNamedQuery(Accompagnateur.QUERY_GET_ALL, Accompagnateur.class)
                .getResultList();
    }

    public List<Accompagnateur> rechercher(String nom, String prenom) {
        if (nom == null || nom.isEmpty()) {
            nom = "%";
        } else {
            nom = nom + "%";
        }
        if (prenom == null || prenom.isEmpty()) {
            prenom = "%";
        } else {
            prenom = prenom + "%";
        }
        return entityManager
                .createNamedQuery(Accompagnateur.QUERY_RECHERCHER, Accompagnateur.class)
                .setParameter("nom", nom.trim().toLowerCase())
                .setParameter("prenom", prenom.trim().toLowerCase())
                .getResultList();
    }

}
