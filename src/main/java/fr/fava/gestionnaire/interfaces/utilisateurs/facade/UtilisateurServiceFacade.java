package fr.fava.gestionnaire.interfaces.utilisateurs.facade;

import fr.fava.gestionnaire.application.UtilisateurService;
import fr.fava.gestionnaire.application.AjouterUtilisateurDTO;
import fr.fava.gestionnaire.application.mail.Mail;
import fr.fava.gestionnaire.application.mail.MailService;
import java.text.MessageFormat;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author paoesco
 */
@ApplicationScoped
public class UtilisateurServiceFacade {

    @Inject
    private UtilisateurService utilisateurService;

    @Inject
    private MailService mailService;

    public String create(AjouterUtilisateurDTO dto) {
        final String password = utilisateurService.create(dto);
        Mail mail = new Mail(dto.getEmail(), "[FAVA] Création d'un compte", MessageFormat.format("Un administrateur vous a créé un compte sur l'interface gestionnaire FAVA. Login : {0} / Mot de passe : {1}", dto.getLogin(), password));
        // TODO : envoyer email
        // mailService.envoyerMail(mail, "no-reply@fava.fr", "");
        return password;
    }

    public String reinitPassword(String login, String email) {
        String password = utilisateurService.reinitPassword(login);
        Mail mail = new Mail(email, "[FAVA] Réinitialisation mot de passe", MessageFormat.format("Un administrateur a réinitialisé votre mot de passe. Nouveau mot de passe : {1}", password));
        // TODO : envoyer email
        // mailService.envoyerMail(mail, "no-reply@fava.fr", "");
        return password;
    }

}
