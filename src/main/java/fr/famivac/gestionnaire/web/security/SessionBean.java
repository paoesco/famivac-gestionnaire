package fr.famivac.gestionnaire.web.security;

import fr.famivac.gestionnaire.domains.utilisateurs.control.UtilisateurService;
import fr.famivac.gestionnaire.domains.utilisateurs.entity.Utilisateur;
import java.io.Serializable;
import java.security.Principal;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;

/**
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
		Principal principal = securityContext.getCallerPrincipal();
		if (Objects.nonNull(principal)) {
			connectedUser = utilisateurService.retrieve(principal.getName());
		}
	}

	public Utilisateur getConnectedUser() {
		return connectedUser;
	}

}
