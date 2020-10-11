package fr.famivac.gestionnaire.familles.boundary;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;
import fr.famivac.gestionnaire.familles.entity.PeriodeAccueil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Set;

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
