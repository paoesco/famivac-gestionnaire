package fr.fava.gestionnaire.domain.enfant;

import fr.fava.gestionnaire.domain.common.Adresse;
import fr.fava.gestionnaire.domain.common.Coordonnees;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author paoesco
 */
@Entity
public class FamilleAccueil implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    private String prenom;
    private Adresse adresse;
    private Coordonnees coordonnees;

    public FamilleAccueil() {
        adresse = new Adresse();
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }

}
