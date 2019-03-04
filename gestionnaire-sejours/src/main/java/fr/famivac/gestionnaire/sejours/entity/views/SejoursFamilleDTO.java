package fr.famivac.gestionnaire.sejours.entity.views;

import java.util.Date;

/**
 *
 * @author paoesco
 */
public class SejoursFamilleDTO {

    private Long sejourId;

    private Long familleId;

    private String familleFirstname;

    private String familleLastname;

    private Long enfantId;

    private String enfantFirstname;

    private String enfantLastname;

    private Date dateDebut;

    private Date dateFin;

    protected SejoursFamilleDTO() {
        // JPA
    }

    public SejoursFamilleDTO(Long sejourId, Long familleId, String familleFirstname, String familleLastname, Long enfantId, String enfantFirstname, String enfantLastname, Date dateDebut, Date dateFin) {
        this.sejourId = sejourId;
        this.familleId = familleId;
        this.familleFirstname = familleFirstname;
        this.familleLastname = familleLastname;
        this.enfantId = enfantId;
        this.enfantFirstname = enfantFirstname;
        this.enfantLastname = enfantLastname;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Long getSejourId() {
        return sejourId;
    }

    public void setSejourId(Long sejourId) {
        this.sejourId = sejourId;
    }

    public Long getFamilleId() {
        return familleId;
    }

    public void setFamilleId(Long familleId) {
        this.familleId = familleId;
    }

    public String getFamilleFirstname() {
        return familleFirstname;
    }

    public void setFamilleFirstname(String familleFirstname) {
        this.familleFirstname = familleFirstname;
    }

    public String getFamilleLastname() {
        return familleLastname;
    }

    public void setFamilleLastname(String familleLastname) {
        this.familleLastname = familleLastname;
    }

    public Long getEnfantId() {
        return enfantId;
    }

    public void setEnfantId(Long enfantId) {
        this.enfantId = enfantId;
    }

    public String getEnfantFirstname() {
        return enfantFirstname;
    }

    public void setEnfantFirstname(String enfantFirstname) {
        this.enfantFirstname = enfantFirstname;
    }

    public String getEnfantLastname() {
        return enfantLastname;
    }

    public void setEnfantLastname(String enfantLastname) {
        this.enfantLastname = enfantLastname;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

}
