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
    private final RestTemplate spoonacularRestTemplate;
    private final RestTemplate spoonacularRestTemplateForRetry;
    private final SpoonacularClientConfiguration configuration;

    private final Logger logger = LoggerFactory.getLogger(SpoonacularClient.class);

    public SpoonacularClient(RestTemplate spoonacularRestTemplate, RestTemplate spoonacularRestTemplateForRetry, SpoonacularClientConfiguration configuration) {
        this.spoonacularRestTemplate = spoonacularRestTemplate;
        this.spoonacularRestTemplateForRetry = spoonacularRestTemplateForRetry;
        this.configuration = configuration;
    }

    public SearchResults findIngredientsByName(String name) {
        String uriString = UriComponentsBuilder.fromUriString(configuration.getUrl())
                .path(configuration.getSearchPath())
                .queryParam("query", name)
                .queryParam("number", configuration.getSearchLimit())
                .queryParam("sort", "calories")
                .queryParam("sortDirection", "desc")
                .queryParam("apiKey", configuration.getApiKey())
                .build()
                .toUriString();

        try {
            return spoonacularRestTemplate.getForObject(uriString, SearchResults.class);
        } catch (Exception ex) {
            try {
                logger.warn("Retry request for ingredients by name. Exception for first request cause", ex.getCause());
                return spoonacularRestTemplateForRetry.getForObject(uriString, SearchResults.class);
            } catch (RestClientException retryEx) {
                logger.error("Retry request for ingredients by name failed. Cause = ", retryEx.getCause());
                return new SearchResults(List.of());
            }
        }
    }

    public SpoonacularIngredientInformation getIngredientInformationById(Long id) {
        String uriString = UriComponentsBuilder.fromUriString(configuration.getUrl())
                .path(String.format(configuration.getDetailsPath(), id))
                .queryParam("amount", 100)
                .queryParam("unit", "gram")
                .queryParam("apiKey", configuration.getApiKey())
                .build()
                .toUriString();

        try {
            return spoonacularRestTemplate.getForObject(uriString, SpoonacularIngredientInformation.class);
        } catch (RestClientException ex) {
            try {
                logger.warn("Retry request for ingredient information. Exception for first request cause", ex.getCause());
                return spoonacularRestTemplateForRetry.getForObject(uriString, SpoonacularIngredientInformation.class);
            } catch (RestClientException retryEx) {
                logger.error("Retry request for ingredient information failed. Cause", retryEx.getCause());
                return null;
            }
        }
    }

}
