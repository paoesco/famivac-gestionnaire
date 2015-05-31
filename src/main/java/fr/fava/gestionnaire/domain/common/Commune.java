package fr.fava.gestionnaire.domain.common;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author paoesco
 */
@Entity
@NamedQuery(name = Commune.QUERY_RETRIEVE_ALL, query = "select c from Commune c order by c.ville")
public class Commune implements Serializable {

    public static final String QUERY_RETRIEVE_ALL = "communeRetrieveAll";

    @Id
    private String code;

    @Column(nullable = false)
    private String ville;

    public Commune() {

    }

    public Commune(String code, String ville) {
        if (code == null || code.isEmpty() || ville == null || ville.isEmpty()) {
            throw new IllegalArgumentException("Tous les paramètres sont obligatoires");
        }
        this.code = code;
        this.ville = ville;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getLabel() {
        if (code == null) {
            return "";
        }
        return ville + " (" + code + ")";
    }

}
