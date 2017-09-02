package fr.famivac.gestionnaire.sejours.control;

import java.util.Date;

/**
 * @author paoesco
 */
public class VoyageDTO {

    private Long sejourId;

    private String enfant;

    private Date date;

    public Long getSejourId() {
        return sejourId;
    }

    public void setSejourId(Long sejourId) {
        this.sejourId = sejourId;
    }

    public String getEnfant() {
        return enfant;
    }

    public void setEnfant(String enfant) {
        this.enfant = enfant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
