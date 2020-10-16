package fr.famivac.gestionnaire.interfaces.facade.utilisateurs;

import fr.famivac.gestionnaire.administration.authentication.control.AjouterUtilisateurDTO;
import fr.famivac.gestionnaire.administration.authentication.control.UtilisateurService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author paoesco
 */
@ApplicationScoped
public class UtilisateurServiceFacade {

    @Inject
    private UtilisateurService utilisateurService;


    public String create(AjouterUtilisateurDTO dto) {
        final String password = utilisateurService.create(dto);
        return password;
    }

    public String reinitPassword(String login, String email) {
        String password = utilisateurService.reinitPassword(login);
        return password;
    }

    public void lock(String login, String email) {
        utilisateurService.lock(login);
    }

    public void unlock(String login, String email) {
        utilisateurService.unlock(login);
    }

}
