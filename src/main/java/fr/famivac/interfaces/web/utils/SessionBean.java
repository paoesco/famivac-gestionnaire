package fr.famivac.interfaces.web.utils;

import fr.famivac.gestionnaire.administration.control.UtilisateurService;
import fr.famivac.gestionnaire.administration.entity.Utilisateur;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author paoesco
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {

    @Inject
    private UtilisateurService utilisateurService;

    private Utilisateur connectedUser;

    @PostConstruct
    public void init() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utilisateur entity = utilisateurService.retrieve(user.getUsername());
        if (entity == null) {
            connectedUser = Utilisateur.fakeUtilisateur;
            connectedUser.setNom("(system)");
            connectedUser.setPrenom(user.getUsername());
            connectedUser.setEnabled(false);
        } else {
            connectedUser = entity;
        }

    }
    
    public Utilisateur getConnectedUser() {
        return connectedUser;
    }

}
