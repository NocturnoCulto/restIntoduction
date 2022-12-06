package pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular.model.SearchResults;
import pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular.model.SpoonacularIngredientInformation;

import java.util.List;

@Component
public class SpoonacularClient {

    private final Logger logger = LoggerFactory.getLogger(SpoonacularClient.class);

    public SpoonacularClient(
    ) {
    }

    public SearchResults findIngredientsByName(String name) {
        String uriString = UriComponentsBuilder.fromUriString("url z konfiguracji")
                .path("ścieżka z konfiguracji")
                .queryParam("query", name)
                .queryParam("number", "limit")
                .queryParam("sort", "calories")
                .queryParam("sortDirection", "desc")
                .queryParam("apiKey", "key")
                .build()
                .toUriString();

        try {
            return null;
        } catch (Exception ex) {
            try {
                logger.warn("Retry request for ingredients by name. Exception for first request cause = {}", ex.getCause().toString());
                return null;
            } catch (RestClientException retryEx) {
                logger.error("Retry request for ingredients by name failed. Cause = {}", retryEx.getCause().toString());
                return new SearchResults(List.of());
            }
        }
    }

    public SpoonacularIngredientInformation getIngredientInformationById(Long id) {
        String uriString = UriComponentsBuilder.fromUriString("")
                .path(String.format("url/%s", id))
                .queryParam("amount", 100)
                .queryParam("unit", "gram")
                .queryParam("apiKey", "key")
                .build()
                .toUriString();

        try {
            return null;
        } catch (RestClientException ex) {
            try {
                logger.warn("Retry request for ingredient information. Exception for first request cause = {}", ex.getCause().toString());
                return null;
            } catch (RestClientException retryEx) {
                logger.error("Retry request for ingredient information failed. Cause = {}", retryEx.getCause().toString());
                return null;
            }
        }
    }

}
