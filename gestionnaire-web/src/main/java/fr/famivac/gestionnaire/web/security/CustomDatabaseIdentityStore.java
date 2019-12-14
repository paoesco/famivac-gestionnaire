package fr.famivac.gestionnaire.web.security;

import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import fr.famivac.gestionnaire.administration.authentication.control.UtilisateurService;
import fr.famivac.gestionnaire.administration.authentication.entity.Utilisateur;
import fr.famivac.gestionnaire.commons.exceptions.WrongCredentialsException;

@ApplicationScoped
public class CustomDatabaseIdentityStore implements IdentityStore {

	@Inject
	private UtilisateurService utilisateurService;

	@Override
	public CredentialValidationResult validate(Credential credential) {
		UsernamePasswordCredential credentials = (UsernamePasswordCredential) credential;
		String login = credentials.getCaller();
		String password = credentials.getPasswordAsString();
		try {
			Utilisateur logged = utilisateurService.login(login, password);
			return new CredentialValidationResult(logged.getLogin(),
					logged.getGroupes().stream().map(groupe -> groupe.getNom()).collect(Collectors.toSet()));
		} catch (WrongCredentialsException ex) {
			return CredentialValidationResult.NOT_VALIDATED_RESULT;
		}
	}

}
