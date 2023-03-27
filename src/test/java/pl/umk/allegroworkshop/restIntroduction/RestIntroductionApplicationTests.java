package pl.umk.allegroworkshop.restIntroduction;

import org.junit.jupiter.api.Test;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.MealsResponse;
import pl.umk.allegroworkshop.restIntroduction.domain.model.ExternalIngredientsList;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RestIntroductionApplicationTests extends BaseTest {

    @Test
    void shouldReturnAllMeals() throws Exception {
        //given:
        String uri = "/getMeals";

        //when:
        MealsResponse response = getResponseForUri(uri, MealsResponse.class);

        //then:
        assertEquals(4, response.getMeals().size());
    }

    @Test
    void shouldSearchIngredientsByName() throws Exception {
        //given:
        String uri = "/searchIngredients?name=banana";

        //when:
        ExternalIngredientsList response = getResponseForUri(uri, ExternalIngredientsList.class);

        //then:

        assertEquals(5, response.getExternalIngredientsList().size());
        wireMockServer.verify(getRequestedFor(urlPathEqualTo("/food/ingredients/search"))
                .withQueryParam("query", equalTo("banana"))
                .withQueryParam("number", equalTo("20"))
                .withQueryParam("sort", equalTo("calories"))
                .withQueryParam("sortDirection", equalTo("desc"))
                .withQueryParam("apiKey", equalTo("apiKey")));
    }

    @Test
    void shouldSearchIngredientsDetails() throws Exception {
        assertEquals(true, false);

    }

    @Test
    void shouldCachedIngredientsDetails() throws Exception {
        assertEquals(true, false);

    }

    @Test
    void shouldCatchSpoonacularExceptions() throws Exception {
        assertEquals(true, false);

    }

}
