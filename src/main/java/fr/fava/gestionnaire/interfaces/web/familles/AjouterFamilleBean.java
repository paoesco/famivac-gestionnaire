package fr.fava.gestionnaire.interfaces.web.familles;

import fr.fava.gestionnaire.application.famille.FamilleService;
import fr.fava.gestionnaire.application.famille.CreateFamilleRequestDTO;
import fr.fava.gestionnaire.application.CommuneService;
import fr.fava.gestionnaire.interfaces.web.utils.CompleteCommune;
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
public class AjouterFamilleBean implements Serializable, CompleteCommune {

    private CreateFamilleRequestDTO form;

    @Inject
    private FamilleService familleService;

    @Inject
    private CommuneService communeService;

    public void init() {
        form = new CreateFamilleRequestDTO();
    }

    public String ajouter() {
        Long familleId = familleService.create(form);
        return "/famille/details.xhtml?id=" + familleId + "&faces-redirect=true";
    }

    public CreateFamilleRequestDTO getForm() {
        return form;
    }

    public void setForm(CreateFamilleRequestDTO form) {
        this.form = form;
    }

    @Override
    public CommuneService getCommuneService() {
        return communeService;
    }

}
