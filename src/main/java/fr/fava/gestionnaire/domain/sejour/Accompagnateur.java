package fr.fava.gestionnaire.domain.sejour;

import fr.fava.gestionnaire.domain.Entity;
import fr.fava.gestionnaire.domain.utils.Email;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author paoesco
 */
@javax.persistence.Entity
@Table(name = "ACCOMPAGNATEUR")
@NamedQuery(name = Accompagnateur.QUERY_GET_ALL, query = "select acc from Accompagnateur acc order by acc.nom, acc.prenom")
public class Accompagnateur extends Entity implements Serializable {

    public static final String QUERY_GET_ALL = "queryAccompagnateurGetAll";

    @Column(name = "NOM")
    private String nom;
    @Column(name = "PRENOM")
    private String prenom;
    @Column(name = "TELEPHONE")
    private String telephone;
    @Column(name = "EMAIL")
    @Email
    private String email;

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

}
