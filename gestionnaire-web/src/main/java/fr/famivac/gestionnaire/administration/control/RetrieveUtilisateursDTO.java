package fr.famivac.gestionnaire.administration.control;

import fr.famivac.gestionnaire.administration.entity.Groupe;
import java.util.ArrayList;
import java.util.List;

/**
 * @author paoesco
 */
public class RetrieveUtilisateursDTO {

    private String nom;

    private String prenom;

    private String login;

    private String groupesLabel;

    private String email;

    private boolean enabled;

    private List<Groupe> groupes;

    public RetrieveUtilisateursDTO() {
        groupes = new ArrayList<>();
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGroupesLabel() {
        if (!getGroupes().isEmpty()) {
            groupesLabel = getGroupes().stream().map((Groupe g) -> {
                return g.getLibelle();
            }).reduce((g1, g2) -> {
                return g1 + "," + g2;
            }).get();
        }
        return groupesLabel;
    }

    public void setGroupesLabel(String groupesLabel) {
        this.groupesLabel = groupesLabel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<Groupe> groupes) {
        this.groupes = groupes;
    }

}
