package fr.famivac.gestionnaire.familles.control;

import fr.famivac.gestionnaire.commons.entity.Commune;
import fr.famivac.gestionnaire.familles.entity.MembreFamille;
import fr.famivac.gestionnaire.commons.entity.Sexe;
import fr.famivac.gestionnaire.commons.events.UpdateFamilleEvent;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;
import net.bull.javamelody.MonitoringInterceptor;

/**
 *
 * @author paoesco
 */
@Stateless
@Interceptors({MonitoringInterceptor.class})
public class MembreService {
    
    @Inject
    private EntityManager entityManager;
    
    @Inject
    Event<UpdateFamilleEvent> updateFamilleEvent;
    
    public MembreDTO retrieve(@PathParam("id") long id) {
        MembreFamille entity = entityManager.find(MembreFamille.class, id);
        MembreDTO dto = new MembreDTO();
        dto.setId(entity.getId());
        Commune commune = null;
        if (entity.getCommuneDeNaissance() != null) {
            commune = new Commune(entity.getCommuneDeNaissance().getCode(), entity.getCommuneDeNaissance().getVille());
        }
        dto.setCommuneDeNaissance(commune);
        dto.setDateNaissance(entity.getDateNaissance());
        dto.setLienDeParente(entity.getLienDeParente());
        dto.setNom(entity.getNom());
        dto.setNomDeNaissance(entity.getNomDeNaissance());
        dto.setPrenom(entity.getPrenom());
        dto.setProfession(entity.getProfession());
        dto.setReferent(entity.isReferent());
        dto.setSexe(entity.getSexe().name());
        dto.setCoordonnees(entity.getCoordonnees());
        return dto;
        
    }
    
    public void update(Long familleId, MembreDTO request) {
        MembreFamille entity = entityManager.find(MembreFamille.class, request.getId());
        Commune commune = new Commune(request.getCommuneDeNaissance().getCode(), request.getCommuneDeNaissance().getVille());
        entity.setCommuneDeNaissance(commune);
        entity.setDateNaissance(request.getDateNaissance());
        entity.setLienDeParente(request.getLienDeParente());
        entity.setNom(request.getNom());
        entity.setNomDeNaissance(request.getNomDeNaissance());
        entity.setPrenom(request.getPrenom());
        entity.setProfession(request.getProfession());
        entity.setReferent(request.isReferent());
        entity.setSexe(Sexe.valueOf(request.getSexe()));
        entity.setCoordonnees(request.getCoordonnees());
        entityManager.merge(entity);

        // Publish message
        UpdateFamilleEvent event = new UpdateFamilleEvent();
        event.setId(familleId);
        event.setNom(entity.getNom());
        event.setPrenom(entity.getPrenom());
        event.setReferent(entity.isReferent());
        updateFamilleEvent.fire(event);
    }
    
}
