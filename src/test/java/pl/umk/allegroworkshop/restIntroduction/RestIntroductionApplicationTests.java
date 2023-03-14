package pl.umk.allegroworkshop.restIntroduction;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.umk.allegroworkshop.restIntroduction.api.v1.MealsApi;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.MealsResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestIntroductionApplicationTests extends BaseTest {

    @Test
    void shouldReturnAllMeals() throws Exception {
        //given:
        String uri = "/getMeals";

        //when:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MealsApi.apiVersionAccept)).andReturn();

        //then:
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        MealsResponse meals = super.mapFromJson(content, MealsResponse.class);
        assertEquals(4, meals.getMeals().size());
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
