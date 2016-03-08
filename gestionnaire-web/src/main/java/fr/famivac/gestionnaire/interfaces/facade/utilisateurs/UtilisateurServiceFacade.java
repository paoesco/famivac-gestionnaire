package fr.famivac.gestionnaire.interfaces.facade.utilisateurs;

import fr.famivac.gestionnaire.administration.control.AjouterUtilisateurDTO;
import fr.famivac.gestionnaire.administration.control.UtilisateurService;
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

//    @Inject
//    private MailService mailService;

    public String create(AjouterUtilisateurDTO dto) {
        final String password = utilisateurService.create(dto);
//        Mail mail = new Mail(dto.getEmail(), "[FAVA] Création d'un compte", MessageFormat.format("Un administrateur vous a créé un compte sur l'interface gestionnaire FAVA. Login : {0} / Mot de passe : {1}", dto.getLogin(), password));
        // TODO : envoyer email
        // mailService.envoyerMail(mail, "no-reply@fava.fr", "");
        return password;
    }

    public String reinitPassword(String login, String email) {
        String password = utilisateurService.reinitPassword(login);
//        Mail mail = new Mail(email, "[FAVA] Réinitialisation mot de passe", MessageFormat.format("Un administrateur a réinitialisé votre mot de passe. Nouveau mot de passe : {1}", password));
        // TODO : envoyer email
        // mailService.envoyerMail(mail, "no-reply@fava.fr", "");
        return password;
    }

}
