package fr.famivac.interfaces.web.accompagnateurs;

import fr.famivac.gestionnaire.sejours.control.AccompagnateurService;
import fr.famivac.gestionnaire.sejours.entity.Accompagnateur;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class AccompagnateurAjoutBean implements Serializable {

    private Accompagnateur form;

    @Inject
    private AccompagnateurService accompagnateurService;

    public void init() {
        form = new Accompagnateur();
    }

    public String ajouter() {
        Long accId = accompagnateurService.create(form);
        return "/accompagnateurs/details.xhtml?id=" + accId + "&faces-redirect=true";
    }

    public Accompagnateur getForm() {
        return form;
    }

    public void setForm(Accompagnateur form) {
        this.form = form;
    }

}
