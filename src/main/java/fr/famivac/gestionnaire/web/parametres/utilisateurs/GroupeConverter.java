package fr.famivac.gestionnaire.web.parametres.utilisateurs;

import fr.famivac.gestionnaire.domains.utilisateurs.control.GroupeService;
import fr.famivac.gestionnaire.domains.utilisateurs.entity.Groupe;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author paoesco
 */
@Named
@ApplicationScoped
@FacesConverter("groupeConverter")
public class GroupeConverter implements Converter<Groupe> {

    @Inject
    private GroupeService groupeService;

    @Override
    public Groupe getAsObject(FacesContext context, UIComponent component, String value) {
        return groupeService.retrieve(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Groupe value) {
        if (value == null) {
            return null;
        }
        return ((Groupe) value).getNom();
    }

}
