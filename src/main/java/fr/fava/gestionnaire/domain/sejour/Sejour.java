package fr.fava.gestionnaire.domain.sejour;

import fr.fava.gestionnaire.domain.enfant.Enfant;
import fr.fava.gestionnaire.domain.famille.Famille;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author paoesco
 */
public class Sejour implements Serializable {
    
    private Famille famille;
    
    private Enfant enfant;

}
