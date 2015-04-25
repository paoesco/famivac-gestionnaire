package fr.fava.gestionnaire.application.famille;

import fr.fava.gestionnaire.domain.famille.FamilleRepository;
import fr.fava.gestionnaire.domain.model.Adresse;
import fr.fava.gestionnaire.domain.famille.Chambre;
import fr.fava.gestionnaire.domain.model.Commune;
import fr.fava.gestionnaire.domain.famille.Famille;
import fr.fava.gestionnaire.domain.famille.MembreFamille;
import fr.fava.gestionnaire.domain.model.Sexe;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import net.bull.javamelody.MonitoringInterceptor;

/**
 * @author paoesco
 */
@Stateless
@Interceptors({MonitoringInterceptor.class})
public class FamilleService {

    @Inject
    private EntityManager entityManager;

    @Inject
    private FamilleRepository repository;

    public Long create(CreateFamilleRequestDTO request) {
        Commune communeFamille = new Commune(request.getAdresse().getCommune().getCode(), request.getAdresse().getCommune().getVille());
        Adresse adresse = new Adresse(request.getAdresse().getLigneAdresseUne(), request.getAdresse().getLigneAdresseDeux(), communeFamille);
        Famille entity = new Famille(adresse, request.getProjet());
        entityManager.persist(entity);

        Commune communeMembre = request.getMembrePrincipal().getCommuneDeNaissance();
        MembreFamille membre = new MembreFamille(entity,
                request.getMembrePrincipal().getNom(),
                request.getMembrePrincipal().getNomDeNaissance(),
                request.getMembrePrincipal().getPrenom(),
                Sexe.valueOf(request.getMembrePrincipal().getSexe()),
                request.getMembrePrincipal().getDateNaissance(),
                request.getMembrePrincipal().getProfession(),
                true,
                communeMembre,
                request.getMembrePrincipal().getCoordonnees());
        entityManager.persist(membre);
        entity.ajouterMembre(membre);
        return entity.getId();
    }

    public Famille retrieve(@PathParam("id") long id) {
        return entityManager.find(Famille.class, id);
    }

    public List<RetrieveFamillesResponseDTO> retrieve(String nomReferent, @QueryParam("param1") String prenomReferent) {
        List<Famille> beans = repository.retrieve(nomReferent, prenomReferent);
        List<RetrieveFamillesResponseDTO> dtos = beans.stream().map((Famille f) -> {
            RetrieveFamillesResponseDTO dto = new RetrieveFamillesResponseDTO();
            dto.setId(f.getId());
            dto.setNomReferent(f.getMembreReferent().getNom());
            dto.setPrenomReferent(f.getMembreReferent().getPrenom());
            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }

    public void update(Famille entity) {
        entityManager.merge(entity);
    }

    public void delete(long id) {
        Famille famille = entityManager.find(Famille.class, id);
        if (famille == null) {
            throw new IllegalArgumentException("La famille n'existe pas");
        }
        entityManager.remove(famille);
    }

    public void deleteChambre(long id) {
        Chambre entity = entityManager.find(Chambre.class, id);
        entity.getFamille().retirerChambre(entity);
        entityManager.remove(entity);
    }

    public Long addMembre(long familleId, MembreDTO request) {
        Famille famille = entityManager.find(Famille.class, familleId);
        if (famille == null) {
            throw new IllegalArgumentException("La famille n'existe pas");
        }
        Commune commune = new Commune(request.getCommuneDeNaissance().getCode(), request.getCommuneDeNaissance().getVille());
        MembreFamille membre = new MembreFamille(famille,
                request.getNom(),
                request.getNomDeNaissance(),
                request.getPrenom(),
                Sexe.valueOf(request.getSexe()),
                request.getDateNaissance(),
                request.getProfession(),
                commune,
                request.getCoordonnees());
        entityManager.persist(membre);
        famille.ajouterMembre(membre);
        return membre.getId();

    }

    public Chambre addChambre(long familleId, Chambre entity) {
        Famille famille = entityManager.find(Famille.class, familleId);
        if (famille == null) {
            throw new IllegalArgumentException("La famille n'existe pas");
        }
        entityManager.persist(entity);
        famille.ajouterChambre(entity);
        return entity;
    }

}
