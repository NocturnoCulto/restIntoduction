package pl.umk.allegroworkshop.restIntroduction;

import org.junit.jupiter.api.Test;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.BooksResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescriptionStoreIntegrationTests extends BaseTest {

    @Test
    void shouldReturnDescription() throws Exception {
        //given:
        String uri = "/book/123";

        //when:
        BooksResponse booksResponse = getResponseForUri(uri, BooksResponse.class);

        //then:
        assertEquals(booksResponse.getBooks().get(0).getId(), 123);
        assertEquals(booksResponse.getBooks().get(0).getDescription().shortDescription(), "Short text description");
        assertEquals(booksResponse.getBooks().get(0).getDescription().longDescription(), "Long text description");

        wireMockServer.verify(getRequestedFor(urlPathEqualTo("/descriptionById/123")));
    }

    @Test
    void shouldCachedDescription() throws Exception {
        //given:
        String uri = "/book/123";

        //when:
        for (int i = 0; i < 10; i++) {
            getResponseForUri(uri, BooksResponse.class);
        }
        BooksResponse booksResponse = getResponseForUri(uri, BooksResponse.class);

        //then:
        assertEquals(booksResponse.getBooks().get(0).getId(), 123);
        assertEquals(booksResponse.getBooks().get(0).getDescription().shortDescription(), "Short text description");
        assertEquals(booksResponse.getBooks().get(0).getDescription().longDescription(), "Long text description");

        wireMockServer.verify(1, getRequestedFor(urlPathEqualTo("/descriptionById/123")));
    }

    @Test
    void shouldCatchDescriptionStoreExceptionsAndRetryRequest() throws Exception {
        //given:
        wireMockServer.resetAll();
        stubDescriptionStoreFailedRequest();
        String uri = "/book/123";

        //when:
        BooksResponse booksResponse = getResponseForUri(uri, BooksResponse.class);

        //then:
        assertEquals(null, booksResponse.getBooks().get(0).getDescription());
        wireMockServer.verify(2, getRequestedFor(urlPathEqualTo("/descriptionById/123")));
    }

    @Test
    void shouldRetryDescriptionStoreAndReturnResponse() throws Exception {
        assertEquals(true, false);
    }

}
