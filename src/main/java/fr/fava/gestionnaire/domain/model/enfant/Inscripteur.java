package fr.fava.gestionnaire.domain.model.enfant;

import fr.fava.gestionnaire.domain.model.Adresse;
import fr.fava.gestionnaire.domain.utils.Email;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author paoesco
 */
@Entity
@NamedQuery(name = Inscripteur.QUERY_RETRIEVE_ALL, query = "select i from Inscripteur i order by i.nom,i.prenom,i.organisme")
public class Inscripteur implements Serializable {

    public static final String QUERY_RETRIEVE_ALL = "insctipteurRetrieveAll";

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "inscripteur")
    private final Set<Enfant> enfants;

    @Enumerated(EnumType.STRING)
    private TypeInscripteur type;

    private String nom;

    private String prenom;

    private String organisme;

    @Embedded
    private Adresse adresse;

    private String personneReferente;

    private String telephone;

    private String fax;

    @Email
    private String email;

    private boolean responsableLegal;

    public Inscripteur() {
        enfants = new HashSet<>();
        adresse = new Adresse();
        type = TypeInscripteur.SERVICE_SOCIAL;
    }

    public Long getId() {
        return id;
    }

    public Set<Enfant> getEnfants() {
        return Collections.unmodifiableSet(enfants);
    }

    public void ajouterEnfant(Enfant enfant) {
        enfants.add(enfant);
    }

    public void retirerEnfant(Enfant enfant) {
        enfants.remove(enfant);
    }

    public TypeInscripteur getType() {
        return type;
    }

    public void setType(TypeInscripteur type) {
        this.type = type;
    }

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

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getPersonneReferente() {
        return personneReferente;
    }

    public void setPersonneReferente(String personneReferente) {
        this.personneReferente = personneReferente;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isResponsableLegal() {
        return responsableLegal;
    }

    public void setResponsableLegal(boolean responsableLegal) {
        this.responsableLegal = responsableLegal;
    }

    public boolean isParticulier() {
        return TypeInscripteur.PARTICULIER.equals(type);
    }

    public boolean isTypeServiceSocialOuAutre() {
        return TypeInscripteur.SERVICE_SOCIAL.equals(type) || TypeInscripteur.AUTRE.equals(type);
    }

}
