package fr.famivac.interfaces.web.index;

import fr.famivac.gestionnaire.dashboard.control.DashboardDTO;
import fr.famivac.gestionnaire.dashboard.control.DashboardService;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean index.
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class IndexBean implements Serializable {

    private long nombreFamilles;
    private long nombreEnfants;
    private long nombreInscripteurs;
    private long nombreSejoursEnCours;

    @Inject
    private DashboardService dashboardService;

    public void init() {
        DashboardDTO dashboardDTO = dashboardService.getDefault();
        nombreEnfants = dashboardDTO.getNombreEnfants();
        nombreFamilles = dashboardDTO.getNombreFamilles();
        nombreInscripteurs = dashboardDTO.getNombreInscripteurs();
        nombreSejoursEnCours = dashboardDTO.getNombreSejoursEnCours();
    }

    public long getNombreFamilles() {
        return nombreFamilles;
    }

    public void setNombreFamilles(long nombreFamilles) {
        this.nombreFamilles = nombreFamilles;
    }

    public long getNombreEnfants() {
        return nombreEnfants;
    }

    public void setNombreEnfants(long nombreEnfants) {
        this.nombreEnfants = nombreEnfants;
    }

    public long getNombreInscripteurs() {
        return nombreInscripteurs;
    }

    public void setNombreInscripteurs(long nombreInscripteurs) {
        this.nombreInscripteurs = nombreInscripteurs;
    }

    public long getNombreSejoursEnCours() {
        return nombreSejoursEnCours;
    }

    public void setNombreSejoursEnCours(long nombreSejoursEnCours) {
        this.nombreSejoursEnCours = nombreSejoursEnCours;
    }

    public DashboardService getDashboardService() {
        return dashboardService;
    }

    public void setDashboardService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

}
