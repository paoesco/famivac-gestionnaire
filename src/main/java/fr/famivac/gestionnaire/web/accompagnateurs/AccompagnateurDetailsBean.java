package fr.famivac.gestionnaire.web.accompagnateurs;

import fr.famivac.gestionnaire.domains.sejours.control.AccompagnateurService;
import fr.famivac.gestionnaire.domains.sejours.entity.Accompagnateur;
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

    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accompagnateur getForm() {
        return form;
    }

    public void setForm(Accompagnateur form) {
        this.form = form;
    }

}
