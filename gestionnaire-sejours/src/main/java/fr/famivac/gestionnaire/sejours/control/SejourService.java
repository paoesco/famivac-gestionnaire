package fr.famivac.gestionnaire.sejours.control;

import fr.famivac.gestionnaire.commons.events.UpdateEnfantEvent;
import fr.famivac.gestionnaire.commons.events.UpdateFamilleEvent;
import fr.famivac.gestionnaire.commons.utils.DateUtils;
import fr.famivac.gestionnaire.sejours.entity.PeriodeJournee;
import fr.famivac.gestionnaire.sejours.entity.Sejour;
import fr.famivac.gestionnaire.sejours.entity.SejourRepository;
import fr.famivac.gestionnaire.sejours.entity.StatutSejour;
import fr.famivac.gestionnaire.sejours.entity.VoyageRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
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

    @Inject
    private VoyageRepository voyageRepository;

    @Inject
    private SejourRepository sejourRepository;

    public long create(Long familleId,
            String familleNom,
            String famillePrenom,
            String familleTelephone,
            Long enfantId,
            String enfantNom,
            String enfantPrenom,
            Date dateDebut,
            PeriodeJournee periodeJourneeDebut,
            Date dateFin,
            PeriodeJournee periodeJourneeFin,
            BigDecimal fraisSejourJournalier,
            BigDecimal fraisDossier,
            BigDecimal fraisVoyage,
            BigDecimal fraisPensionFamilleJournalier) {
        Sejour sejour = new Sejour(familleId,
                familleNom,
                famillePrenom,
                enfantId,
                enfantNom,
                enfantPrenom,
                dateDebut,
                periodeJourneeDebut,
                dateFin,
                periodeJourneeFin)
                .withFraisSejourJournalier(fraisSejourJournalier)
                .withFraisDossier(fraisDossier)
                .withFraisVoyage(fraisVoyage)
                .withFraisPensionFamilleJournalier(fraisPensionFamilleJournalier);
        sejour.getAller().setNomPersonneAReception(famillePrenom + " " + familleNom);
        sejour.getAller().setTelephonePersonneAReception(familleTelephone);
        sejour.getRetour().setNomPersonneDepart(famillePrenom + " " + familleNom);
        sejour.getRetour().setTelephonePersonneDepart(familleTelephone);
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
        // Query
        StringBuilder jpql = new StringBuilder(" select s ");
        jpql.append(" from Sejour s ");
        jpql.append( "where lower(s.enfantNom) like :nomEnfant and lower(s.enfantPrenom) like :prenomEnfant ");
        jpql.append(" and lower(s.familleNom) like :nomReferent and lower(s.famillePrenom) like :prenomReferent ");
        if (StatutSejour.EN_COURS.equals(statutSejour)) {
            jpql.append(" and s.dateDebut <= :today and :today < s.dateFin ");
        }
        if (StatutSejour.A_VENIR.equals(statutSejour)) {
            jpql.append(" and :today < s.dateDebut ");
        }
        jpql.append(" order by s.dateDebut, s.dateFin ");

        // Parameters
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
        Query query = entityManager
                .createQuery(jpql.toString(), Sejour.class)
                .setParameter("nomReferent", nomReferent.toLowerCase())
                .setParameter("prenomReferent", prenomReferent.toLowerCase())
                .setParameter("nomEnfant", nomEnfant.trim().toLowerCase())
                .setParameter("prenomEnfant", prenomEnfant.trim().toLowerCase());
        if (StatutSejour.EN_COURS.equals(statutSejour) || StatutSejour.A_VENIR.equals(statutSejour)) {
            query.setParameter("today", new Date(), TemporalType.DATE);
        }
        List<Sejour> entities = query.getResultList();
        Stream<Sejour> stream = entities.stream();
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
     * @param pEvent
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
                s.getAller().setTelephonePersonneAReception(event.getTelephone());
                s.getRetour().setTelephonePersonneDepart(event.getTelephone());
            });
        }

    }

    public List<VoyageDTO> prochainsVoyages(Date date) {
        return voyageRepository.prochainsVoyages(date)
                .stream()
                .map(voyage -> {
                    VoyageDTO dto = new VoyageDTO();
                    String accompagnateurs = voyage
                            .getAccompagnateurs()
                            .stream()
                            .map(acc -> {
                                return acc.getPrenom() + " " + acc.getNom();
                            })
                            .collect(Collectors.joining(","));
                    dto.setAccompagnateur(accompagnateurs);
                    dto.setDateVoyage(voyage.getDateVoyage());
                    dto.setEnfant(voyage.getSejour().getEnfantNom() + " " + voyage.getSejour().getEnfantPrenom());
                    dto.setEnfantId(voyage.getSejour().getEnfantId());
                    dto.setFamille(voyage.getSejour().getFamilleNom() + " " + voyage.getSejour().getFamillePrenom());
                    dto.setFamilleId(voyage.getSejour().getFamilleId());
                    // Heure rdv est automatiquement calculée 30 minutes avant l'heure officielle de départ du transport
                    dto.setHeureRendezVous(DateUtils.sumTimeToDate(voyage.getHeureDepart(), 0, -30, 0));
                    dto.setHeureTransport(voyage.getHeureDepart());
                    dto.setHeureArrivee(voyage.getHeureArrivee());
                    dto.setRetour(voyage.isRetour());
                    dto.setLieu(voyage.getLieuDepart());
                    dto.setNumeroTransport(voyage.getNumeroTrain());
                    String accompagnateursTelephones = voyage
                            .getAccompagnateurs()
                            .stream()
                            .map(acc -> {
                                return acc.getTelephone();
                            })
                            .collect(Collectors.joining(","));
                    dto.setTelephoneAccompagnateur(accompagnateursTelephones);
                    if (voyage.isRetour()) {
                        dto.setContactParis(voyage.getNomPersonneAReception());
                        dto.setTelephoneContactParis(voyage.getTelephonePersonneAReception());
                    } else {
                        dto.setContactParis(voyage.getNomPersonneDepart());
                        dto.setTelephoneContactParis(voyage.getTelephonePersonneDepart());
                    }
                    dto.setSejourId(voyage.getSejour().getId());
                    dto.setVoyageId(voyage.getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public BilanDTO getBilanSurLaPeriode(Date dateDebut, Date dateFin) {
        List<Sejour> sejours = sejourRepository.getSejoursTerminesDansLaPeriode(dateDebut, dateFin);
        BigDecimal totalForfait = BigDecimal.ZERO;
        BigDecimal totalFraisDossier = BigDecimal.ZERO;
        BigDecimal totalFraisVoyage = BigDecimal.ZERO;
        BigDecimal totalFraisPensionFamille = BigDecimal.ZERO;
        Integer totalNombreJourneesVacances = 0;
        for (Sejour sejour : sejours) {
            int nombreJourneesVacancesSejour = sejour.nombreJours();
            totalNombreJourneesVacances += nombreJourneesVacancesSejour;
            totalForfait = totalForfait.add(new BigDecimal(nombreJourneesVacancesSejour).multiply(sejour.getForfaitJournalier()));
            totalFraisDossier = totalFraisDossier.add(sejour.getFraisDossier());
            totalFraisVoyage = totalFraisVoyage.add(sejour.getFraisVoyage());
            totalFraisPensionFamille = totalFraisPensionFamille.add(new BigDecimal(nombreJourneesVacancesSejour).multiply(sejour.getFraisPensionFamilleJournalier()));
        }
        BilanDTO result = new BilanDTO();
        result.setDateDebut(dateDebut);
        result.setDateFin(dateFin);
        result.setTotalForfait(totalForfait);
        result.setTotalFraisDossier(totalFraisDossier);
        result.setTotalFraisVoyage(totalFraisVoyage);
        result.setTotalFraisPensionFamille(totalFraisPensionFamille);
        result.setTotalNombreJourneesVacances(totalNombreJourneesVacances);
        return result;
    }

}
