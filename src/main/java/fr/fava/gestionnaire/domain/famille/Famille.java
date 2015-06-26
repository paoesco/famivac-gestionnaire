package fr.fava.gestionnaire.domain.famille;

import fr.fava.gestionnaire.domain.common.Adresse;
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

    private boolean sejoursComplets;

    private String precisionsSejoursNonComplets;

    @Column(nullable = false, length = 2000)
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

    private boolean accepteHandicap;

    private boolean accepteMalade;

    private boolean extraitCasierJudiciaire;

    @Temporal(TemporalType.DATE)
    private Date dateReceptionCasierJudiciaire;

    private String nomRecruteur;

    @Temporal(TemporalType.DATE)
    private Date dateRecrutement;

    @Enumerated(EnumType.STRING)
    private Avis avisRecrutement;

    private boolean visiteDdcs;

    @Temporal(TemporalType.DATE)
    private Date dateVisiteDdcs;

    @Enumerated(EnumType.STRING)
    private Avis avisDdcs;

    @Column(length = 2000)
    private String remarque;

    @OneToOne(mappedBy = "famille", cascade = CascadeType.ALL, orphanRemoval = true)
    private InformationsHabitation informationsHabitation;

    protected Famille() {
        this.adresse = new Adresse();
        this.membres = new HashSet<>();
        this.chambres = new HashSet<>();
        this.tranchesAges = new HashSet<>();
        this.periodesSouhaitees = new HashSet<>();
        this.informationsHabitation = new InformationsHabitation(this);
    }

    public Famille(Adresse adresse, String projet) {
        if (adresse == null
                || projet == null
                || projet.isEmpty()) {
            throw new IllegalArgumentException("Tous les paramètres sont obligatoires");
        }
        this.adresse = adresse;
        membres = new HashSet<>();
        this.projet = projet;
        chambres = new HashSet<>();
        this.informationsHabitation = new InformationsHabitation(this);
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
            return m.isReferent();
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

    public boolean isSejoursComplets() {
        return sejoursComplets;
    }

    public void setSejoursComplets(boolean sejoursComplets) {
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

    public boolean isAccepteHandicap() {
        return accepteHandicap;
    }

    public void setAccepteHandicap(boolean accepteHandicap) {
        this.accepteHandicap = accepteHandicap;
    }

    public boolean isAccepteMalade() {
        return accepteMalade;
    }

    public void setAccepteMalade(boolean accepteMalade) {
        this.accepteMalade = accepteMalade;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public boolean isExtraitCasierJudiciaire() {
        return extraitCasierJudiciaire;
    }

    public void setExtraitCasierJudiciaire(boolean extraitCasierJudiciaire) {
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

    public boolean isVisiteDdcs() {
        return visiteDdcs;
    }

    public void setVisiteDdcs(boolean visiteDdcs) {
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
        return informationsHabitation == null ? new InformationsHabitation() : informationsHabitation;
    }

    public void setInformationsHabitation(InformationsHabitation informationsHabitation) {
        this.informationsHabitation = informationsHabitation;
    }

}
