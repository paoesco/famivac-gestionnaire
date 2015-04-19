package fr.fava.gestionnaire.interfaces.enfants.web;

import fr.fava.gestionnaire.application.CommuneService;
import fr.fava.gestionnaire.application.enfant.EnfantService;
import fr.fava.gestionnaire.domain.model.enfant.Enfant;
import fr.fava.gestionnaire.domain.model.inscripteur.TypeInscripteur;
import fr.fava.gestionnaire.interfaces.utils.web.CompleteCommune;
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
public class EnfantDetailsBean implements Serializable, CompleteCommune {

    private long id;

    private Enfant form;

    @Inject
    private CommuneService communeService;

    @Inject
    private EnfantService enfantService;

    public void init() {
        form = enfantService.retrieve(id);
    }

    public void update() {
        enfantService.update(form);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sauvées", ""));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Enfant getForm() {
        return form;
    }

    public void setForm(Enfant form) {
        this.form = form;
    }

    @Override
    public CommuneService getCommuneService() {
        return communeService;
    }

    public boolean isTypeInscripteurParticulier() {
        return TypeInscripteur.PARTICULIER.equals(form.getInscripteur().getType());
    }

    public boolean isTypeServiceSocialOuAutre() {
        return TypeInscripteur.SERVICE_SOCIAL.equals(form.getInscripteur().getType())
                || TypeInscripteur.AUTRE.equals(form.getInscripteur().getType());
    }

    public boolean isInscripteurEstResponsableLegal() {
        return form.getInscripteur().isResponsableLegal();
    }

}
