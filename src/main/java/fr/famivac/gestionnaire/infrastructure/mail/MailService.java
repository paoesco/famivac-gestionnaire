package fr.famivac.gestionnaire.infrastructure.mail;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author paoesco
 */
@Stateless
public class MailService {

    @Resource(name = "java:jboss/mail/famivac")
    private Session mailSession;

    @Asynchronous
    public void send(Mail mail) {
        try {
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(mail.getFrom()));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getRecipient()));
            msg.setSubject(mail.getSubject());
            msg.setContent(mail.getBody(), "text/html; charset=utf-8");
            Transport.send(msg);
        } catch (MessagingException ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.WARNING, null, ex);
        }

    }

}
