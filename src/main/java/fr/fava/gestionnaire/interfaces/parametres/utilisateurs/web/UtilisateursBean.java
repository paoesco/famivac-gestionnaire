package fr.fava.gestionnaire.interfaces.parametres.utilisateurs.web;

import fr.fava.gestionnaire.application.groupe.GroupeService;
import fr.fava.gestionnaire.domain.model.Groupe;
import fr.fava.gestionnaire.application.UtilisateurService;
import fr.fava.gestionnaire.application.dto.AjouterUtilisateurDTO;
import fr.fava.gestionnaire.application.dto.RetrieveUtilisateursDTO;
import fr.fava.gestionnaire.interfaces.utilisateurs.facade.UtilisateurServiceFacade;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean des enfants.
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class UtilisateursBean implements Serializable {

    private AjouterUtilisateurDTO form;

    /**
     * Liste des utilistaeurs.
     */
    private LazyUtilisateurDataModel lazyModel;

    @Inject
    private UtilisateurService utilisateurService;
    @Inject
    private UtilisateurServiceFacade utilisateurServiceFacade;
    @Inject
    private GroupeService groupeService;

    private List<Groupe> groupes;

    /**
     * Initialisation du bean par la vue.
     */
    public void init() {
        lazyModel = new LazyUtilisateurDataModel(utilisateurService.retrieve());
        groupes = groupeService.retrieve();
        form = new AjouterUtilisateurDTO();
        Groupe groupeGestionnaire = groupes.stream().filter(g -> Groupe.ROLE_GESTIONNAIRE.equals(g.getNom())).findFirst().get();
        form.setGroupe(groupeGestionnaire);
    }

    public List<Groupe> completeGroupe(String query) {
        if (query == null || query.isEmpty()) {
            return groupes;
        }
        return groupes
                .stream()
                .filter(
                        g -> g.getLibelle().toLowerCase().trim().contains(query.toLowerCase().trim())
                ).collect(Collectors.toList());
    }

    public void ajouter() {
        String password = utilisateurServiceFacade.create(form);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "L'utilisateur a été ajouté", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        FacesMessage displayPass = new FacesMessage(FacesMessage.SEVERITY_INFO, MessageFormat.format("Le mot de passe généré est : {0}", password), null);
        FacesContext.getCurrentInstance().addMessage(null, displayPass);
        init();
    }

    public void supprimer(RetrieveUtilisateursDTO user) {
        utilisateurService.delete(user.getLogin());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "L'utilisateur a été supprimé", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        init();
    }

    public void reinitialiserMotDePasse(RetrieveUtilisateursDTO user) {
        String password = utilisateurServiceFacade.reinitPassword(user.getLogin(), user.getEmail());
        FacesMessage displayPass = new FacesMessage(FacesMessage.SEVERITY_INFO, MessageFormat.format("Le mot de passe a été réinitialisé : {0}", password), null);
        FacesContext.getCurrentInstance().addMessage(null, displayPass);
    }

    public LazyUtilisateurDataModel getLazyModel() {
        return lazyModel;
    }

    public AjouterUtilisateurDTO getForm() {
        return form;
    }

    public void setForm(AjouterUtilisateurDTO form) {
        this.form = form;
    }

    public List<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<Groupe> groupes) {
        this.groupes = groupes;
    }

}
