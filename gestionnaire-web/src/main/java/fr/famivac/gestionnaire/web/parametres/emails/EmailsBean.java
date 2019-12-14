package fr.famivac.gestionnaire.web.parametres.emails;

import fr.famivac.gestionnaire.email.control.Mail;
import fr.famivac.gestionnaire.email.control.MailService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class EmailsBean implements Serializable {

    @Inject
    private MailService mailService;

    @Inject
    private EmailForm emailForm;

    public void send() {
        Mail mail = new Mail(emailForm.getRecipient(), emailForm.getSubject(), emailForm.getBody());
        mailService.send(mail);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail envoyé !", ""));
        emailForm.init();
    }

}
