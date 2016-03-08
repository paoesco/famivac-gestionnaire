package fr.famivac.interfaces.web.utils;

import fr.famivac.gestionnaire.sejours.control.AccompagnateurService;
import fr.famivac.gestionnaire.sejours.entity.Accompagnateur;
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
@FacesConverter("accompagnateurConverter")
public class AccompagnateurConverter implements Converter {

    @Inject
    private AccompagnateurService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Accompagnateur bean = service.get(Long.valueOf(value));
        return bean;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || ((Accompagnateur) value).getId() == null) {
            return null;
        }
        return ((Accompagnateur) value).getId().toString();
    }

}
