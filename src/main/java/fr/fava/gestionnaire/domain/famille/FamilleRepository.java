package fr.fava.gestionnaire.domain.famille;

import fr.fava.gestionnaire.domain.famille.Famille;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author paoesco
 */
@RequestScoped
public class FamilleRepository {

    @Inject
    private EntityManager entityManager;

    public List<Famille> retrieve(String nomReferent, String prenomReferent) {
        StringBuilder sQuery = new StringBuilder(" select distinct f from Famille f inner join f.membres m ");
        if (nomReferent != null && !nomReferent.isEmpty()) {
            sQuery.append(" and lower(m.nom) like :nomReferent ");
        }
        if (prenomReferent != null && !prenomReferent.isEmpty()) {
            sQuery.append(" and lower(m.prenom) like :prenomReferent ");
        }

        Query query = entityManager.createQuery(sQuery.toString().replaceFirst("and", "where"), Famille.class);
        if (nomReferent != null && !nomReferent.isEmpty()) {
            query.setParameter("nomReferent", "%" + nomReferent.toLowerCase() + "%");
        }
        if (prenomReferent != null && !prenomReferent.isEmpty()) {
            query.setParameter("prenomReferent", "%" + prenomReferent.toLowerCase() + "%");
        }
        return query.getResultList();
    }

//    private String stripAccents(String s) {
//        s = Normalizer.normalize(s, Normalizer.Form.NFD);
//        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
//        return s;
//    }

}
