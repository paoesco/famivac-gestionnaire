package fr.famivac.gestionnaire.email.control;

/**
 * @author paoesco
 */
public class Mail {

    private final String recipient;

    private final String from;

    private final String subject;

    private final String body;

//    private final String nomPieceJointe;

//    private final InputStream pieceJointe;

//    private final String mimeTypePieceJointe;

    public Mail(String destinataire, String sujet, String message) {
        this.recipient = destinataire;
        this.from = "no-reply@famivac.fr";
        this.subject = sujet;
        this.body = message;
//        this.nomPieceJointe = null;
//        this.pieceJointe = null;
//        this.mimeTypePieceJointe = null;
    }

//    public Mail(String destinataire, String sujet, String message, String nomPieceJointe, InputStream pieceJointe, String mimeTypePieceJointe) {
//        this.recipient = destinataire;
//        this.from = "no-reply@famivac.fr";
//        this.subject = sujet;
//        this.body = message;
//        this.nomPieceJointe = nomPieceJointe;
//        this.pieceJointe = pieceJointe;
//        this.mimeTypePieceJointe = mimeTypePieceJointe;
//    }

    public String getRecipient() {
        return recipient;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

//    public String getNomPieceJointe() {
//        return nomPieceJointe;
//    }
//
//    public InputStream getPieceJointe() {
//        return pieceJointe;
//    }
//
//    public String getMimeTypePieceJointe() {
//        return mimeTypePieceJointe;
//    }

}
