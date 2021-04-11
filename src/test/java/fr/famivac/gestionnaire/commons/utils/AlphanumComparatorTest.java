package fr.famivac.gestionnaire.commons.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        Assertions.assertEquals(8, liste.size());
        Assertions.assertEquals("2", liste.get(0));
        Assertions.assertEquals("9c", liste.get(1));
        Assertions.assertEquals("10", liste.get(2));
        Assertions.assertEquals("12", liste.get(3));
        Assertions.assertEquals("99", liste.get(4));
        Assertions.assertEquals("A", liste.get(5));
        Assertions.assertEquals("a", liste.get(6));
        Assertions.assertEquals("B", liste.get(7));
    }

}
