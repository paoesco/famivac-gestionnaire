package fr.fava.gestionnaire.application.famille;

import fr.fava.gestionnaire.domain.famille.FamilleRepository;
import fr.fava.gestionnaire.domain.common.Adresse;
import fr.fava.gestionnaire.domain.famille.Chambre;
import fr.fava.gestionnaire.domain.common.Commune;
import fr.fava.gestionnaire.domain.famille.Famille;
import fr.fava.gestionnaire.domain.famille.MembreFamille;
import fr.fava.gestionnaire.domain.common.Sexe;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;
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
        Commune communeMembre = request.getMembrePrincipal().getCommuneDeNaissance();
        MembreFamille membre = new MembreFamille(
                request.getMembrePrincipal().getNom(),
                request.getMembrePrincipal().getNomDeNaissance(),
                request.getMembrePrincipal().getPrenom(),
                Sexe.valueOf(request.getMembrePrincipal().getSexe()),
                request.getMembrePrincipal().getDateNaissance(),
                request.getMembrePrincipal().getProfession(),
                true,
                communeMembre,
                request.getMembrePrincipal().getCoordonnees());
        entity.ajouterMembre(membre);
        entityManager.persist(entity);
        return entity.getId();
    }

    public Famille get(@PathParam("id") long id) {
        return entityManager.find(Famille.class, id);
    }

    public List<FamilleDTO> rechercher(String nomReferent, String prenomReferent) {
        List<Famille> beans = repository.retrieve(nomReferent, prenomReferent);
        List<FamilleDTO> dtos = beans.stream().map((Famille f) -> {
            return new FamilleDTO(f);
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
        MembreFamille membre = new MembreFamille(
                request.getNom(),
                request.getNomDeNaissance(),
                request.getPrenom(),
                Sexe.valueOf(request.getSexe()),
                request.getDateNaissance(),
                request.getProfession(),
                commune,
                request.getCoordonnees());
        famille.ajouterMembre(membre);
        return membre.getId();

    }

    public void removeMembre(long familleId, long membreId) {
        Famille famille = entityManager.find(Famille.class, familleId);
        MembreFamille membre = entityManager.find(MembreFamille.class, membreId);
        famille.retirerMembre(membre);
    }

    public void definirReferent(long familleId, long membreId) {
        Famille famille = entityManager.find(Famille.class, familleId);
        famille.definirReferent(membreId);
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
