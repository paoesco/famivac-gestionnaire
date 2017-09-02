package fr.famivac.gestionnaire.interfaces.web.sejours.voyages;

import fr.famivac.gestionnaire.sejours.control.SejourService;
import fr.famivac.gestionnaire.sejours.control.VoyageDTO;
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
public class VoyagesPageBean implements Serializable {

    private LazyVoyagesDataModel lazyModel;

    @Inject
    private SejourService sejourService;

    public void init() {
        List<VoyageDTO> voyages = sejourService.getVoyages();
        lazyModel = new LazyVoyagesDataModel(voyages);
    }

    public LazyVoyagesDataModel getLazyModel() {
        return lazyModel;
    }

}