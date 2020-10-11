package fr.famivac.gestionnaire.familles.boundary;


import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class FamilleServiceTest {

    FamilleService service;

    @BeforeEach
    public void beforeEach() {
        service = new FamilleService();
    }

    public void getFamilles()  {
        List<FamilleDTO> familles = service.rechercher("tristant", "", false);
        assert !familles.isEmpty();
    }
}
