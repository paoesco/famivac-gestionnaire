package fr.famivac.gestionnaire.domains.familles.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Paolo
 */
@Entity
public class Chambre implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Integer nombreLits;

    @ManyToOne
    private Famille famille;
    
    protected Chambre(){
        nombreLits = 0;
    }

    public Chambre(Integer nombreLits, Famille famille) {
        if (famille == null) {
            throw new IllegalArgumentException("Préciser la famille");
        }
        this.nombreLits = nombreLits;
        this.famille = famille;
    }

    public Integer getNombreLits() {
        return nombreLits;
    }

    public void setNombreLits(Integer nombreLits) {
        this.nombreLits = nombreLits;
    }

    public Famille getFamille() {
        return famille;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Chambre other = (Chambre) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
