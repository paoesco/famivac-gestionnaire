package fr.fava.gestionnaire.domain.sejour;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author paoesco
 */
@Entity
public class Voyage implements Serializable {

    @Id
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dateVoyage;
    @Temporal(TemporalType.TIME)
    private Date heureDepart;
    @Enumerated(EnumType.STRING)
    private Transport transport;
    private String lieuRendezVous;
    private String lieuArrivee;
    @Temporal(TemporalType.TIME)
    private Date heureArrivee;
    private String nomPersonneAReception;
    private String telephonePersonneAReception;

    
    public Long getId() {
        return id;
    }

    public Date getDateVoyage() {
        return Objects.isNull(dateVoyage) ? null : (Date) dateVoyage.clone();
    }

    public void setDateVoyage(Date dateVoyage) {
        this.dateVoyage = Objects.isNull(dateVoyage) ? null : (Date) dateVoyage.clone();
    }

    public Date getHeureDepart() {
        return Objects.isNull(heureDepart) ? null : (Date) heureDepart.clone();
    }

    public void setHeureDepart(Date heureDepart) {
        this.heureDepart = Objects.isNull(heureDepart) ? null : (Date) heureDepart.clone();
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public String getLieuRendezVous() {
        return lieuRendezVous;
    }

    public void setLieuRendezVous(String lieuRendezVous) {
        this.lieuRendezVous = lieuRendezVous;
    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public Date getHeureArrivee() {
        return Objects.isNull(heureArrivee) ? null : (Date) heureArrivee.clone();
    }

    public void setHeureArrivee(Date heureArrivee) {
        this.heureArrivee = Objects.isNull(heureArrivee) ? null : (Date) heureArrivee.clone();
    }

    public String getNomPersonneAReception() {
        return nomPersonneAReception;
    }

    public void setNomPersonneAReception(String nomPersonneAReception) {
        this.nomPersonneAReception = nomPersonneAReception;
    }

    public String getTelephonePersonneAReception() {
        return telephonePersonneAReception;
    }

    public void setTelephonePersonneAReception(String telephonePersonneAReception) {
        this.telephonePersonneAReception = telephonePersonneAReception;
    }

}
