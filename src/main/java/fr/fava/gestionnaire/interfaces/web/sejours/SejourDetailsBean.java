package fr.fava.gestionnaire.interfaces.web.sejours;

import fr.fava.gestionnaire.application.enfant.EnfantDTO;
import fr.fava.gestionnaire.application.enfant.EnfantService;
import fr.fava.gestionnaire.application.famille.FamilleDTO;
import fr.fava.gestionnaire.application.famille.FamilleService;
import fr.fava.gestionnaire.application.sejour.SejourService;
import fr.fava.gestionnaire.domain.sejour.Sejour;
import fr.fava.gestionnaire.domain.sejour.SejourRepository;
import fr.fava.gestionnaire.domain.sejour.StatutSejour;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
            return familleService.rechercher("%", "%");
        }
        return familleService.rechercher(query, "%");
    }

    public List<EnfantDTO> completeEnfant(String query) {
        if (query == null || query.isEmpty()) {
            return enfantService.retrieve("%", "%");
        }
        return enfantService.retrieve(query, "%");
    }

    public void terminerSejour() {
        sejourService.update(sejour);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Le séjour a été terminé.", ""));
    }

    public void annulerSejour() {
        sejour.setDateAnnulation(new Date());
        sejourService.update(sejour);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Le séjour a été annulé.", ""));
    }
    
    public void reactiverSejour(){
        sejour.setDateAnnulation(null);
        sejour.setDateFinReelle(null);
        sejour.setMotifAnnulation(null);
        sejour.setMotifFinSejour(null);
        sejourService.update(sejour);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Le séjour a été réactivé.", ""));
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

    public String getStatut() {
        Optional<StatutSejour> ostatut = sejour.statut(new Date());
        if (!ostatut.isPresent()) {
            return "";
        }
        return ostatut.get().name();
    }

}
