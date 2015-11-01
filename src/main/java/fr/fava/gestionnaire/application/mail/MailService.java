package fr.fava.gestionnaire.application.mail;

import fr.fava.gestionnaire.application.mail.exceptions.MailException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.util.ByteArrayDataSource;

/**
 * @author paoesco
 */
@ApplicationScoped
public class MailService {

//    @Resource(name = "mail/session")
//    private Session mailSession;

    public void envoyerMail(Mail mail, String username, String password) throws MailException {
//        // Recipient's email ID needs to be mentioned.
//        String to = mail.getDestinataire();
//
//        // Sender's email ID needs to be mentioned
//        String from = mail.getSource();
//
//        try (InputStream attachmentAutoClose = mail.getPieceJointe()) {
//            Message message = new MimeMessage(mailSession);
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//            // Set To: header field of the header.
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            // Set Subject: header field
//            message.setSubject(mail.getSujet());
//
//            if (attachmentAutoClose == null) {
//                message.setText(mail.getMessage());
//            } else {
//                Multipart multipart = new MimeMultipart();
//                // creates body part for the message
//                MimeBodyPart messageBodyPart = new MimeBodyPart();
//                messageBodyPart.setContent(message, "text/html");
//                messageBodyPart.setText(mail.getMessage());
//                multipart.addBodyPart(messageBodyPart);
//
//                // creates body part for the attachment
//                MimeBodyPart attachPart = new MimeBodyPart();
//                DataSource source = new ByteArrayDataSource(attachmentAutoClose, mail.getMimeTypePieceJointe());
//                attachPart.setDataHandler(new DataHandler(source));
//                attachPart.setFileName(mail.getNomPieceJointe());
//                multipart.addBodyPart(attachPart);
//
//                message.setContent(multipart);
//            }
//
//            // Send message
//            Transport.send(message, username, password);
//        } catch (MessagingException | IOException ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Impossible d'envoyer un email à " + mail.getDestinataire(), ex);
//            throw new MailException(ex);
//        }
    }

}
