package fr.fava.gestionnaire.interfaces.web.communes;

import fr.fava.gestionnaire.domain.common.Commune;
import fr.fava.gestionnaire.application.administration.CommuneService;
import fr.fava.gestionnaire.application.administration.RetrieveUtilisateursDTO;
import fr.fava.gestionnaire.domain.administration.Groupe;
import fr.fava.gestionnaire.domain.administration.Utilisateur;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;

/**
 * Backing bean des communes.
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class CommunesBean implements Serializable {

    /**
     * Liste des utilistaeurs.
     */
    private LazyCommuneDataModel lazyModel;

    @Inject
    private CommuneService communeService;

    private Commune form;

    /**
     * Initialisation du bean par la vue.
     */
    public void init() {
        form = new Commune();
        lazyModel = new LazyCommuneDataModel(communeService.retrieve());
    }

    public void delete(String code) {
        communeService.delete(code);
        init();
    }

    public void ajouter() {
        if (lazyModel.contains(form)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Une commune avec ce code existe déjà", null));
            return;
        }
        communeService.create(form);
        init();
    }

    public void onCellEdit(CellEditEvent event) {
        Commune bean = lazyModel.getRowData(event.getRowIndex());
        String oldValue = (String) event.getOldValue();
        String newValue = (String) event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            communeService.update(bean);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La commune a été mise à jour", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public LazyCommuneDataModel getLazyModel() {
        return lazyModel;
    }

    public CommuneService getCommuneService() {
        return communeService;
    }

    public void setCommuneService(CommuneService communeService) {
        this.communeService = communeService;
    }

    public Commune getForm() {
        return form;
    }

    public void setForm(Commune form) {
        this.form = form;
    }

}
