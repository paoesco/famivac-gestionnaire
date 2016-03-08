package fr.famivac.gestionnaire.dashboard.control;

/**
 * @author paoesco
 */
public class DashboardDTO {

    private long nombreFamilles;
    private long nombreEnfants;
    private long nombreInscripteurs;
    private long nombreSejoursEnCours;

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

}
