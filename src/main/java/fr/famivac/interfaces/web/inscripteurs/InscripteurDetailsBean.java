package fr.famivac.interfaces.web.inscripteurs;

import fr.famivac.gestionnaire.administration.control.CommuneService;
import fr.famivac.gestionnaire.enfants.control.InscripteurService;
import fr.famivac.gestionnaire.enfants.entity.Inscripteur;
import fr.famivac.gestionnaire.enfants.entity.TypeInscripteur;
import fr.famivac.interfaces.web.utils.CompleteCommune;
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
public class InscripteurDetailsBean implements Serializable, CompleteCommune {

    private long id;

    private Inscripteur form;

    @Inject
    private CommuneService communeService;

    @Inject
    private InscripteurService inscripteurService;

    public void init() {
        form = inscripteurService.retrieve(id);
    }

    public void update() {
        inscripteurService.update(form);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sauvées", ""));
    }

    public void supprimerEnfant(long enfantId) {
        inscripteurService.retirerEnfant(enfantId);
        init();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Inscripteur getForm() {
        return form;
    }

    public void setForm(Inscripteur form) {
        this.form = form;
    }

    @Override
    public CommuneService getCommuneService() {
        return communeService;
    }

    public boolean isTypeInscripteurParticulier() {
        return TypeInscripteur.PARTICULIER.equals(form.getType());
    }

    public boolean isTypeServiceSocialOuAutre() {
        return TypeInscripteur.SERVICE_SOCIAL.equals(form.getType())
                || TypeInscripteur.AUTRE.equals(form.getType());
    }

}
