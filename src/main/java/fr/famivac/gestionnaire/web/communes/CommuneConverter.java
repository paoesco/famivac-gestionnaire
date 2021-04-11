package fr.famivac.gestionnaire.web.communes;

import fr.famivac.gestionnaire.commons.entity.Commune;
import fr.famivac.gestionnaire.domains.parametres.CommuneService;
import java.util.Objects;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
@FacesConverter("communeConverter")
public class CommuneConverter implements Converter<Commune> {

  private final CommuneService communeService;

  @Inject
  public CommuneConverter(CommuneService communeService) {
    this.communeService = communeService;
  }

  @Override
  public Commune getAsObject(FacesContext context, UIComponent component, String value) {
    return communeService.retrieve(value);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Commune value) {
    return Objects.isNull(value) ? null : value.getCode();
  }
}
