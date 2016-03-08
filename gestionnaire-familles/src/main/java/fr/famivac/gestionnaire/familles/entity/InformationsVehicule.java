package fr.famivac.gestionnaire.familles.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author paoesco
 */
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
    private int nombreVehicules;

    @Column(name = "TYPE_VEHICULE_1")
    private String typeVehicule1;

    @Column(name = "NOMBRE_PLACES_VEHICULE_1")
    private int nombrePlacesVehicule1;

    @Column(name = "CONDUCTEUR_PRINCIPAL_VEHICULE_1")
    private String conducteurPrincipalVehicule1;

    @Column(name = "TYPE_VEHICULE_2")
    private String typeVehicule2;

    @Column(name = "NOMBRE_PLACES_VEHICULE_2")
    private int nombrePlacesVehicule2;

    @Column(name = "CONDUCTEUR_PRINCIPAL_VEHICULE_2")
    private String conducteurPrincipalVehicule2;

    @Column(name = "ASSURANCE")
    private String assurance;

    @Column(name = "NOMBRE_REHAUSSEURS")
    private int nombreRehausseurs;

    @Column(name = "NOMBRE_SIEGES_ATUO_BEBE")
    private int nombreSiegesAutoBebe;

    /**
     * Constructeur JPA.
     */
    protected InformationsVehicule() {
        
    }

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

    public int getNombreVehicules() {
        return nombreVehicules;
    }

    public void setNombreVehicules(int nombreVehicules) {
        this.nombreVehicules = nombreVehicules;
    }

    public String getTypeVehicule1() {
        return typeVehicule1;
    }

    public void setTypeVehicule1(String typeVehicule1) {
        this.typeVehicule1 = typeVehicule1;
    }

    public int getNombrePlacesVehicule1() {
        return nombrePlacesVehicule1;
    }

    public void setNombrePlacesVehicule1(int nombrePlacesVehicule1) {
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

    public int getNombrePlacesVehicule2() {
        return nombrePlacesVehicule2;
    }

    public void setNombrePlacesVehicule2(int nombrePlacesVehicule2) {
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

    public int getNombreRehausseurs() {
        return nombreRehausseurs;
    }

    public void setNombreRehausseurs(int nombreRehausseurs) {
        this.nombreRehausseurs = nombreRehausseurs;
    }

    public int getNombreSiegesAutoBebe() {
        return nombreSiegesAutoBebe;
    }

    public void setNombreSiegesAutoBebe(int nombreSiegesAutoBebe) {
        this.nombreSiegesAutoBebe = nombreSiegesAutoBebe;
    }

}
