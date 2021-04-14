package fr.famivac.gestionnaire.domains.sejours.entity;

import fr.famivac.gestionnaire.commons.entity.Entity;
import fr.famivac.gestionnaire.commons.utils.Email;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/** @author paoesco */
@jakarta.persistence.Entity
@Table(name = "ACCOMPAGNATEUR")
@NamedQueries({
  @NamedQuery(
      name = Accompagnateur.QUERY_GET_ALL,
      query =
          "select acc from Accompagnateur acc where acc.deletedAt is null order by acc.nom, acc.prenom"),
  @NamedQuery(
      name = Accompagnateur.QUERY_RECHERCHER,
      query =
          "select acc from Accompagnateur acc where acc.deletedAt is null and lower(acc.nom) like :nom or lower(acc.prenom) like :prenom order by acc.nom, acc.prenom")
})
public class Accompagnateur extends Entity implements Serializable {

  public static final String QUERY_GET_ALL = "queryAccompagnateurGetAll";
  public static final String QUERY_RECHERCHER = "queryAccompagnateurRechercher";

  @Column(name = "NOM")
  private String nom;

  @Column(name = "PRENOM")
  private String prenom;

  @Column(name = "TELEPHONE")
  private String telephone;

  @Column(name = "EMAIL")
  @Email
  private String email;

  @Column(name = "deleted_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date deletedAt;

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

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Date deletedAt) {
    this.deletedAt = deletedAt;
  }
}
