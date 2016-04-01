package fr.famivac.gestionnaire.interfaces.web.sejours;

import fr.famivac.gestionnaire.familles.control.FamilleDTO;
import fr.famivac.gestionnaire.familles.control.FamilleService;
import fr.famivac.gestionnaire.sejours.control.SejourService;
import fr.famivac.gestionnaire.enfants.control.EnfantDTO;
import fr.famivac.gestionnaire.enfants.control.EnfantService;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class AjouterSejourBean implements Serializable {

    @Inject
    private FamilleService familleService;

    @Inject
    private EnfantService enfantService;

    @Inject
    private SejourService sejourService;

    private AjouterSejourForm form;

    public void init() {
        form = new AjouterSejourForm();
    }

    public String ajouter() {
        long sejourId = sejourService.create(form.getFamille().getId(),
                form.getFamille().getNomReferent(),
                form.getFamille().getPrenomReferent(),
                form.getEnfant().getId(),
                form.getEnfant().getNomEnfant(),
                form.getEnfant().getPrenomEnfant(),
                form.getDateDebut(),
                form.getDateFin());
        return "/sejours/details.xhtml?id=" + sejourId + "&faces-redirect=true";
    }

    public List<FamilleDTO> completeFamille(String query) {
        if (query == null || query.isEmpty()) {
            return familleService.rechercher("%", "%", null);
        }
        return familleService.rechercher(query, "%", null);
    }

    public List<EnfantDTO> completeEnfant(String query) {
        if (query == null || query.isEmpty()) {
            return enfantService.retrieve("%", "%");
        }
        return enfantService.retrieve(query, "%");
    }

    public AjouterSejourForm getForm() {
        return form;
    }

    public void setForm(AjouterSejourForm form) {
        this.form = form;
    }

}
