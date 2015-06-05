package fr.fava.gestionnaire.interfaces.inscripteurs.web;

import fr.fava.gestionnaire.application.CommuneService;
import fr.fava.gestionnaire.application.enfant.InscripteurService;
import fr.fava.gestionnaire.domain.common.Commune;
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
public class AjouterInscripteurBean implements Serializable {

    private List<Commune> communes;

    private Inscripteur form;

    @Inject
    private CommuneService communeService;

    @Inject
    private InscripteurService inscripteurService;

    public void init() {
        form = new Inscripteur();
        communes = communeService.retrieve();
    }

    public String ajouter() {
        Long id = inscripteurService.create(form);
        return "/inscripteur/details.xhtml?id=" + id + "&faces-redirect=true";
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
        return TypeInscripteur.PARTICULIER.equals(form.getType());
    }

    public boolean isTypeServiceSocialOuAutre() {
        return TypeInscripteur.SERVICE_SOCIAL.equals(form.getType())
                || TypeInscripteur.AUTRE.equals(form.getType());
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

    public Inscripteur getForm() {
        return form;
    }

    public void setForm(Inscripteur form) {
        this.form = form;
    }

}
