package fr.famivac.gestionnaire.sejours.control;

import fr.famivac.gestionnaire.commons.events.UpdateEnfantEvent;
import fr.famivac.gestionnaire.commons.events.UpdateFamilleEvent;
import fr.famivac.gestionnaire.sejours.entity.Sejour;
import fr.famivac.gestionnaire.sejours.entity.StatutSejour;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
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
        Sejour sejour = new Sejour(dto.getFamilleId(),
                dto.getFamilleNom(),
                dto.getFamillePrenom(),
                dto.getEnfantId(),
                dto.getEnfantNom(),
                dto.getEnfantPrenom(),
                dto.getDateDebut(),
                dto.getDateFin());
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

    public List<SejourDTO> rechercher(String nomReferent, String prenomReferent, String nomEnfant, String prenomEnfant, StatutSejour statutSejour) {
        if (nomReferent == null || nomReferent.isEmpty()) {
            nomReferent = "%";
        } else {
            nomReferent = "%" + nomReferent + "%";
        }
        if (prenomReferent == null || prenomReferent.isEmpty()) {
            prenomReferent = "%";
        } else {
            prenomReferent = "%" + prenomReferent + "%";
        }
        if (nomEnfant == null || nomEnfant.isEmpty()) {
            nomEnfant = "%";
        } else {
            nomEnfant = "%" + nomEnfant + "%";
        }
        if (prenomEnfant == null || prenomEnfant.isEmpty()) {
            prenomEnfant = "%";
        } else {
            prenomEnfant = "%" + prenomEnfant + "%";
        }
        List<Sejour> entities = entityManager
                .createNamedQuery(Sejour.QUERY_SEJOURS_RECHERCHER, Sejour.class)
                .setParameter("nomReferent", nomReferent)
                .setParameter("prenomReferent", prenomReferent)
                .setParameter("nomEnfant", nomEnfant.trim().toLowerCase())
                .setParameter("prenomEnfant", prenomEnfant.trim().toLowerCase())
                .getResultList();

        Stream<Sejour> stream = entities.stream();
        if (statutSejour != null) {
            stream = stream.filter((Sejour s) -> {
                return statutSejour.equals(s.statutDuJour());
            });
        }
        return stream
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

    /**
     * Listen to message coming from enfants.
     *
     * @param event
     */
    @Asynchronous
    public void updateEnfant(@Observes UpdateEnfantEvent pEvent) {
        UpdateEnfantEvent event = pEvent; // FIX https://issues.jboss.org/browse/WELD-2019
        List<Sejour> sejours = entityManager.createNamedQuery(Sejour.QUERY_SEJOURS_DE_L_ENFANT, Sejour.class)
                .setParameter("enfantId", event.getId())
                .getResultList();
        sejours.forEach(s -> {
            s.setEnfantNom(event.getNom());
            s.setEnfantPrenom(event.getPrenom());
        });

    }

    /**
     * Listen to message coming from familles.
     *
     * @param pEvent
     */
    @Asynchronous
    public void updateFamille(@Observes UpdateFamilleEvent pEvent) {
        UpdateFamilleEvent event = pEvent; // FIX https://issues.jboss.org/browse/WELD-2019
        if (event.isReferent()) { // Update only if the event concerns a referent
            List<Sejour> sejours = entityManager.createNamedQuery(Sejour.QUERY_SEJOURS_DE_LA_FAMILLE, Sejour.class)
                    .setParameter("familleId", event.getId())
                    .getResultList();
            sejours.forEach(s -> {
                s.setFamilleNom(event.getNom());
                s.setFamillePrenom(event.getPrenom());
            });
        }

    }

}
