package pl.umk.allegroworkshop.restIntroduction;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.umk.allegroworkshop.restIntroduction.api.v1.MealsApi;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("integration")
@SpringBootTest(classes = RestIntroductionApplication.class)
@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    WireMockServer wireMockServer = null; // You need to initialize wireMock server on specific port

    @BeforeAll
    void startWireMock() {
        // start wireMock server
    }

    @BeforeEach
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // implement wireMock stubs
    }

    @AfterEach
    protected void clearAfterEach() {
        // You need to reset all wireMock mappings
    }

    @AfterAll
    void stopWireMock() {
        // stop wireMock server
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, clazz);
    }

    protected <T> T getResponseForUri(String uri, Class<T> responseClazz) throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MealsApi.apiVersionAccept)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        return mapFromJson(content, responseClazz);
    }
}
