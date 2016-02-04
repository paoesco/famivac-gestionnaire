package fr.fava.gestionnaire.application;

import fr.fava.gestionnaire.domain.enfant.EnfantRepository;
import fr.fava.gestionnaire.domain.famille.FamilleRepository;
import fr.fava.gestionnaire.domain.inscripteur.InscripteurRepository;
import fr.fava.gestionnaire.domain.sejour.SejourRepository;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import net.bull.javamelody.MonitoringInterceptor;

/**
 * @author paoesco
 */
@Stateless
@Interceptors({MonitoringInterceptor.class})
@TransactionAttribute(TransactionAttributeType.NEVER)
public class DashboardService {
    
    @Inject
    private FamilleRepository familleRepository;
    @Inject
    private EnfantRepository enfantRepository;
    @Inject
    private SejourRepository sejourRepository;
    @Inject
    private InscripteurRepository inscripteurRepository;
    
    public DashboardDTO getDefault() {
        DashboardDTO dashboardDTO = new DashboardDTO();
        dashboardDTO.setNombreFamilles(familleRepository.count());
        dashboardDTO.setNombreEnfants(enfantRepository.count());
        dashboardDTO.setNombreInscripteurs(inscripteurRepository.count());
        dashboardDTO.setNombreSejoursEnCours(sejourRepository.countActifs());
        return dashboardDTO;
    }
    
}
