package fr.famivac.gestionnaire.interfaces.web.security;

import fr.famivac.gestionnaire.administration.authentication.control.UtilisateurService;
import fr.famivac.gestionnaire.administration.authentication.entity.Utilisateur;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;

/**
 *
 * @author paoesco
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = -2139402738549202214L;

	@Inject
	private UtilisateurService utilisateurService;

	@Inject
	private SecurityContext securityContext;

	private Utilisateur connectedUser;

	@PostConstruct
	public void init() {
		String username = securityContext.getCallerPrincipal().getName();
		connectedUser = utilisateurService.retrieve(username);
	}

	public Utilisateur getConnectedUser() {
		return connectedUser;
	}

}
