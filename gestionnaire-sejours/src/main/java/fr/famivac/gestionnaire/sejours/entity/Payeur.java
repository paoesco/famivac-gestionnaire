package fr.famivac.gestionnaire.sejours.entity;

import fr.famivac.gestionnaire.commons.entity.Adresse;
import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author paoesco
 */
@Entity
public class Payeur implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypePayeur type;

    private String nom;

    private String prenom;

    @Embedded
    private Adresse adresse;

    @ManyToOne
    private Sejour sejour;

    public Payeur() {
        adresse = new Adresse();
    }

    public Long getId() {
        return id;
    }

    public TypePayeur getType() {
        return type;
    }

    public void setType(TypePayeur type) {
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Sejour getSejour() {
        return sejour;
    }

    public void setSejour(Sejour sejour) {
        this.sejour = sejour;
    }

}
