package fr.famivac.gestionnaire.domains.enfants.entity;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

/** @author paoesco */
@ApplicationScoped
public class EnfantRepository {

  @Inject private EntityManager entityManager;

  public List<Enfant> retrieve(String nomEnfant, String prenomEnfant) {
    StringBuilder sQuery = new StringBuilder(" select e from Enfant e ");
    if (nomEnfant != null && !nomEnfant.isEmpty()) {
      sQuery.append(" and lower(e.nom) like :nomEnfant ");
    }
    if (prenomEnfant != null && !prenomEnfant.isEmpty()) {
      sQuery.append(" and lower(e.prenom) like :prenomEnfant ");
    }
    sQuery.append(" order by e.nom, e.prenom ");

    Query query =
        entityManager.createQuery(sQuery.toString().replaceFirst("and", "where"), Enfant.class);
    if (nomEnfant != null && !nomEnfant.isEmpty()) {
      query.setParameter("nomEnfant", "%" + nomEnfant.toLowerCase() + "%");
    }
    if (prenomEnfant != null && !prenomEnfant.isEmpty()) {
      query.setParameter("prenomEnfant", "%" + prenomEnfant.toLowerCase() + "%");
    }
    return query.getResultList();
  }

  public Long count() {
    String jpql = "select count(e.id) from Enfant e";
    Query q = entityManager.createQuery(jpql);
    return (long) q.getSingleResult();
  }

  //    private String stripAccents(String s) {
  //        s = Normalizer.normalize(s, Normalizer.Form.NFD);
  //        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
  //        return s;
  //    }
}
