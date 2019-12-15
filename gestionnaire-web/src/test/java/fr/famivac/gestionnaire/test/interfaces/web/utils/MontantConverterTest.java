package fr.famivac.gestionnaire.test.interfaces.web.utils;

import fr.famivac.gestionnaire.web.utils.MontantConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 *
 * @author paoesco
 */
public class MontantConverterTest {
    
    private MontantConverter converter;
    
    @BeforeAll
    public void before() {
        this.converter = new MontantConverter();
    }
    
    @Test
    public void testConvertToBigDecimal() {
        // GIVEN
        String montant = "10.12";
        // WHEN
        BigDecimal result = (BigDecimal) this.converter.getAsObject(null, null, montant);
        // THEN
        Assertions.assertEquals(10.12, result.doubleValue(), 0);
    }
    
    @Test
    public void testConvertToString() {
        // GIVEN
        BigDecimal montant = new BigDecimal("10.12");
        // WHEN
        String result = this.converter.getAsString(null, null, montant);
        // THEN
        Assertions.assertEquals("10.12", result);
    }
    
}
