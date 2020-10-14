package fr.famivac.gestionnaire.familles.boundary;


import kong.unirest.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;
import java.util.Objects;

public class FamilleServiceTest {

    FamilleService service;

    MockedStatic<Unirest> unirestMock;

    @BeforeEach
    public void beforeEach() {
        System.setProperty("auth0.domain", "http://auth0.local");
        System.setProperty("auth0.api.audience", "http://api.local");
        service = new FamilleService();
        unirestMock = Mockito.mockStatic(Unirest.class);
        this.mockAuthentication();
        this.mockRequest();


    }

    @AfterEach
    public void afterEach() {
        if (!Objects.isNull(unirestMock)) {
            unirestMock.close();
        }
    }

    @Test
    public void getFamilles()  {
        List<FamilleDTO> familles = service.rechercher("tristant", "", false);
        assert !familles.isEmpty();
        FamilleDTO famille = familles.get(0);
        assert famille.getNomReferent().equals("TRISTANT");
        assert famille.getEmailReferent().equals("a@a.com");
        assert famille.getTelephoneReferent().equals("telephone1");
    }

    private void mockAuthentication() {
        JsonNode node = new JsonNode("{\"access_token\": \"access_token\"}");
        HttpResponse<JsonNode> responseMock = Mockito.mock(HttpResponse.class);
        HttpRequestWithBody postRequestMock = Mockito.mock(HttpRequestWithBody.class);
        RequestBodyEntity requestBodyEntity = Mockito.mock(RequestBodyEntity.class);

        Mockito.when(responseMock.getBody()).thenReturn(node);
        Mockito.when(postRequestMock.header(Mockito.anyString(), Mockito.anyString())).thenReturn(postRequestMock);
        Mockito.when(postRequestMock.body(Mockito.anyString())).thenReturn(requestBodyEntity);
        Mockito.when(requestBodyEntity.asJson()).thenReturn(responseMock);
        unirestMock.when(() -> Unirest.post(Mockito.anyString())).thenReturn(postRequestMock);
    }

    private void mockRequest() {
        JsonNode node = new JsonNode("{\"_embedded\":{\"familles\":[{\"id\":49,\"membres\":[{\"id\":50,\"nom\":\"TRISTANT\",\"nomDeNaissance\":\"TRISTANT\",\"prenom\":\"Catherine\",\"sexe\":\"FEMME\",\"dateNaissance\":\"1965-07-05\",\"referent\":true,\"communeDeNaissance\":{},\"profession\":\"Educatrice \",\"lienDeParente\":\"\",\"coordonnees\":{\"email\":\"a@a.com\",\"fax\":\"fax\",\"telephone1\":\"telephone1\",\"telephone2\":\"telephone2\"}},{\"id\":51,\"nom\":\"TRISTANT\",\"nomDeNaissance\":\"TRISTANT\",\"prenom\":\"Catherine\",\"sexe\":\"FEMME\",\"dateNaissance\":\"1965-07-05\",\"referent\":false,\"communeDeNaissance\":{},\"profession\":\"Educatrice \",\"lienDeParente\":\"\",\"coordonnees\":{}}],\"adresse\":{},\"periodesSouhaitees\":[\"AOUT\",\"NOEL\",\"JUILLET\",\"PRINTEMPS\"],\"sejoursComplets\":false,\"precisionsSejoursNonComplets\":\"7 juillet au 21 août\",\"projet\":\"Faire passer de bonnes vacances \",\"chambres\":[{}],\"tranchesAges\":[\"DE_6_A_9_ANS\",\"DE_9_A_12_ANS\",\"INDIFFERENT\",\"MOINS_DE_6_ANS\",\"PLUS_DE_12_ANS\"],\"connaissanceAssociation\":null,\"nombreFillesSouhaitees\":2,\"nombreGarconsSouhaites\":null,\"accepteHandicap\":false,\"accepteMalade\":false,\"extraitCasierJudiciaire\":false,\"dateReceptionCasierJudiciaire\":null,\"nomRecruteur\":\"\",\"dateRecrutement\":null,\"avisRecrutement\":null,\"visiteDdcs\":false,\"dateVisiteDdcs\":null,\"avisDdcs\":null,\"remarque\":null,\"informationsHabitation\":{},\"informationsVehicule\":{},\"dateRadiation\":null,\"candidature\":false,\"archivee\":false}]}}");
        GetRequest getRequestMock = Mockito.mock(GetRequest.class);
        HttpResponse<JsonNode> responseMock = Mockito.mock(HttpResponse.class);

        Mockito.when(getRequestMock.header(Mockito.anyString(), Mockito.anyString())).thenReturn(getRequestMock);
        Mockito.when(getRequestMock.queryString(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(getRequestMock);
        Mockito.when(getRequestMock.queryString(Mockito.anyString(), Mockito.anyString())).thenReturn(getRequestMock);
        Mockito.when(getRequestMock.asJson()).thenReturn(responseMock);
        Mockito.when(responseMock.getBody()).thenReturn(node);

        unirestMock.when(() -> Unirest.get(Mockito.anyString())).thenReturn(getRequestMock);
    }

}
