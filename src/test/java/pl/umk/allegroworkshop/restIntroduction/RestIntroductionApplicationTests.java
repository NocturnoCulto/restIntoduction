package pl.umk.allegroworkshop.restIntroduction;

import org.junit.jupiter.api.Test;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.MealsResponse;

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
        assertEquals(true, false);
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
