package fr.famivac.interfaces.web.sejours;

import fr.famivac.gestionnaire.sejours.control.SejourDTO;
import fr.famivac.gestionnaire.sejours.control.SejourService;
import fr.famivac.gestionnaire.sejours.entity.StatutSejour;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class SejoursBean implements Serializable {

    /**
     * Liste des séjours.
     */
    private LazySejourDataModel lazyModel;

    @Inject
    private SejourService sejourService;

    @Inject
    private RechercherSejoursForm rechercherForm;

    public void init() {
        rechercherForm.setEnCours(true);
        rechercher();
    }

    public void supprimer(Long id) {
        sejourService.delete(id);
        init(); // recharge des séjours
    }

    public void rechercher() {
        StatutSejour statut = null;
        if (rechercherForm.isEnCours()) {
            statut = StatutSejour.EN_COURS;
        }
        List<SejourDTO> sejours = sejourService.rechercher(rechercherForm.getNomReferent(), rechercherForm.getPrenomReferent(), rechercherForm.getNomEnfant(), rechercherForm.getPrenomEnfant(), statut);
        lazyModel = new LazySejourDataModel(sejours);
    }

    public LazySejourDataModel getLazyModel() {
        return lazyModel;
    }

}
