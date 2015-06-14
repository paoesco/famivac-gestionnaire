package fr.fava.gestionnaire.interfaces.web.utils;

import fr.fava.gestionnaire.application.enfant.EnfantDTO;
import fr.fava.gestionnaire.application.enfant.EnfantService;
import fr.fava.gestionnaire.domain.enfant.Enfant;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author paoesco
 */
@Named
@ApplicationScoped
@FacesConverter("enfantConverter")
public class EnfantConverter implements Converter {

    @Inject
    private EnfantService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Enfant bean = service.retrieve(Long.valueOf(value));
        return new EnfantDTO(bean);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        return String.valueOf(((EnfantDTO) value).getId());
    }

}
