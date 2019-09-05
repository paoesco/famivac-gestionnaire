package fr.famivac.gestionnaire.interfaces.web.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LogoutBean {

	@Inject
	private ExternalContext externalContext;

	public String submit() {
		externalContext.invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}

}
