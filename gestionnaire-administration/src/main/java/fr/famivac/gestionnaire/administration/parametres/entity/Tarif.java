package fr.famivac.gestionnaire.administration.parametres.entity;

import fr.famivac.gestionnaire.commons.utils.DateUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author paoesco
 */
@Entity
@Table(name = "TARIF")
@NamedQueries({
    @NamedQuery(name = Tarif.QUERY_LISTE_ALL, query = "select t from Tarif t")
})
public class Tarif {

    public static final String QUERY_LISTE_ALL = "getTarifs";

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "MONTANT", precision = 10, scale = 2, nullable = false)
    private BigDecimal montant;

    @Column(name = "DATE_DEBUT_VALIDITE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDebutValidite;

    @Column(name = "DATE_FIN_VALIDITE")
    @Temporal(TemporalType.DATE)
    private Date dateFinValidite;

    public Tarif() {
        montant = new BigDecimal(BigInteger.ZERO);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Date getDateDebutValidite() {
        return dateDebutValidite;
    }

    public void setDateDebutValidite(Date dateDebutValidite) {
        this.dateDebutValidite = dateDebutValidite;
    }

    public Date getDateFinValidite() {
        return dateFinValidite;
    }

    public void setDateFinValidite(Date dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    public boolean isEnCours(Date date) {
        return Objects.isNull(dateFinValidite) || DateUtils.after(dateFinValidite, date);
    }

}
