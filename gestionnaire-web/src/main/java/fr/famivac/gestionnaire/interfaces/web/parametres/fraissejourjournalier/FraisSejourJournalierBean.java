package fr.famivac.gestionnaire.interfaces.web.parametres.fraissejourjournalier;

import fr.famivac.gestionnaire.administration.parametres.control.FraisSejourJournalierService;
import fr.famivac.gestionnaire.administration.parametres.entity.FraisSejourJournalier;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class FraisSejourJournalierBean implements Serializable {

    private FraisSejourJournalier form;

    /**
     * Liste des tarifs.
     */
    private LazyFraisSejourJournalierDataModel lazyModel;

    @Inject
    private FraisSejourJournalierService tarifsService;

    /**
     * Initialisation du bean.
     */
    public void init() {
        lazyModel = new LazyFraisSejourJournalierDataModel(tarifsService.retrieve());
        form = new FraisSejourJournalier();
    }

    public void onRowEdit(RowEditEvent event) {
        FraisSejourJournalier entity = (FraisSejourJournalier) event.getObject();
        tarifsService.update(entity);
        FacesMessage msg = new FacesMessage("Le tarif a été modifié");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void create() {
        tarifsService.create(form);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Le tarif a été ajouté", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        init();
    }

    public void supprimer(Long id) {
        tarifsService.delete(id);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Le tarif a été supprimé", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        init();
    }

    public LazyFraisSejourJournalierDataModel getLazyModel() {
        return lazyModel;
    }

    public FraisSejourJournalier getForm() {
        return form;
    }

    public void setForm(FraisSejourJournalier form) {
        this.form = form;
    }

}
