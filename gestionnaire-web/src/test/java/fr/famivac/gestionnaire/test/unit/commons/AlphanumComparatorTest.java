package fr.famivac.gestionnaire.test.unit.commons;

import fr.famivac.gestionnaire.commons.utils.AlphanumComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author pescobar
 */
public class AlphanumComparatorTest {

    private final AlphanumComparator alphanumComparator = new AlphanumComparator();

    @Test
    public void testTri() {
        List<String> liste = new ArrayList<>();
        liste.add("10");
        liste.add("9c");
        liste.add("99");
        liste.add("12");
        liste.add("A");
        liste.add("a");
        liste.add("B");
        liste.add("2");
        Collections.sort(liste, alphanumComparator);
        Assert.assertEquals(8, liste.size());
        Assert.assertEquals("2", liste.get(0));
        Assert.assertEquals("9c", liste.get(1));
        Assert.assertEquals("10", liste.get(2));
        Assert.assertEquals("12", liste.get(3));
        Assert.assertEquals("99", liste.get(4));
        Assert.assertEquals("A", liste.get(5));
        Assert.assertEquals("a", liste.get(6));
        Assert.assertEquals("B", liste.get(7));
    }

}
