package fr.fava.gestionnaire.application.sejour;

import fr.fava.gestionnaire.domain.enfant.Enfant;
import fr.fava.gestionnaire.domain.famille.Famille;
import fr.fava.gestionnaire.domain.sejour.Sejour;
import java.util.List;
import java.util.stream.Collectors;
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
public class SejourService {

    @Inject
    private EntityManager entityManager;

    public long create(AjouterSejourDTO dto) {
        Famille famille = entityManager.find(Famille.class, dto.getFamille().getId());
        Enfant enfant = entityManager.find(Enfant.class, dto.getEnfant().getId());
        Sejour sejour = new Sejour(famille, enfant, dto.getDateDebut(), dto.getDateFin());
        entityManager.persist(sejour);
        return sejour.getId();
    }

    public List<SejourDTO> get() {
        List<Sejour> entities = entityManager
                .createNamedQuery(Sejour.QUERY_SEJOURS_RETRIEVE, Sejour.class)
                .getResultList();
        return entities
                .stream()
                .map((Sejour s) -> {
                    return new SejourDTO(s);
                })
                .collect(Collectors.toList());
    }

    public List<SejourDTO> rechercher(String nomReferent, String prenomReferent, String nomEnfant, String prenomEnfant) {
        if (nomReferent == null || nomReferent.isEmpty()) {
            nomReferent = "%";
        }
        else {
            nomReferent = "%" + nomReferent + "%";
        }
        if (prenomReferent == null || prenomReferent.isEmpty()) {
            prenomReferent = "%";
        }
        else {
            prenomReferent = "%" + prenomReferent + "%";
        }
        if (nomEnfant == null || nomEnfant.isEmpty()) {
            nomEnfant = "%";
        }
        else {
            nomEnfant = "%" + nomEnfant + "%";
        }
        if (prenomEnfant == null || prenomEnfant.isEmpty()) {
            prenomEnfant = "%";
        }
        else {
            prenomEnfant = "%" + prenomEnfant + "%";
        }
        List<Sejour> entities = entityManager
                .createNamedQuery(Sejour.QUERY_SEJOURS_RECHERCHER, Sejour.class)
                .setParameter("nomReferent", nomReferent)
                .setParameter("prenomReferent", prenomReferent)
                .setParameter("nomEnfant", nomEnfant.trim().toLowerCase())
                .setParameter("prenomEnfant", prenomEnfant.trim().toLowerCase())
                .getResultList();
        return entities
                .stream()
                .map((Sejour s) -> {
                    return new SejourDTO(s);
                })
                .collect(Collectors.toList());
    }

    public void update(Sejour sejour) {
        entityManager.merge(sejour);
    }

    public void delete(long id) {
        Sejour sejour = entityManager.find(Sejour.class, id);
        entityManager.remove(sejour);
    }
    
}
