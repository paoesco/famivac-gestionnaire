package fr.famivac.gestionnaire.email.control;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author paoesco
 */
@ApplicationScoped
public class MailService {

    @Resource(name = "java:jboss/mail/famivac")
    private Session mailSession;

    public void send(Mail mail) throws MailException {
        try {
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(mail.getFrom()));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getRecipient()));
            msg.setSubject(mail.getSubject());
            msg.setText(mail.getBody());
            Transport.send(msg);
        } catch (MessagingException ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
            throw new MailException(ex);
        }

    }

}
