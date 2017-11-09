package fr.famivac.gestionnaire.interfaces.web.parametres.tarifs;

import fr.famivac.gestionnaire.administration.parametres.control.TarifsService;
import fr.famivac.gestionnaire.administration.parametres.entity.Tarif;
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
public class TarifsBean implements Serializable {

    private Tarif form;

    /**
     * Liste des tarifs.
     */
    private LazyTarifsDataModel lazyModel;

    @Inject
    private TarifsService tarifsService;

    /**
     * Initialisation du bean.
     */
    public void init() {
        lazyModel = new LazyTarifsDataModel(tarifsService.retrieve());
        form = new Tarif();
    }

    public void onRowEdit(RowEditEvent event) {
        Tarif entity = (Tarif) event.getObject();
        tarifsService.update(entity);
        FacesMessage msg = new FacesMessage("Le tarif a été modifié");
        FacesContext.getCurrentInstance().addMessage(null, msg);
//        Tarif bean = lazyModel.getRowData(event.getRowIndex());
//        tari
//        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "L'utilisateur a été modifié", null);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        RetrieveUtilisateursDTO dto = lazyModel.getRowData(event.getRowIndex());
//        List<Groupe> oldValue = (List<Groupe>) event.getOldValue();
//        List<Groupe> newValue = (List<Groupe>) event.getNewValue();
//
//        if (newValue != null && !newValue.equals(oldValue)) {
//            Utilisateur utilisateur = utilisateurService.retrieve(dto.getLogin());
//            utilisateur.setGroupes(new HashSet<>(newValue));
//            utilisateurService.update(utilisateur);
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "L'utilisateur a été modifié", null);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }

    }

    public void newTarif() {
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

    public LazyTarifsDataModel getLazyModel() {
        return lazyModel;
    }

    public Tarif getForm() {
        return form;
    }

    public void setForm(Tarif form) {
        this.form = form;
    }

}
