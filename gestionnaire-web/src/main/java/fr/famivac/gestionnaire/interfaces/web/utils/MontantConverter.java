package fr.famivac.gestionnaire.interfaces.web.utils;

import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author paoesco
 */
@FacesConverter
public class MontantConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return new BigDecimal(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        BigDecimal montant = (BigDecimal) value;
        return montant.toPlainString();
    }

}
