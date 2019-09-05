package fr.famivac.gestionnaire.web;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "/faces/login.xhtml", useForwardToLogin = false, errorPage = "/faces/login.xhtml?error=true"))
@FacesConfig
@ApplicationScoped
public class SecurityConfiguration {

}
