package fr.famivac.gestionnaire.test.integration;

import java.io.File;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;

/**
 * @author pescobar
 */
@RunWith(Arquillian.class)
public abstract class AbstractIntegrationTest {

    /**
     * Déploiement complet de la webapp.
     *
     * @return archive au format Arquillian.
     */
    @Deployment
    public static WebArchive createDeployment() {
        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "fr.famivac.gestionnaire.dashboard")
                .addPackages(true, "fr.famivac.gestionnaire.email")
                .addPackages(true, "fr.famivac.gestionnaire.interfaces")
                .addPackages(true, "fr.famivac.gestionnaire.test") // ???
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"), "beans.xml")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"), "web.xml")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/applicationContext.xml"), "applicationContext.xml")
                .addAsResource(new File("src/main/resources/ValidationMessages_fr.properties"), "ValidationMessages_fr.properties")
                .addAsResource(new File("src/test/resources/arquillian/persistence.xml"), "META-INF/persistence.xml")
                .addAsWebInfResource(new File("src/test/resources/arquillian/wildfly-ds.xml"), "wildfly-ds.xml")
                .addAsLibraries(libs);
    }

}
