package pl.umk.allegroworkshop.restIntroduction;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.umk.allegroworkshop.restIntroduction.api.v1.MealsApi;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.*;
import pl.umk.allegroworkshop.restIntroduction.domain.model.ExternalIngredientsList;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

class RestIntroductionApplicationTests extends BaseTest {

	@Test
	void getAllBooksEndpoint() throws Exception {
		String uri = "/getMeals";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MealsApi.apiVersionAccept)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		MealsResponse meals = super.mapFromJson(content, MealsResponse.class);
		assertTrue(meals.getMeals() .size() > 0);
	}

	@Test
	void getOneBookEndpoint() throws Exception {
		String uri = "/getMeal/125";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MealsApi.apiVersionAccept)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		MealsResponse meals = super.mapFromJson(content, MealsResponse.class);
		assertEquals(1, meals.getMeals().size());
	}

	@Test
	void getFromService() throws Exception {
		String uri = "/searchIngredients?name=banana";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MealsApi.apiVersionAccept)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		ExternalIngredientsList meals = super.mapFromJson(content, ExternalIngredientsList.class);
		assertEquals(5, meals.getExternalIngredientsList().size());
		wireMockServer.verify(1, getRequestedFor(urlPathEqualTo("/food/ingredients/search")).withQueryParam("query", equalTo("banana")));
	}
}
