package fr.fava.gestionnaire.domain.inscripteur;

import fr.fava.gestionnaire.domain.common.Coordonnees;
import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author paoesco
 */
@Entity
public class ResponsableInscripteur implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    private String prenom;

    @Embedded
    private Coordonnees coordonnees;

    @OneToOne(mappedBy = "responsable")
    private Inscripteur inscripteur;

    public ResponsableInscripteur() {
        coordonnees = new Coordonnees();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }

    public Inscripteur getInscripteur() {
        return inscripteur;
    }

    public void setInscripteur(Inscripteur inscripteur) {
        this.inscripteur = inscripteur;
    }

}
