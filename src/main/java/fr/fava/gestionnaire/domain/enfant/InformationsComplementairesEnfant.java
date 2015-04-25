package fr.fava.gestionnaire.domain.enfant;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * @author paoesco
 */
@Embeddable
public class InformationsComplementairesEnfant implements Serializable {

    private boolean attestationCMU;

    private boolean carteVitale;

    private String contactUrgence;

    private String telephoneUrgence;

    private boolean enuretique;

    private boolean familleASE;

    public boolean isAttestationCMU() {
        return attestationCMU;
    }

    public void setAttestationCMU(boolean attestationCMU) {
        this.attestationCMU = attestationCMU;
    }

    public boolean isCarteVitale() {
        return carteVitale;
    }

    public void setCarteVitale(boolean carteVitale) {
        this.carteVitale = carteVitale;
    }

    public String getContactUrgence() {
        return contactUrgence;
    }

    public void setContactUrgence(String contactUrgence) {
        this.contactUrgence = contactUrgence;
    }

    public String getTelephoneUrgence() {
        return telephoneUrgence;
    }

    public void setTelephoneUrgence(String telephoneUrgence) {
        this.telephoneUrgence = telephoneUrgence;
    }

    public boolean isEnuretique() {
        return enuretique;
    }

    public void setEnuretique(boolean enuretique) {
        this.enuretique = enuretique;
    }

    public boolean isFamilleASE() {
        return familleASE;
    }

    public void setFamilleASE(boolean familleASE) {
        this.familleASE = familleASE;
    }

}
