package pl.umk.allegroworkshop.restIntroduction;

import org.junit.jupiter.api.Test;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.MealsResponse;
import pl.umk.allegroworkshop.restIntroduction.domain.model.ExternalIngredientDetails;
import pl.umk.allegroworkshop.restIntroduction.domain.model.ExternalIngredientsList;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RestIntroductionApplicationTests extends BaseTest {

    @Test
    void shouldReturnAllMeals() throws Exception {
        // given:
        String uri = "/getMeals";

        // when:
        MealsResponse response = getResponseForUri(uri, MealsResponse.class);

        // then:
        assertEquals(4, response.getMeals().size());
    }

    @Test
    void shouldSearchIngredientsByName() throws Exception {
        // given:
        String uri = "/searchIngredients?name=banana";

        // when:
        ExternalIngredientsList response = getResponseForUri(uri, ExternalIngredientsList.class);

        // then:

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
        // given:
        String uri = "/ingredientDetails?id=1";

        // when:
        ExternalIngredientDetails response = getResponseForUri(uri, ExternalIngredientDetails.class);

        // then:
        assertEquals(1, response.getId());
        assertEquals("banana chips", response.getName());
        assertEquals(100, response.getAmount());
        assertEquals(5, response.getPossibleUnits().size());
        assertEquals(519, response.getCalories());

        wireMockServer.verify(getRequestedFor(urlPathEqualTo("/food/ingredients/1/information"))
                .withQueryParam("amount", equalTo("100"))
                .withQueryParam("unit", equalTo("gram"))
                .withQueryParam("apiKey", equalTo("apiKey")));
    }

    @Test
    void shouldCachedIngredientsDetails() throws Exception {
        // given:
        String uri = "/ingredientDetails?id=1";

        // when:
        for (int i = 0; i < 10; i++) {
            getResponseForUri(uri, ExternalIngredientDetails.class);
        }
        ExternalIngredientDetails response = getResponseForUri(uri, ExternalIngredientDetails.class);

        // then:
        assertEquals(1, response.getId());
        assertEquals("banana chips", response.getName());
        assertEquals(100, response.getAmount());
        assertEquals(5, response.getPossibleUnits().size());
        assertEquals(519, response.getCalories());

        wireMockServer.verify(1, getRequestedFor(urlPathEqualTo("/food/ingredients/1/information"))
                .withQueryParam("amount", equalTo("100"))
                .withQueryParam("unit", equalTo("gram"))
                .withQueryParam("apiKey", equalTo("apiKey")));
    }

    @Test
    void shouldCatchSpoonacularExceptionsAndRetryRequest() throws Exception {
        //given:
        wireMockServer.resetAll();
        stubSpoonacularFailedRequest();
        String uri = "/searchIngredients?name=banana";

        //when:
        ExternalIngredientsList response = getResponseForUri(uri, ExternalIngredientsList.class);

        //then:
        assertEquals(0, response.getExternalIngredientsList().size());
        wireMockServer.verify(2, getRequestedFor(urlPathEqualTo("/food/ingredients/search")));
    }

    @Test
    void shouldRetrySpoonacularServiceAndReturnResponse() throws Exception {
        // given:
        stubFirstRequestFailedScenario();
        String uri = "/searchIngredients?name=apple";

        // when:
        ExternalIngredientsList response = getResponseForUri(uri, ExternalIngredientsList.class);

        // then:

        assertEquals(5, response.getExternalIngredientsList().size());
        assertEquals("apple 1", response.getExternalIngredientsList().get(0).getName());

        wireMockServer.verify(2, getRequestedFor(urlPathEqualTo("/food/ingredients/search"))
                .withQueryParam("query", equalTo("apple"))
                .withQueryParam("number", equalTo("20"))
                .withQueryParam("sort", equalTo("calories"))
                .withQueryParam("sortDirection", equalTo("desc"))
                .withQueryParam("apiKey", equalTo("apiKey")));
    }

}
