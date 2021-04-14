package fr.famivac.gestionnaire.web.sejours;

import fr.famivac.gestionnaire.domains.enfants.control.EnfantDTO;
import fr.famivac.gestionnaire.domains.familles.boundary.FamilleDTO;
import fr.famivac.gestionnaire.domains.sejours.entity.PeriodeJournee;
import java.util.Date;
import jakarta.validation.constraints.NotNull;

/** @author paoesco */
public class AjouterSejourForm {

  @NotNull private FamilleDTO famille;

  private EnfantDTO enfant;

  @NotNull private Date dateDebut;

  @NotNull private String periodeJourneeDebut;

  @NotNull private Date dateFin;

  @NotNull private String periodeJourneeFin;

  public AjouterSejourForm() {
    periodeJourneeFin = PeriodeJournee.APRES_MIDI.name();
  }

  public FamilleDTO getFamille() {
    return famille;
  }

  public void setFamille(FamilleDTO famille) {
    this.famille = famille;
  }

  public EnfantDTO getEnfant() {
    return enfant;
  }

  public void setEnfant(EnfantDTO enfant) {
    this.enfant = enfant;
  }

  public Date getDateDebut() {
    return dateDebut;
  }

  public void setDateDebut(Date dateDebut) {
    this.dateDebut = dateDebut;
  }

  public String getPeriodeJourneeDebut() {
    return periodeJourneeDebut;
  }

  public void setPeriodeJourneeDebut(String periodeJourneeDebut) {
    this.periodeJourneeDebut = periodeJourneeDebut;
  }

  public Date getDateFin() {
    return dateFin;
  }

  public void setDateFin(Date dateFin) {
    this.dateFin = dateFin;
  }

  public String getPeriodeJourneeFin() {
    return periodeJourneeFin;
  }

  public void setPeriodeJourneeFin(String periodeJourneeFin) {
    this.periodeJourneeFin = periodeJourneeFin;
  }
}
