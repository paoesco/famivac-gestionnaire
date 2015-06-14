package fr.fava.gestionnaire.interfaces.web.utils;

import fr.fava.gestionnaire.application.famille.FamilleService;
import fr.fava.gestionnaire.application.famille.FamilleDTO;
import fr.fava.gestionnaire.domain.famille.Famille;
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
@FacesConverter("familleConverter")
public class FamilleConverter implements Converter {

    @Inject
    private FamilleService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Famille bean = service.get(Long.valueOf(value));
        return new FamilleDTO(bean);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        return ((FamilleDTO) value).getId().toString();
    }

}
