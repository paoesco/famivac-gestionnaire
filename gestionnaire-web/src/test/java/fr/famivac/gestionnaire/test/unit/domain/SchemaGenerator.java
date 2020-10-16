package fr.famivac.gestionnaire.test.unit.domain;

import javax.persistence.Persistence;

/**
 *
 * @author paoesco
 */
public class SchemaGenerator {

    public static void main(String[] args) {
        Persistence.generateSchema("gestionnairePUTest", null);
    }
}
