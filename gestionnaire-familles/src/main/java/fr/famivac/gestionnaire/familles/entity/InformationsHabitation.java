package fr.famivac.gestionnaire.familles.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author paoesco
 */
@Entity
@Table(name = "INFORMATIONS_HABITATION")
public class InformationsHabitation implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "FAMILLE_ID", nullable = false)
    private Famille famille;

    @Column(name = "TYPE_HABITATION")
    @Enumerated(EnumType.STRING)
    private TypeHabitation typeHabitation;

    @Column(name = "SITUATION_HABITATION")
    @Enumerated(EnumType.STRING)
    private SituationHabitation situationHabitation;

    @Column(name = "VILLE_LA_PLUS_PROCHE")
    private String villeLaPlusProche;

    @Column(name = "JARDIN_ESCPACE_JEU")
    private Boolean jardinEspaceJeu;

    @Column(name = "PISCINE")
    private Boolean piscine;

    @Column(name = "PISCINE_SECURISE")
    private Boolean piscineSecurisee;

    @Column(name = "NOMBRE_CHIENS")
    private Integer nombreChiens;

    @Column(name = "NOMBRE_CHATS")
    private Integer nombreChats;

    @Column(name = "AUTRES_ANIMAUX", length = 1000)
    private String autresAnimaux;

    @Column(name = "POINT_VIGILANCE", length = 1000)
    private String pointVigilance;

    /**
     * Constructeur JPA
     */
    protected InformationsHabitation() {
    }

    public InformationsHabitation(Famille famille) {
        if (famille == null) {
            throw new IllegalArgumentException("La famille est obligatoire");
        }
        this.jardinEspaceJeu = false;
        this.piscine = false;
        this.nombreChats = 0;
        this.nombreChiens = 0;
        this.famille = famille;
    }

    public Long getId() {
        return id;
    }

    public Famille getFamille() {
        return famille;
    }

    public TypeHabitation getTypeHabitation() {
        return typeHabitation;
    }

    public void setTypeHabitation(TypeHabitation typeHabitation) {
        this.typeHabitation = typeHabitation;
    }

    public SituationHabitation getSituationHabitation() {
        return situationHabitation;
    }

    public void setSituationHabitation(SituationHabitation situationHabitation) {
        this.situationHabitation = situationHabitation;
    }

    public String getVilleLaPlusProche() {
        return villeLaPlusProche;
    }

    public void setVilleLaPlusProche(String villeLaPlusProche) {
        this.villeLaPlusProche = villeLaPlusProche;
    }

    public Boolean getJardinEspaceJeu() {
        return jardinEspaceJeu;
    }

    public void setJardinEspaceJeu(Boolean jardinEspaceJeu) {
        this.jardinEspaceJeu = jardinEspaceJeu;
    }

    public Boolean getPiscine() {
        return piscine;
    }

    public void setPiscine(Boolean piscine) {
        this.piscine = piscine;
    }

    public Boolean getPiscineSecurisee() {
        return piscineSecurisee;
    }

    public void setPiscineSecurisee(Boolean piscineSecurisee) {
        this.piscineSecurisee = piscineSecurisee;
    }

    public Integer getNombreChiens() {
        return nombreChiens;
    }

    public void setNombreChiens(Integer nombreChiens) {
        this.nombreChiens = nombreChiens;
    }

    public Integer getNombreChats() {
        return nombreChats;
    }

    public void setNombreChats(Integer nombreChats) {
        this.nombreChats = nombreChats;
    }

    public String getAutresAnimaux() {
        return autresAnimaux;
    }

    public void setAutresAnimaux(String autresAnimaux) {
        this.autresAnimaux = autresAnimaux;
    }

    public String getPointVigilance() {
        return pointVigilance;
    }

    public void setPointVigilance(String pointVigilance) {
        this.pointVigilance = pointVigilance;
    }

}
