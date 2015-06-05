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
    
    public List<SejourDTO> retrieve() {
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
    
    public void update(Sejour sejour) {
        entityManager.merge(sejour);
    }
    
    public void delete(long id) {
        Sejour sejour = entityManager.find(Sejour.class, id);
        entityManager.remove(sejour);
    }
    
}
