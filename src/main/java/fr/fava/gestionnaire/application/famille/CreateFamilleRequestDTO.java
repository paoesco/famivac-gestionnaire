package fr.fava.gestionnaire.application.famille;

import fr.fava.gestionnaire.domain.common.Adresse;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paoesco
 */
@XmlRootElement
public class CreateFamilleRequestDTO {

    private Adresse adresse;

    private String projet;

    private MembreDTO membrePrincipal;

    private boolean candidature;

    public CreateFamilleRequestDTO() {
        adresse = new Adresse();
        membrePrincipal = new MembreDTO();
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getProjet() {
        return projet;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }

    public MembreDTO getMembrePrincipal() {
        return membrePrincipal;
    }

    public void setMembrePrincipal(MembreDTO membrePrincipal) {
        this.membrePrincipal = membrePrincipal;
    }

    public boolean isCandidature() {
        return candidature;
    }

    public void setCandidature(boolean candidature) {
        this.candidature = candidature;
    }

}
