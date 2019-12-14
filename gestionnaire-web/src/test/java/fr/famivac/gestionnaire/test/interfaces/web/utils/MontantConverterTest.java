package fr.famivac.gestionnaire.test.interfaces.web.utils;

import fr.famivac.gestionnaire.web.utils.MontantConverter;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author paoesco
 */
public class MontantConverterTest {
    
    private MontantConverter converter;
    
    @Before
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
        Assert.assertEquals(10.12, result.doubleValue(), 0);
    }
    
    @Test
    public void testConvertToString() {
        // GIVEN
        BigDecimal montant = new BigDecimal("10.12");
        // WHEN
        String result = this.converter.getAsString(null, null, montant);
        // THEN
        Assert.assertEquals("10.12", result);
    }
    
}
