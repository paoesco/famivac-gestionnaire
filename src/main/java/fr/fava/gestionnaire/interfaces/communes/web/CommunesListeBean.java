package fr.fava.gestionnaire.interfaces.communes.web;

import fr.fava.gestionnaire.domain.common.Commune;
import fr.fava.gestionnaire.application.CommuneService;
import java.io.Serializable;
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
public class CommunesListeBean implements Serializable {

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
