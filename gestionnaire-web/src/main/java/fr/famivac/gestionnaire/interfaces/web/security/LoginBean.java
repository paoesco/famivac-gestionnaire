package fr.famivac.gestionnaire.interfaces.web.security;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

@Named
@RequestScoped
public class LoginBean {
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String username;
	
	@Inject
	private SecurityContext securityContext;
	
	@Inject
	private ExternalContext externalContext;
	
	@Inject
	private FacesContext facesContext;

	public void submit() throws IOException {
		switch (continueAuthentication()) {
		case SEND_CONTINUE:
			facesContext.responseComplete();
			break;
		case SEND_FAILURE:
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
			break;
		case SUCCESS:
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login succeed", null));
			externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");
			break;
		case NOT_DONE:
		}
	}

	private AuthenticationStatus continueAuthentication() {
		return securityContext.authenticate((HttpServletRequest) externalContext.getRequest(),
				(HttpServletResponse) externalContext.getResponse(),
				AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(getUsername(), getPassword())));
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}