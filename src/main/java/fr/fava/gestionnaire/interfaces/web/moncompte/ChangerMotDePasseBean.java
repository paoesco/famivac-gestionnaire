package fr.fava.gestionnaire.interfaces.web.moncompte;

import fr.fava.gestionnaire.application.UtilisateurService;
import fr.fava.gestionnaire.application.exceptions.WrongPasswordException;
import fr.fava.gestionnaire.interfaces.web.utils.SessionBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author paoesco
 */
@Named
@RequestScoped
public class ChangerMotDePasseBean {

    private String actualPassword;

    private String newPassword;

    private String confirmNewPassword;
    
    @Inject
    private SessionBean sessionBean;

    @Inject
    private UtilisateurService utilisateurService;

    public void changer() {
        if (!newPassword.equals(confirmNewPassword)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Les mots de passe ne correspond pas", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        try {
            utilisateurService.changePassword(sessionBean.getConnectedUser().getLogin(), actualPassword, newPassword);
            actualPassword = "";
            newPassword = "";
            confirmNewPassword = "";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Votre mot de passe a été changé", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (WrongPasswordException ex) {
            Logger.getLogger(ChangerMotDePasseBean.class.getName()).log(Level.FINE, null, ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Le mot de passe actuel ne correspond pas", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

}
