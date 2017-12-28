package fr.famivac.gestionnaire.interfaces.web.sejours;

import fr.famivac.gestionnaire.administration.parametres.control.FraisDossierService;
import fr.famivac.gestionnaire.administration.parametres.control.FraisSejourJournalierService;
import fr.famivac.gestionnaire.familles.control.FamilleDTO;
import fr.famivac.gestionnaire.familles.control.FamilleService;
import fr.famivac.gestionnaire.sejours.control.SejourService;
import fr.famivac.gestionnaire.enfants.control.EnfantDTO;
import fr.famivac.gestionnaire.enfants.control.EnfantService;
import fr.famivac.gestionnaire.sejours.entity.PeriodeJournee;
import java.io.Serializable;
import java.math.BigDecimal;
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

    @Inject
    private FraisSejourJournalierService fraisDeSejourJournalierService;

    @Inject
    private FraisDossierService fraisDossierService;

    private AjouterSejourForm form;

    public void init() {
        form = new AjouterSejourForm();
    }

    public String ajouter() {
        BigDecimal fraisSejourJournalier = fraisDeSejourJournalierService
                .getCurrentMontant(form.getDateDebut())
                .orElse(BigDecimal.ZERO);
        BigDecimal fraisDossier = fraisDossierService
                .getCurrentMontant(form.getDateDebut())
                .orElse(BigDecimal.ZERO);
        System.out.println(fraisSejourJournalier);
        long sejourId = sejourService.create(form.getFamille().getId(),
                form.getFamille().getNomReferent(),
                form.getFamille().getPrenomReferent(),
                form.getFamille().getTelephoneReferent(),
                form.getEnfant().getId(),
                form.getEnfant().getNomEnfant(),
                form.getEnfant().getPrenomEnfant(),
                form.getDateDebut(),
                PeriodeJournee.valueOf(form.getPeriodeJourneeDebut()),
                form.getDateFin(),
                PeriodeJournee.valueOf(form.getPeriodeJourneeFin()),
                fraisSejourJournalier,
                fraisDossier);
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
