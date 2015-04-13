package fr.fava.gestionnaire.domain.model;

import fr.fava.gestionnaire.domain.utils.Email;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author paoesco
 */
@Embeddable
public class Coordonnees implements Serializable, Cloneable {

    private String telephone1;

    private String telephone2;

    @Email
    private String email;

    private String fax;

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public Coordonnees clone() {
        try {
            return (Coordonnees) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

}
