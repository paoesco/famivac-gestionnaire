package fr.famivac.gestionnaire.familles.entity;

import fr.famivac.gestionnaire.commons.entity.Adresse;
import fr.famivac.gestionnaire.commons.utils.DateUtils;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author paoesco
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Famille.QUERY_LISTE_ALL, query = "select f from Famille f")
})
public class Famille implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String QUERY_LISTE_ALL = "listFamilles";

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn
    private final Set<MembreFamille> membres;

    @Embedded
    private final Adresse adresse;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<PeriodeAccueil> periodesSouhaitees;

    private Boolean sejoursComplets;

    private String precisionsSejoursNonComplets;

    @Column(length = 2000)
    private String projet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final Set<Chambre> chambres;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<TrancheAgeEnfant> tranchesAges;

    @Column(length = 2000)
    private String connaissanceAssociation;

    private Integer nombreFillesSouhaitees;

    private Integer nombreGarconsSouhaites;

    private Boolean accepteHandicap;

    private Boolean accepteMalade;

    private Boolean extraitCasierJudiciaire;

    @Temporal(TemporalType.DATE)
    private Date dateReceptionCasierJudiciaire;

    private String nomRecruteur;

    @Temporal(TemporalType.DATE)
    private Date dateRecrutement;

    @Enumerated(EnumType.STRING)
    private Avis avisRecrutement;

    private Boolean visiteDdcs;

    @Temporal(TemporalType.DATE)
    private Date dateVisiteDdcs;

    @Enumerated(EnumType.STRING)
    private Avis avisDdcs;

    @Column(length = 2000)
    private String remarque;

    @OneToOne(mappedBy = "famille", cascade = CascadeType.ALL, orphanRemoval = true)
    private InformationsHabitation informationsHabitation;

    @OneToOne(mappedBy = "famille", cascade = CascadeType.ALL, orphanRemoval = true)
    private InformationsVehicule informationsVehicule;

    @Column(name = "DATE_RADIATION")
    @Temporal(TemporalType.DATE)
    private Date dateRadiation;

    @Column(name = "CANDIDATURE")
    private Boolean candidature;

    @Column(name = "ARCHIVEE")
    private Boolean archivee;

    protected Famille() {
        this.adresse = new Adresse();
        this.membres = new HashSet<>();
        this.chambres = new HashSet<>();
        this.tranchesAges = new HashSet<>();
        this.periodesSouhaitees = new HashSet<>();
        this.informationsHabitation = new InformationsHabitation(this);
        this.informationsVehicule = new InformationsVehicule(this);
        this.accepteHandicap = false;
        this.accepteMalade = false;
        this.extraitCasierJudiciaire = false;
        this.visiteDdcs = false;
        this.candidature = false;
        this.archivee = false;
    }

    public Famille(Adresse adresse, String projet, Boolean candidature) {
        this.adresse = adresse;
        membres = new HashSet<>();
        this.projet = projet;
        chambres = new HashSet<>();
        this.informationsHabitation = new InformationsHabitation(this);
        this.informationsVehicule = new InformationsVehicule(this);
        this.candidature = candidature;
        this.accepteHandicap = false;
        this.accepteMalade = false;
        this.extraitCasierJudiciaire = false;
        this.visiteDdcs = false;
        this.archivee = false;
    }

    public void ajouterChambre(Chambre chambre) {
        if (chambre == null) {
            throw new IllegalArgumentException("Renseigner la chambre");
        }
        chambres.add(chambre);
    }

    public void retirerChambre(Chambre chambre) {
        if (chambre == null) {
            throw new IllegalArgumentException("Renseigner la chambre");
        }
        chambres.remove(chambre);
    }

    public void ajouterMembre(MembreFamille membre) {
        if (membre == null) {
            throw new IllegalArgumentException("Tous les paramètre sont obligatoires");
        }
        this.membres.add(membre);
    }

    public void retirerMembre(MembreFamille membre) {
        if (membre == null) {
            throw new IllegalArgumentException("Tous les paramètre sont obligatoires");
        }
        this.membres.remove(membre);
    }

    public MembreFamille getMembreReferent() {
        return membres.stream().filter((MembreFamille m) -> {
            return m.getReferent();
        }).findFirst().get();
    }

    public void definirReferent(Long id) {
        membres.stream().forEach((MembreFamille m) -> {
            if (m.getId().equals(id)) {
                m.setReferent(true);
            } else {
                m.setReferent(false);
            }
        });
    }

    public Boolean getRadiee() {
        return dateRadiation != null && DateUtils.after(new Date(), dateRadiation);
    }

    public Long getId() {
        return id;
    }

    public Set<MembreFamille> getMembres() {
        return Collections.unmodifiableSet(membres);
    }

    public String[] getPeriodesSouhaitees() {
        return periodesSouhaitees.stream().map((PeriodeAccueil p) -> {
            return p.name();
        }).collect(Collectors.toList()).toArray(new String[periodesSouhaitees.size()]);
    }

    public void setPeriodesSouhaitees(String[] periodesSouhaitees) {
        this.periodesSouhaitees.clear();
        for (String p : periodesSouhaitees) {
            this.periodesSouhaitees.add(PeriodeAccueil.valueOf(p));
        }
    }

    public Boolean getSejoursComplets() {
        return sejoursComplets;
    }

    public void setSejoursComplets(Boolean sejoursComplets) {
        this.sejoursComplets = sejoursComplets;
    }

    public String getPrecisionsSejoursNonComplets() {
        return precisionsSejoursNonComplets;
    }

    public void setPrecisionsSejoursNonComplets(String precisionsSejoursNonComplets) {
        this.precisionsSejoursNonComplets = precisionsSejoursNonComplets;
    }

    public String getProjet() {
        return projet;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }

    public List<Chambre> getChambres() {
        return chambres.stream().collect(Collectors.toList());
    }

    public String[] getTranchesAges() {
        return tranchesAges.stream().map((TrancheAgeEnfant t) -> {
            return t.name();
        }).collect(Collectors.toList()).toArray(new String[tranchesAges.size()]);
    }

    public void setTranchesAges(String[] tranchesAges) {
        this.tranchesAges.clear();
        for (String t : tranchesAges) {
            this.tranchesAges.add(TrancheAgeEnfant.valueOf(t));
        }
    }

    public String getConnaissanceAssociation() {
        return connaissanceAssociation;
    }

    public void setConnaissanceAssociation(String connaissanceAssociation) {
        this.connaissanceAssociation = connaissanceAssociation;
    }

    public Integer getNombreFillesSouhaitees() {
        return nombreFillesSouhaitees;
    }

    public void setNombreFillesSouhaitees(Integer nombreFillesSouhaitees) {
        this.nombreFillesSouhaitees = nombreFillesSouhaitees;
    }

    public Integer getNombreGarconsSouhaites() {
        return nombreGarconsSouhaites;
    }

    public void setNombreGarconsSouhaites(Integer nombreGarconsSouhaites) {
        this.nombreGarconsSouhaites = nombreGarconsSouhaites;
    }

    public Boolean getAccepteHandicap() {
        return accepteHandicap;
    }

    public void setAccepteHandicap(Boolean accepteHandicap) {
        this.accepteHandicap = accepteHandicap;
    }

    public Boolean getAccepteMalade() {
        return accepteMalade;
    }

    public void setAccepteMalade(Boolean accepteMalade) {
        this.accepteMalade = accepteMalade;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public Boolean getExtraitCasierJudiciaire() {
        return extraitCasierJudiciaire;
    }

    public void setExtraitCasierJudiciaire(Boolean extraitCasierJudiciaire) {
        this.extraitCasierJudiciaire = extraitCasierJudiciaire;
    }

    public Date getDateReceptionCasierJudiciaire() {
        return dateReceptionCasierJudiciaire == null ? null : (Date) dateReceptionCasierJudiciaire.clone();
    }

    public void setDateReceptionCasierJudiciaire(Date dateReceptionCasierJudiciaire) {
        this.dateReceptionCasierJudiciaire = dateReceptionCasierJudiciaire == null ? null : (Date) dateReceptionCasierJudiciaire.clone();
    }

    public String getNomRecruteur() {
        return nomRecruteur;
    }

    public void setNomRecruteur(String nomRecruteur) {
        this.nomRecruteur = nomRecruteur;
    }

    public Date getDateRecrutement() {
        return dateRecrutement == null ? null : (Date) dateRecrutement.clone();
    }

    public void setDateRecrutement(Date dateRecrutement) {
        this.dateRecrutement = dateRecrutement == null ? null : (Date) dateRecrutement.clone();
    }

    public Avis getAvisRecrutement() {
        return avisRecrutement;
    }

    public void setAvisRecrutement(Avis avisRecrutement) {
        this.avisRecrutement = avisRecrutement;
    }

    public Boolean getVisiteDdcs() {
        return visiteDdcs;
    }

    public void setVisiteDdcs(Boolean visiteDdcs) {
        this.visiteDdcs = visiteDdcs;
    }

    public Date getDateVisiteDdcs() {
        return dateVisiteDdcs == null ? null : (Date) dateVisiteDdcs.clone();
    }

    public void setDateVisiteDdcs(Date dateVisiteDdcs) {
        this.dateVisiteDdcs = dateVisiteDdcs == null ? null : (Date) dateVisiteDdcs.clone();
    }

    public Avis getAvisDdcs() {
        return avisDdcs;
    }

    public void setAvisDdcs(Avis avisDdcs) {
        this.avisDdcs = avisDdcs;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public InformationsHabitation getInformationsHabitation() {
        return informationsHabitation == null ? new InformationsHabitation(this) : informationsHabitation;
    }

    public void setInformationsHabitation(InformationsHabitation informationsHabitation) {
        this.informationsHabitation = informationsHabitation;
    }

    public InformationsVehicule getInformationsVehicule() {
        return informationsVehicule == null ? new InformationsVehicule(this) : informationsVehicule;
    }

    public void setInformationsVehicule(InformationsVehicule informationsVehicule) {
        this.informationsVehicule = informationsVehicule;
    }

    public Date getDateRadiation() {
        return dateRadiation == null ? null : (Date) dateRadiation.clone();
    }

    public void setDateRadiation(Date dateRadiation) {
        this.dateRadiation = dateRadiation == null ? null : (Date) dateRadiation.clone();
    }

    public Boolean getCandidature() {
        return candidature;
    }

    public void setCandidature(Boolean candidature) {
        this.candidature = candidature;
    }

    public Boolean getArchivee() {
        return archivee;
    }

    public void setArchivee(Boolean archivee) {
        this.archivee = archivee;
    }

    public Status getStatus() {
        if (getRadiee()) {
            return Status.RADIEE;
        }
        if (!getCandidature()) {
            return Status.ACTIVE;
        }
        if (getCandidature()) {
            return Status.CANDIDATURE;
        }
        throw new IllegalStateException("No status found");
    }

}
