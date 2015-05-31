package fr.fava.gestionnaire.interfaces.utils.web;

import fr.fava.gestionnaire.application.GroupeService;
import fr.fava.gestionnaire.domain.common.Groupe;
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
public class GroupeConverter implements Converter {

    @Inject
    private GroupeService groupeService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return groupeService.retrieve(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        return ((Groupe) value).getNom();
    }

}
