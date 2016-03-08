package fr.famivac.interfaces.web.parametres.utilisateurs;

import fr.famivac.gestionnaire.commons.entity.utils.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author paoesco
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DetailsUtilisateurForm {

    @NotNull
    @XmlElement(required = true)
    private String login;

    @Size(max = 255)
    @XmlElement
    private String password;

    @Size(max = 255)
    @XmlTransient
    private String confirmPassword;

    @NotNull
    @Size(min = 1, max = 255)
    @XmlElement(required = true)
    private String nom;

    @NotNull
    @Size(min = 1, max = 255)
    @XmlElement(required = true)
    private String prenom;

    @NotNull
    @Size(min = 1, max = 255)
    @XmlElement(required = true)
    private String groupe;

    @NotNull
    @Email
    @Size(min = 1, max = 2000)
    @XmlElement(required = true)
    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
