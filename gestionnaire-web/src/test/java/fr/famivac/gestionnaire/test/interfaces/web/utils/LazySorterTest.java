package fr.famivac.gestionnaire.test.interfaces.web.utils;

import fr.famivac.gestionnaire.sejours.control.SejourDTO;
import fr.famivac.gestionnaire.enfants.entity.Inscripteur;
import fr.famivac.gestionnaire.enfants.entity.TypeInscripteur;
import fr.famivac.gestionnaire.interfaces.web.utils.LazySorter;
import fr.famivac.gestionnaire.test.unit.commons.DateUtilsTest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.primefaces.model.SortOrder;

/**
 *
 * @author pescobar
 */
public class LazySorterTest {

    private Date toDate(String s) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(s);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtilsTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail();
            return null;
        }
    }

    public List<SejourDTO> getSejours() {
        List<SejourDTO> sejours = new ArrayList<>(8);
        SejourDTO s1 = new SejourDTO();
        s1.setFamille("10");
        s1.setDateDebut(toDate("01/03/2015"));
        sejours.add(s1);
        SejourDTO s2 = new SejourDTO();
        s2.setFamille("9c");
        s2.setDateDebut(toDate("01/02/2015"));
        sejours.add(s2);
        SejourDTO s3 = new SejourDTO();
        s3.setFamille("99");
        s3.setDateDebut(toDate("01/10/2015"));
        sejours.add(s3);
        SejourDTO s4 = new SejourDTO();
        s4.setFamille("12");
        s4.setDateDebut(toDate("01/10/2015"));
        sejours.add(s4);
        SejourDTO s5 = new SejourDTO();
        s5.setFamille("2");
        s5.setDateDebut(toDate("01/01/2015"));
        sejours.add(s5);
        SejourDTO s6 = new SejourDTO();
        s6.setFamille("A");
        s6.setDateDebut(toDate("01/10/2015"));
        sejours.add(s6);
        SejourDTO s7 = new SejourDTO();
        s7.setFamille("a");
        s7.setDateDebut(toDate("01/10/2015"));
        sejours.add(s7);
        SejourDTO s8 = new SejourDTO();
        s8.setFamille("B");
        s8.setDateDebut(toDate("01/10/2015"));
        sejours.add(s8);

        Assert.assertEquals("10", sejours.get(0).getFamille());
        Assert.assertEquals("9c", sejours.get(1).getFamille());
        Assert.assertEquals("99", sejours.get(2).getFamille());
        Assert.assertEquals("12", sejours.get(3).getFamille());
        Assert.assertEquals("2", sejours.get(4).getFamille());
        Assert.assertEquals("A", sejours.get(5).getFamille());
        Assert.assertEquals("a", sejours.get(6).getFamille());
        Assert.assertEquals("B", sejours.get(7).getFamille());

        return sejours;
    }

    public List<Inscripteur> getInscripteurs() {
        List<Inscripteur> inscripteurs = new ArrayList<>(3);
        Inscripteur i1 = new Inscripteur();
        i1.setType(TypeInscripteur.PARTICULIER);
        inscripteurs.add(i1);
        Inscripteur i2 = new Inscripteur();
        i2.setType(TypeInscripteur.SERVICE_SOCIAL);
        inscripteurs.add(i2);
        Inscripteur i3 = new Inscripteur();
        i3.setType(TypeInscripteur.AUTRE);
        inscripteurs.add(i3);

        return inscripteurs;
    }

    @Test(expected = RuntimeException.class)
    public void testTriException() {
        List<SejourDTO> sejours = getSejours();
        LazySorter<SejourDTO> lazySorter = new LazySorter<>(SejourDTO.class, "xxxx", SortOrder.ASCENDING);
        Collections.sort(sejours, lazySorter);
    }

    @Test
    public void testTriStringAscending() {
        List<SejourDTO> sejours = getSejours();
        LazySorter<SejourDTO> lazySorter = new LazySorter<>(SejourDTO.class, "famille", SortOrder.ASCENDING);
        Collections.sort(sejours, lazySorter);
        Assert.assertEquals(8, sejours.size());
        Assert.assertEquals("2", sejours.get(0).getFamille());
        Assert.assertEquals("9c", sejours.get(1).getFamille());
        Assert.assertEquals("10", sejours.get(2).getFamille());
        Assert.assertEquals("12", sejours.get(3).getFamille());
        Assert.assertEquals("99", sejours.get(4).getFamille());
        Assert.assertEquals("A", sejours.get(5).getFamille());
        Assert.assertEquals("a", sejours.get(6).getFamille());
        Assert.assertEquals("B", sejours.get(7).getFamille());

    }

    @Test
    public void testTriStringDescending() {
        List<SejourDTO> sejours = getSejours();
        LazySorter<SejourDTO> lazySorter = new LazySorter<>(SejourDTO.class, "famille", SortOrder.DESCENDING);
        Collections.sort(sejours, lazySorter);
        Assert.assertEquals(8, sejours.size());
        Assert.assertEquals("B", sejours.get(0).getFamille());
        Assert.assertEquals("A", sejours.get(1).getFamille());
        Assert.assertEquals("a", sejours.get(2).getFamille());
        Assert.assertEquals("99", sejours.get(3).getFamille());
        Assert.assertEquals("12", sejours.get(4).getFamille());
        Assert.assertEquals("10", sejours.get(5).getFamille());
        Assert.assertEquals("9c", sejours.get(6).getFamille());
        Assert.assertEquals("2", sejours.get(7).getFamille());
    }

    @Test
    public void testTriEnumAscending() {
        List<Inscripteur> inscripteurs = getInscripteurs();
        LazySorter<Inscripteur> lazySorter = new LazySorter<>(Inscripteur.class, "type", SortOrder.ASCENDING);
        Collections.sort(inscripteurs, lazySorter);
        Assert.assertEquals(3, inscripteurs.size());
        Assert.assertEquals(TypeInscripteur.AUTRE, inscripteurs.get(0).getType());
        Assert.assertEquals(TypeInscripteur.PARTICULIER, inscripteurs.get(1).getType());
        Assert.assertEquals(TypeInscripteur.SERVICE_SOCIAL, inscripteurs.get(2).getType());
    }

    @Test
    public void testTriEnumDescending() {
        List<Inscripteur> inscripteurs = getInscripteurs();
        LazySorter<Inscripteur> lazySorter = new LazySorter<>(Inscripteur.class, "type", SortOrder.DESCENDING);
        Collections.sort(inscripteurs, lazySorter);
        Assert.assertEquals(3, inscripteurs.size());
        Assert.assertEquals(TypeInscripteur.SERVICE_SOCIAL, inscripteurs.get(0).getType());
        Assert.assertEquals(TypeInscripteur.PARTICULIER, inscripteurs.get(1).getType());
        Assert.assertEquals(TypeInscripteur.AUTRE, inscripteurs.get(2).getType());
    }

    @Test
    public void testTriDateAscending() {
        List<SejourDTO> sejours = getSejours();
        LazySorter<SejourDTO> lazySorter = new LazySorter<>(SejourDTO.class, "dateDebut", SortOrder.ASCENDING);
        Collections.sort(sejours, lazySorter);
        Assert.assertEquals(8, sejours.size());
        Assert.assertEquals("2", sejours.get(0).getFamille());
        Assert.assertEquals("9c", sejours.get(1).getFamille());
        Assert.assertEquals("10", sejours.get(2).getFamille());
    }

    @Test
    public void testTriDateDescending() {
        List<SejourDTO> sejours = getSejours();
        LazySorter<SejourDTO> lazySorter = new LazySorter<>(SejourDTO.class, "dateDebut", SortOrder.DESCENDING);
        Collections.sort(sejours, lazySorter);
        Assert.assertEquals(8, sejours.size());
        Assert.assertEquals("10", sejours.get(5).getFamille());
        Assert.assertEquals("9c", sejours.get(6).getFamille());
        Assert.assertEquals("2", sejours.get(7).getFamille());
    }

}
