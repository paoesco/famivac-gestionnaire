package fr.fava.gestionnaire.application;

import fr.fava.gestionnaire.domain.common.Groupe;
import fr.fava.gestionnaire.domain.utils.Email;
import java.util.List;

/**
 * @author paoesco
 */
public class AjouterUtilisateurDTO {

    private String login;

    @Email
    private String email;

    private String nom;

    private String prenom;

    private List<Groupe> groupes;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<Groupe> groupes) {
        this.groupes = groupes;
    }

}
