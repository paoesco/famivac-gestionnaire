package fr.fava.gestionnaire.domain.common;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Conserver l'historique des adresses.
 *
 * @author paoesco
 */
@Embeddable
public class Adresse implements Serializable {

    @Column(nullable = false)
    private String ligneAdresseUne;

    @Column(nullable = false)
    private String ligneAdresseDeux;

    @ManyToOne(optional = false)
    private Commune commune;

    public Adresse() {

    }

    public Adresse(String ligneAdresseUne, String ligneAdresseDeux, Commune commune) {
        if (ligneAdresseUne == null
                || ligneAdresseUne.isEmpty()
                || commune == null) {
            throw new IllegalArgumentException("Tous les paramètres sont obligatoires");
        }
        this.ligneAdresseUne = ligneAdresseUne;
        this.ligneAdresseDeux = ligneAdresseDeux;
        this.commune = commune;
    }

    public String getLigneAdresseUne() {
        return ligneAdresseUne;
    }

    public void setLigneAdresseUne(String ligneAdresseUne) {
        this.ligneAdresseUne = ligneAdresseUne;
    }

    public String getLigneAdresseDeux() {
        return ligneAdresseDeux;
    }

    public void setLigneAdresseDeux(String ligneAdresseDeux) {
        this.ligneAdresseDeux = ligneAdresseDeux;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        if (commune.getCode() == null || commune.getCode().isEmpty()) {
            throw new IllegalArgumentException("Le code commune est obligatoire");
        }
        this.commune = commune;
    }

}
