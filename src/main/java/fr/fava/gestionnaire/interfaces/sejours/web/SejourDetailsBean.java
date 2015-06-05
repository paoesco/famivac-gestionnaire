package fr.fava.gestionnaire.interfaces.sejours.web;

import fr.fava.gestionnaire.application.enfant.EnfantDTO;
import fr.fava.gestionnaire.application.enfant.EnfantService;
import fr.fava.gestionnaire.application.famille.FamilleDTO;
import fr.fava.gestionnaire.application.famille.FamilleService;
import fr.fava.gestionnaire.application.sejour.SejourService;
import fr.fava.gestionnaire.domain.sejour.Sejour;
import fr.fava.gestionnaire.domain.sejour.SejourRepository;
import java.io.Serializable;
import java.util.List;
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
public class SejourDetailsBean implements Serializable {

    @Inject
    private SejourRepository sejourRepository;

    @Inject
    private SejourService sejourService;

    @Inject
    private FamilleService familleService;

    @Inject
    private EnfantService enfantService;

    private Long id;

    private Sejour sejour;

    /**
     * Initialisation du bean.
     */
    public void init() {
        this.sejour = sejourRepository.get(id);
    }

    public void update() {
        sejourService.update(sejour);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sauvées", ""));
    }

    public List<FamilleDTO> completeFamille(String query) {
        if (query == null || query.isEmpty()) {
            return familleService.retrieve("%", "%");
        }
        return familleService.retrieve(query, "%");
    }

    public List<EnfantDTO> completeEnfant(String query) {
        if (query == null || query.isEmpty()) {
            return enfantService.retrieve("%", "%");
        }
        return enfantService.retrieve(query, "%");
    }

    public Sejour getSejour() {
        return sejour;
    }

    public void setSejour(Sejour sejour) {
        this.sejour = sejour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
