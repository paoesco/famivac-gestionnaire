package fr.fava.gestionnaire.domain.enfant;

import fr.fava.gestionnaire.domain.inscripteur.Inscripteur;
import fr.fava.gestionnaire.domain.inscripteur.TypeInscripteur;
import fr.fava.gestionnaire.domain.common.Adresse;
import fr.fava.gestionnaire.domain.common.Coordonnees;
import fr.fava.gestionnaire.domain.utils.Email;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author paoesco
 */
@Entity
public class ResponsableLegal implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private Set<Enfant> enfants;

    @Enumerated(EnumType.STRING)
    private TypeInscripteur type;

    private String nom;

    private String prenom;

    private String organisme;

    @Embedded
    private Adresse adresse;

    private String lienDeParente;

    @Embedded
    private Coordonnees coordonnees;

    public ResponsableLegal() {
        enfants = new HashSet<>();
        adresse = new Adresse();
        coordonnees = new Coordonnees();
    }

    public ResponsableLegal(Inscripteur inscripteur) {
        this();
        if (!inscripteur.isResponsableLegal()) {
            throw new IllegalArgumentException("L'inscripteur doit être le responsable légal");
        }
        enfants = new HashSet<>(inscripteur.getEnfants());
        type = inscripteur.getType();
        nom = inscripteur.getNom();
        prenom = inscripteur.getPrenom();
        adresse = inscripteur.getAdresse();
        organisme = inscripteur.getOrganisme();
        coordonnees = inscripteur.getCoordonnees().clone();

    }

    public Long getId() {
        return id;
    }

    public Set<Enfant> getEnfants() {
        return Collections.unmodifiableSet(enfants);
    }

    public void ajouterEnfant(Enfant enfant) {
        enfants.add(enfant);
    }

    public void retirerEnfant(Enfant enfant) {
        enfants.remove(enfant);
    }

    public TypeInscripteur getType() {
        return type;
    }

    public void setType(TypeInscripteur type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getLienDeParente() {
        return lienDeParente;
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

}
