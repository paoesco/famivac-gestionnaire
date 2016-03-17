package fr.famivac.gestionnaire.sejours.entity;

import fr.famivac.gestionnaire.commons.utils.DateUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author paoesco
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Sejour.QUERY_SEJOURS_DE_LA_FAMILLE, query = "select s from Sejour s where s.familleId = :familleId order by s.dateDebut"),
    @NamedQuery(name = Sejour.QUERY_SEJOURS_DE_L_ENFANT, query = "select s from Sejour s where s.enfantId = :enfantId order by s.dateDebut"),
    @NamedQuery(name = Sejour.QUERY_SEJOURS_RETRIEVE, query = "select s from Sejour s order by s.dateDebut, s.dateFin"),
    @NamedQuery(name = Sejour.QUERY_SEJOURS_RECHERCHER, query = "select s from Sejour s where lower(s.enfantNom) like :nomEnfant and lower(s.enfantPrenom) like :prenomEnfant and lower(s.familleNom) like :nomReferent and lower(s.famillePrenom) like :prenomReferent order by s.dateDebut, s.dateFin")
})
public class Sejour implements Serializable {

    public static final String QUERY_SEJOURS_DE_LA_FAMILLE = "querySejoursDeLaFamille";
    public static final String QUERY_SEJOURS_DE_L_ENFANT = "querySejoursDeLEnfant";
    public static final String QUERY_SEJOURS_RETRIEVE = "querySejoursRetrieve";
    public static final String QUERY_SEJOURS_RECHERCHER = "querySejoursRechercher";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "famille_id", nullable = false)
    @NotNull
    private Long familleId;

    @Column(name = "FAMILLE_NOM", nullable = false)
    private String familleNom;

    @Column(name = "FAMILLE_PRENOM", nullable = false)
    private String famillePrenom;

    @Column(name = "enfant_id", nullable = false)
    @NotNull
    private Long enfantId;

    @Column(name = "ENFANT_NOM", nullable = false)
    private String enfantNom;

    @Column(name = "ENFANT_PRENOM", nullable = false)
    private String enfantPrenom;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Voyage aller;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Voyage retour;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dateFin;
    @Temporal(TemporalType.DATE)
    private Date dateFinReelle;
    private int tarif;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_ANNULATION")
    private Date dateAnnulation;
    @Column(length = 1000, name = "MOTIF_ANNULATION")
    private String motifAnnulation;
    @Column(length = 1000, name = "MOTIF_FIN_SEJOUR")
    private String motifFinSejour;

    @OneToMany(mappedBy = "sejour")
    private Set<Payeur> payeurs;

    protected Sejour() {
        this.tarif = 0;
        this.payeurs = new HashSet<>();
    }

    public Sejour(Date dateDebut, Date dateFin) {
        if (Objects.isNull(dateDebut) || Objects.isNull(dateFin)) {
            throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
        }
        this.dateDebut = (Date) dateDebut.clone();
        this.dateFin = (Date) dateFin.clone();
        this.tarif = 0;
        this.payeurs = new HashSet<>();
    }

    public Sejour(Long familleId,
            String familleNom,
            String famillePrenom,
            Long enfantId,
            String enfantNom,
            String enfantPrenom,
            Date dateDebut,
            Date dateFin) {
        if (Objects.isNull(familleId)
                || Objects.isNull(familleNom)
                || Objects.isNull(famillePrenom)
                || Objects.isNull(enfantId)
                || Objects.isNull(enfantNom)
                || Objects.isNull(enfantPrenom)
                || Objects.isNull(dateDebut)
                || Objects.isNull(dateFin)) {
            throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
        }
        this.familleId = familleId;
        this.familleNom = familleNom;
        this.famillePrenom = famillePrenom;
        this.enfantId = enfantId;
        this.enfantNom = enfantNom;
        this.enfantPrenom = enfantPrenom;
        this.aller = new Voyage();
        this.retour = new Voyage();
        this.dateDebut = (Date) dateDebut.clone();
        this.dateFin = (Date) dateFin.clone();
        this.tarif = 0;
        this.payeurs = new HashSet<>();
    }

    public int nombreJours() {
        if (getDateAnnulation() != null) {
            return 0;
        }
        LocalDate lDateDebut = DateUtils.toLocalDate(getDateDebut());
        LocalDate lDateFin = DateUtils.toLocalDate(getDateFinEffective());
        return (int) ChronoUnit.DAYS.between(lDateDebut, lDateFin);
        //return Period.between(lDateDebut, lDateFin).getDays();
    }

    public Long getId() {
        return id;
    }

    public Long getFamilleId() {
        return familleId;
    }

    public String getFamilleNom() {
        return familleNom;
    }

    public void setFamilleNom(String familleNom) {
        this.familleNom = familleNom;
    }

    public String getFamillePrenom() {
        return famillePrenom;
    }

    public void setFamillePrenom(String famillePrenom) {
        this.famillePrenom = famillePrenom;
    }

    public Long getEnfantId() {
        return enfantId;
    }

    public String getEnfantNom() {
        return enfantNom;
    }

    public void setEnfantNom(String enfantNom) {
        this.enfantNom = enfantNom;
    }

    public String getEnfantPrenom() {
        return enfantPrenom;
    }

    public void setEnfantPrenom(String enfantPrenom) {
        this.enfantPrenom = enfantPrenom;
    }

    public Voyage getAller() {
        return aller;
    }

    public Voyage getRetour() {
        return retour;
    }

    public Date getDateDebut() {
        return Objects.isNull(dateDebut) ? null : (Date) dateDebut.clone();
    }

    public void setDateDebut(Date dateDebut) {
        if (Objects.isNull(dateDebut)) {
            throw new IllegalArgumentException("La date de début est obligatoire");
        }
        this.dateDebut = (Date) dateDebut.clone();
    }

    public Date getDateFin() {
        return Objects.isNull(dateFin) ? null : (Date) dateFin.clone();
    }

    public void setDateFin(Date dateFin) {
        if (Objects.isNull(dateFin)) {
            throw new IllegalArgumentException("La date de fin est obligatoire");
        }
        this.dateFin = (Date) dateFin.clone();
    }

    public Date getDateFinReelle() {
        return Objects.isNull(dateFinReelle) ? null : (Date) dateFinReelle.clone();
    }

    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = Objects.isNull(dateFinReelle) ? null : (Date) dateFinReelle.clone();
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public Date getDateFinEffective() {
        return getDateFinReelle() == null ? getDateFin() : getDateFinReelle();
    }

    public Set<Payeur> getPayeurs() {
        return Collections.unmodifiableSet(payeurs);
    }

    public void setPayeurs(Set<Payeur> payeurs) {
        this.payeurs = new HashSet<>(payeurs);
    }

    public void ajouterPayeur(Payeur payeur) {
        if (payeur == null) {
            throw new IllegalArgumentException("Le payeur est obligatoire");
        }
        this.payeurs.add(payeur);
    }

    public void retirerPayeur(Payeur payeur) {
        if (payeur == null) {
            throw new IllegalArgumentException("Le payeur est obligatoire");
        }
        this.payeurs.remove(payeur);
    }

    public Date getDateAnnulation() {
        return Objects.isNull(dateAnnulation) ? null : (Date) dateAnnulation.clone();
    }

    public void setDateAnnulation(Date dateAnnulation) {
        this.dateAnnulation = Objects.isNull(dateAnnulation) ? null : (Date) dateAnnulation.clone();
    }

    public String getMotifAnnulation() {
        return motifAnnulation;
    }

    public void setMotifAnnulation(String motifAnnulation) {
        this.motifAnnulation = motifAnnulation;
    }

    public String getMotifFinSejour() {
        return motifFinSejour;
    }

    public void setMotifFinSejour(String motifFinSejour) {
        this.motifFinSejour = motifFinSejour;
    }

    public Optional<StatutSejour> statut(Date date) {
        if (!Objects.isNull(getDateAnnulation())) {
            return Optional.of(StatutSejour.ANNULE);
        }
        if (date.before(getDateDebut())) {
            return Optional.of(StatutSejour.A_VENIR);
        }
        if (DateUtils.between(date, getDateDebut(), getDateFinEffective())) {
            return Optional.of(StatutSejour.EN_COURS);
        }
        if (DateUtils.after(date, getDateFinEffective())) {
            return getDateFinReelle() == null ? Optional.of(StatutSejour.TERMINE) : Optional.of(StatutSejour.TERMINE_PREMATUREMENT);
        }
        return Optional.empty();
    }

    public StatutSejour statutDuJour() {
        return statut(new Date()).get();
    }

}
