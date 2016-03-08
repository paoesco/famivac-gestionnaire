package fr.famivac.gestionnaire.email.control;

import java.io.InputStream;

/**
 * @author paoesco
 */
public class Mail {

    private final String destinataire;

    private final String source;

    private final String sujet;

    private final String message;

    private final String nomPieceJointe;

    private final InputStream pieceJointe;

    private final String mimeTypePieceJointe;

    public Mail(String destinataire, String sujet, String message) {
        this.destinataire = destinataire;
        this.source = "no-reply@vacancesespritfamille.fr";
        this.sujet = sujet;
        this.message = message;
        this.nomPieceJointe = null;
        this.pieceJointe = null;
        this.mimeTypePieceJointe = null;
    }

    public Mail(String destinataire, String sujet, String message, String nomPieceJointe, InputStream pieceJointe, String mimeTypePieceJointe) {
        this.destinataire = destinataire;
        this.source = "no-reply@vacancesespritfamille.fr";
        this.sujet = sujet;
        this.message = message;
        this.nomPieceJointe = nomPieceJointe;
        this.pieceJointe = pieceJointe;
        this.mimeTypePieceJointe = mimeTypePieceJointe;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public String getSource() {
        return source;
    }

    public String getSujet() {
        return sujet;
    }

    public String getMessage() {
        return message;
    }

    public String getNomPieceJointe() {
        return nomPieceJointe;
    }

    public InputStream getPieceJointe() {
        return pieceJointe;
    }

    public String getMimeTypePieceJointe() {
        return mimeTypePieceJointe;
    }

}
