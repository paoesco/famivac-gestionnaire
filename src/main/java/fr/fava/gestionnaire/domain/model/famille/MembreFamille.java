package fr.fava.gestionnaire.domain.model.famille;

import fr.fava.gestionnaire.domain.model.Commune;
import fr.fava.gestionnaire.domain.model.Coordonnees;
import fr.fava.gestionnaire.domain.model.Sexe;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 * Personnes composants la famille.
 *
 * @author paoesco
 */
@Entity
public class MembreFamille implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Famille famille;

    @Column(nullable = false)
    private String nom;

    private String nomDeNaissance;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;

    @Column(nullable = false)
    private boolean referent;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Commune communeDeNaissance;

    private String profession;

    private String lienDeParente;

    @Embedded
    private Coordonnees coordonnees;

    protected MembreFamille() {
        coordonnees = new Coordonnees();
    }

    public MembreFamille(Famille famille, String nom, String nomDeNaissance, String prenom, Sexe sexe, Date dateNaissance, String profession, Commune communeDeNaissance, Coordonnees coordonnees) {
        this(famille, nom, nomDeNaissance, prenom, sexe, dateNaissance, profession, false, communeDeNaissance, coordonnees);
    }

    public MembreFamille(Famille famille, String nom, String nomDeNaissance, String prenom, Sexe sexe, Date dateNaissance, String profession, boolean referent, Commune communeDeNaissance, Coordonnees coordonnees) {
        if (famille == null
                || nom == null
                || nom.isEmpty()
                || prenom == null
                || prenom.isEmpty()
                || sexe == null
                || dateNaissance == null
                || communeDeNaissance == null) {
            throw new IllegalArgumentException("Tous les paramètres sont obligatoires");
        }
        this.famille = famille;
        this.nom = nom;
        this.nomDeNaissance = nomDeNaissance;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = (Date) dateNaissance.clone();
        this.profession = profession;
        this.referent = referent;
        this.communeDeNaissance = communeDeNaissance;
        if (Objects.isNull(coordonnees)) {
            this.coordonnees = new Coordonnees();
        } else {
            this.coordonnees = coordonnees.clone();
        }

    }

    public Long getId() {
        return id;
    }

    public Famille getFamille() {
        return famille;
    }

    public String getNom() {
        return nom;
    }

    public String getNomDeNaissance() {
        return nomDeNaissance;
    }

    public String getPrenom() {
        return prenom;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance == null ? null : (Date) dateNaissance.clone();
    }

    public boolean isReferent() {
        return referent;
    }

    public Commune getCommuneDeNaissance() {
        return communeDeNaissance;
    }

    public String getProfession() {
        return profession;
    }

    public String getLienDeParente() {
        return lienDeParente;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        this.nom = nom;
    }

    public void setNomDeNaissance(String nomDeNaissance) {
        this.nomDeNaissance = nomDeNaissance;
    }

    public void setPrenom(String prenom) {
        if (prenom == null || prenom.isEmpty()) {
            throw new IllegalArgumentException("Le prénom est obligatoire");
        }
        this.prenom = prenom;
    }

    public void setSexe(Sexe sexe) {
        if (sexe == null) {
            throw new IllegalArgumentException("Le sexe est obligatoire");
        }
        this.sexe = sexe;
    }

    public void setDateNaissance(Date dateNaissance) {
        if (dateNaissance == null) {
            throw new IllegalArgumentException("La date de naissance est obligatoire");
        }
        this.dateNaissance = (Date) dateNaissance.clone();
    }

    public void setReferent(boolean referent) {
        this.referent = referent;
    }

    public void setCommuneDeNaissance(Commune communeDeNaissance) {
        if (communeDeNaissance == null || communeDeNaissance.getCode() == null || communeDeNaissance.getCode().isEmpty()) {
            throw new IllegalArgumentException("La commune de naissance est obligatoire");
        }
        this.communeDeNaissance = communeDeNaissance;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setLienDeParente(String lienDeParente) {
        this.lienDeParente = lienDeParente;
    }

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        if (Objects.isNull(coordonnees)) {
            this.coordonnees = new Coordonnees();
        } else {
            this.coordonnees = coordonnees;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MembreFamille other = (MembreFamille) obj;
        return Objects.equals(this.id, other.id);
    }

}
