package fr.famivac.gestionnaire.domains.familles.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/** @author paoesco */
@Entity
@Table(name = "INFORMATIONS_VEHICULE")
public class InformationsVehicule implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne
  @JoinColumn(name = "FAMILLE_ID", nullable = false)
  private Famille famille;

  @Column(name = "NOMBRE_VEHICULES")
  private Integer nombreVehicules;

  @Column(name = "TYPE_VEHICULE_1")
  private String typeVehicule1;

  @Column(name = "NOMBRE_PLACES_VEHICULE_1")
  private Integer nombrePlacesVehicule1;

  @Column(name = "CONDUCTEUR_PRINCIPAL_VEHICULE_1")
  private String conducteurPrincipalVehicule1;

  @Column(name = "TYPE_VEHICULE_2")
  private String typeVehicule2;

  @Column(name = "NOMBRE_PLACES_VEHICULE_2")
  private Integer nombrePlacesVehicule2;

  @Column(name = "CONDUCTEUR_PRINCIPAL_VEHICULE_2")
  private String conducteurPrincipalVehicule2;

  @Column(name = "ASSURANCE")
  private String assurance;

  @Column(name = "NOMBRE_REHAUSSEURS")
  private Integer nombreRehausseurs;

  @Column(name = "NOMBRE_SIEGES_ATUO_BEBE")
  private Integer nombreSiegesAutoBebe;

  /** Constructeur JPA. */
  protected InformationsVehicule() {}

  public InformationsVehicule(Famille famille) {
    this.famille = famille;
  }

  public Long getId() {
    return id;
  }

  public Famille getFamille() {
    return famille;
  }

  public void setFamille(Famille famille) {
    this.famille = famille;
  }

  public Integer getNombreVehicules() {
    return nombreVehicules;
  }

  public void setNombreVehicules(Integer nombreVehicules) {
    this.nombreVehicules = nombreVehicules;
  }

  public String getTypeVehicule1() {
    return typeVehicule1;
  }

  public void setTypeVehicule1(String typeVehicule1) {
    this.typeVehicule1 = typeVehicule1;
  }

  public Integer getNombrePlacesVehicule1() {
    return nombrePlacesVehicule1;
  }

  public void setNombrePlacesVehicule1(Integer nombrePlacesVehicule1) {
    this.nombrePlacesVehicule1 = nombrePlacesVehicule1;
  }

  public String getConducteurPrincipalVehicule1() {
    return conducteurPrincipalVehicule1;
  }

  public void setConducteurPrincipalVehicule1(String conducteurPrincipalVehicule1) {
    this.conducteurPrincipalVehicule1 = conducteurPrincipalVehicule1;
  }

  public String getTypeVehicule2() {
    return typeVehicule2;
  }

  public void setTypeVehicule2(String typeVehicule2) {
    this.typeVehicule2 = typeVehicule2;
  }

  public Integer getNombrePlacesVehicule2() {
    return nombrePlacesVehicule2;
  }

  public void setNombrePlacesVehicule2(Integer nombrePlacesVehicule2) {
    this.nombrePlacesVehicule2 = nombrePlacesVehicule2;
  }

  public String getConducteurPrincipalVehicule2() {
    return conducteurPrincipalVehicule2;
  }

  public void setConducteurPrincipalVehicule2(String conducteurPrincipalVehicule2) {
    this.conducteurPrincipalVehicule2 = conducteurPrincipalVehicule2;
  }

  public String getAssurance() {
    return assurance;
  }

  public void setAssurance(String assurance) {
    this.assurance = assurance;
  }

  public Integer getNombreRehausseurs() {
    return nombreRehausseurs;
  }

  public void setNombreRehausseurs(Integer nombreRehausseurs) {
    this.nombreRehausseurs = nombreRehausseurs;
  }

  public Integer getNombreSiegesAutoBebe() {
    return nombreSiegesAutoBebe;
  }

  public void setNombreSiegesAutoBebe(Integer nombreSiegesAutoBebe) {
    this.nombreSiegesAutoBebe = nombreSiegesAutoBebe;
  }
}
