package fr.fava.gestionnaire.application.famille;

import fr.fava.gestionnaire.domain.common.Commune;
import fr.fava.gestionnaire.domain.famille.MembreFamille;
import fr.fava.gestionnaire.domain.common.Sexe;
import javax.ejb.Stateless;
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

    public MembreDTO retrieve(@PathParam("id") long id) {
        MembreFamille entity = entityManager.find(MembreFamille.class, id);
        MembreDTO dto = new MembreDTO();
        dto.setId(entity.getId());
        Commune commune = new Commune(entity.getCommuneDeNaissance().getCode(), entity.getCommuneDeNaissance().getVille());
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

    public void update(MembreDTO request) {
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
    }

}
