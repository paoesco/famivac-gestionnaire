package fr.famivac.gestionnaire.web.familles;

import fr.famivac.gestionnaire.familles.boundary.FamilleService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean des familles.
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class FamillesListeBean implements Serializable {

    /**
     * Liste des familles.
     */
    private LazyFamilleDataModel lazyModel;

    @Inject
    private FamilleService familleService;

    private List<String> periodesAccueil;

    private Boolean archivees;

    /**
     * Initialisation du bean.
     */
    public void init() {
        archivees = false;
        periodesAccueil = new ArrayList<>();
        rechercher();
    }

    public void rechercher() {
        lazyModel = new LazyFamilleDataModel(familleService.rechercher("", "", periodesAccueil, archivees));
    }

    public void supprimer(Long id) {
        familleService.delete(id);
        init(); // recharge des familles
    }

    public void archiver(Long id) {
        familleService.archiver(id);
        init();
    }

    public void desarchiver(Long id) {
        familleService.desarchiver(id);
        init();
    }

    public LazyFamilleDataModel getLazyModel() {
        return lazyModel;
    }

    public List<String> getPeriodesAccueil() {
        return periodesAccueil;
    }

    public void setPeriodesAccueil(List<String> periodesAccueil) {
        this.periodesAccueil = periodesAccueil;
    }

    public Boolean getArchivees() {
        return archivees;
    }

    public void setArchivees(Boolean archivees) {
        this.archivees = archivees;
    }

}
