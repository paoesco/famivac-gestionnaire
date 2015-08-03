package fr.fava.gestionnaire.interfaces.web.accompagnateurs;

import fr.fava.gestionnaire.application.accompagnateur.AccompagnateurService;
import fr.fava.gestionnaire.domain.sejour.Accompagnateur;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class AccompagnateurDetailsBean implements Serializable {

    private long id;

    private Accompagnateur form;

    @Inject
    private AccompagnateurService accompagnateurService;

    public void init() {
        form = accompagnateurService.get(id);
    }

    public void update() {
        accompagnateurService.update(form);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sauvées", ""));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Accompagnateur getForm() {
        return form;
    }

    public void setForm(Accompagnateur form) {
        this.form = form;
    }

}
