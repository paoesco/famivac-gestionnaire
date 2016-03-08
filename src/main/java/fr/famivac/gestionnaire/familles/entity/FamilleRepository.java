package fr.famivac.gestionnaire.familles.entity;

import java.util.List;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author paoesco
 */
@ApplicationScoped
public class FamilleRepository {

    @Inject
    private EntityManager entityManager;

    public List<Famille> retrieve(String nomReferent, String prenomReferent, Set<PeriodeAccueil> periodesAccueil) {
        StringBuilder sQuery = new StringBuilder(" select distinct f from Famille f join f.membres m ");
        if (periodesAccueil != null && !periodesAccueil.isEmpty()) {
            sQuery.append(" join f.periodesSouhaitees periode ");
        }
        if (nomReferent != null && !nomReferent.isEmpty()) {
            sQuery.append(" and lower(m.nom) like :nomReferent ");
        }
        if (prenomReferent != null && !prenomReferent.isEmpty()) {
            sQuery.append(" and lower(m.prenom) like :prenomReferent ");
        }
        if (periodesAccueil != null && !periodesAccueil.isEmpty()) {
            sQuery.append(" and periode in :periodesAccueil ");
        }

        Query query = entityManager.createQuery(sQuery.toString().replaceFirst("and", "where"), Famille.class);
        if (nomReferent != null && !nomReferent.isEmpty()) {
            query.setParameter("nomReferent", "%" + nomReferent.toLowerCase() + "%");
        }
        if (prenomReferent != null && !prenomReferent.isEmpty()) {
            query.setParameter("prenomReferent", "%" + prenomReferent.toLowerCase() + "%");
        }
        if (periodesAccueil != null && !periodesAccueil.isEmpty()) {
            query.setParameter("periodesAccueil", periodesAccueil);
        }
        return query.getResultList();
    }

    public long count() {
        String jpql = "select count(f.id) from Famille f";
        Query q = entityManager.createQuery(jpql);
        return (long) q.getSingleResult();
    }

//    private String stripAccents(String s) {
//        s = Normalizer.normalize(s, Normalizer.Form.NFD);
//        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
//        return s;
//    }
}
