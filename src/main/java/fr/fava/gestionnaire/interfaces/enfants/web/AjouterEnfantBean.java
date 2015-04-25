package fr.fava.gestionnaire.interfaces.enfants.web;

import fr.fava.gestionnaire.application.CommuneService;
import fr.fava.gestionnaire.application.enfant.EnfantService;
import fr.fava.gestionnaire.application.enfant.InscripteurService;
import fr.fava.gestionnaire.domain.model.Commune;
import fr.fava.gestionnaire.domain.enfant.Enfant;
import fr.fava.gestionnaire.domain.inscripteur.Inscripteur;
import fr.fava.gestionnaire.domain.inscripteur.TypeInscripteur;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class AjouterEnfantBean implements Serializable {

    private long inscripteurId;

    private List<Commune> communes;

    private Enfant form;

    @Inject
    private CommuneService communeService;

    @Inject
    private EnfantService enfantService;

    @Inject
    private InscripteurService inscripteurService;

    public void init() {
        form = new Enfant();
        Inscripteur inscripteur = inscripteurService.retrieve(inscripteurId);
        form.setInscripteur(inscripteur);
        communes = communeService.retrieve();
    }

    public String ajouter() {
        Long id = enfantService.create(form);
        return "/enfant/details.xhtml?id=" + id + "&faces-redirect=true";
    }

    public List<Commune> completeCommune(String query) {
        if (query == null || query.isEmpty()) {
            return communes;
        } else {
            return communes.stream().filter((Commune t) -> {
                return t.getLabel().toLowerCase().trim().contains(query.toLowerCase().trim());
            }).collect(Collectors.toList());
        }
    }

    public boolean isTypeInscripteurParticulier() {
        return TypeInscripteur.PARTICULIER.equals(form.getInscripteur().getType());
    }

    public boolean isTypeServiceSocialOuAutre() {
        return TypeInscripteur.SERVICE_SOCIAL.equals(form.getInscripteur().getType())
                || TypeInscripteur.AUTRE.equals(form.getInscripteur().getType());
    }

    public boolean isInscripteurEstResponsableLegal() {
        return form.getInscripteur().isResponsableLegal();
    }

    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }

    public CommuneService getCommuneService() {
        return communeService;
    }

    public Enfant getForm() {
        return form;
    }

    public void setForm(Enfant form) {
        this.form = form;
    }

    public long getInscripteurId() {
        return inscripteurId;
    }

    public void setInscripteurId(long inscripteurId) {
        this.inscripteurId = inscripteurId;
    }

}
