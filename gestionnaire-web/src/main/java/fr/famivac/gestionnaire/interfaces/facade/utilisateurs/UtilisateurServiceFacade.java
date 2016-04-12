package fr.famivac.gestionnaire.interfaces.facade.utilisateurs;

import fr.famivac.gestionnaire.administration.control.AjouterUtilisateurDTO;
import fr.famivac.gestionnaire.administration.control.UtilisateurService;
import fr.famivac.gestionnaire.email.control.Mail;
import fr.famivac.gestionnaire.email.control.MailException;
import fr.famivac.gestionnaire.email.control.MailService;
import java.text.MessageFormat;
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

    @Inject
    private MailService mailService;

    public String create(AjouterUtilisateurDTO dto) {
        final String password = utilisateurService.create(dto);
        Mail mail = new Mail(
                dto.getEmail(),
                "[FAMIVAC] Création d'un compte",
                MessageFormat.format("Un administrateur vous a créé un compte sur l''interface https://gestionnaire.famivac.fr\n Login : {0} \n Mot de passe généré : {1} \n Veuillez le changer à la première connexion.", dto.getLogin(), password));
        mailService.send(mail);
        return password;
    }

    public String reinitPassword(String login, String email) {
        String password = utilisateurService.reinitPassword(login);
        Mail mail = new Mail(
                email,
                "[FAMIVAC] Réinitialisation mot de passe",
                MessageFormat.format("Un administrateur a réinitialisé votre mot de passe sur le site https://gestionnaire.famivac.fr\n Nouveau mot de passe : {0}", password));
        mailService.send(mail);
        return password;
    }

    public void lock(String login, String email) {
        utilisateurService.lock(login);
        Mail mail = new Mail(
                email,
                "[FAMIVAC] Compte verrouillé",
                "Un administrateur a verrouillé votre compte sur le site https://gestionnaire.famivac.fr\n Veuillez envoyer un e-mail à contact@famivac.fr pour plus d'informations.");
        mailService.send(mail);
    }

    public void unlock(String login, String email) {
        utilisateurService.unlock(login);
        Mail mail = new Mail(
                email,
                "[FAMIVAC] Compte accessible",
                "Un administrateur a rendu accessible votre compte sur le site https://gestionnaire.famivac.fr\n Vous pouvez à nouveau vous connecter.");
        mailService.send(mail);
    }

}
