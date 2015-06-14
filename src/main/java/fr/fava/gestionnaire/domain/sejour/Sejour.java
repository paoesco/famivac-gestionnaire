package fr.fava.gestionnaire.domain.sejour;

import fr.fava.gestionnaire.domain.enfant.Enfant;
import fr.fava.gestionnaire.domain.enfant.Payeur;
import fr.fava.gestionnaire.domain.famille.Famille;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = Sejour.QUERY_SEJOURS_DE_LA_FAMILLE, query = "select s from Sejour s where s.famille.id = :familleId order by s.dateDebut"),
    @NamedQuery(name = Sejour.QUERY_SEJOURS_RETRIEVE, query = "select s from Sejour s order by s.dateDebut, s.dateFin"),
    @NamedQuery(name = Sejour.QUERY_SEJOURS_RECHERCHER, query = "select s from Sejour s join s.famille f join f.membres m where lower(s.enfant.nom) like :nomEnfant and lower(s.enfant.prenom) like :prenomEnfant and lower(m.nom) like :nomReferent and lower(m.prenom) like :prenomReferent and m.referent = true  order by s.dateDebut, s.dateFin")
})
public class Sejour implements Serializable {

    public static final String QUERY_SEJOURS_DE_LA_FAMILLE = "querySejoursDeLaFamille";
    public static final String QUERY_SEJOURS_RETRIEVE = "querySejoursRetrieve";
    public static final String QUERY_SEJOURS_RECHERCHER = "querySejoursRechercher";

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @NotNull
    private Famille famille;

    @ManyToOne
    @NotNull
    private Enfant enfant;

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

    @OneToMany(mappedBy = "sejour")
    private Set<Payeur> payeurs;

    protected Sejour() {
        this.tarif = 0;
        this.payeurs = new HashSet<>();
    }

    protected Sejour(Date dateDebut, Date dateFin) {
        if (Objects.isNull(dateDebut) || Objects.isNull(dateFin)) {
            throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
        }
        this.dateDebut = (Date) dateDebut.clone();
        this.dateFin = (Date) dateFin.clone();
        this.tarif = 0;
        this.payeurs = new HashSet<>();
    }

    public Sejour(Famille famille, Enfant enfant, Date dateDebut, Date dateFin) {
        if (Objects.isNull(famille) || Objects.isNull(enfant) || Objects.isNull(dateDebut) || Objects.isNull(dateFin)) {
            throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
        }
        this.famille = famille;
        this.enfant = enfant;
        this.aller = new Voyage();
        this.retour = new Voyage();
        this.dateDebut = (Date) dateDebut.clone();
        this.dateFin = (Date) dateFin.clone();
        this.tarif = 0;
        this.payeurs = new HashSet<>();
    }

    public int nombreJours() {
        LocalDate lDateDebut = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate lDateFin = getDateFinEffective().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(lDateDebut, lDateFin).getDays();
    }

    public Long getId() {
        return id;
    }

    public Famille getFamille() {
        return famille;
    }

    public Enfant getEnfant() {
        return enfant;
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

}
