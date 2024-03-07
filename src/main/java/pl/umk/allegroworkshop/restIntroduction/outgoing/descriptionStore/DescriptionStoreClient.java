package pl.umk.allegroworkshop.restIntroduction.outgoing.descriptionStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.umk.allegroworkshop.restIntroduction.outgoing.descriptionStore.model.ExternalDescription;

@Component
public class DescriptionStoreClient {
    private final RestTemplate descriptionStoreRestTemplate;
    private final RestTemplate descriptionStoreRestTemplateForRetry;
    private final DescriptionStoreClientConfiguration configuration;

    private final Logger logger = LoggerFactory.getLogger(DescriptionStoreClient.class);

    public DescriptionStoreClient(RestTemplate descriptionStoreRestTemplate, RestTemplate descriptionStoreRestTemplateForRetry, DescriptionStoreClientConfiguration configuration) {
        this.descriptionStoreRestTemplate = descriptionStoreRestTemplate;
        this.descriptionStoreRestTemplateForRetry = descriptionStoreRestTemplateForRetry;
        this.configuration = configuration;
    }

    public ExternalDescription findDescriptionById(String id) {
        String uriString = UriComponentsBuilder.fromUriString(configuration.getUrl())
                .path(String.format(configuration.getPath(), id))
                .build()
                .toUriString();

        try {
            return descriptionStoreRestTemplate.getForObject(uriString, ExternalDescription.class);
        } catch (Exception ex) {
            try {
                logger.warn("Retry request for description id={}. Exception for first fail = {}", id, ex.getMessage());
                return descriptionStoreRestTemplateForRetry.getForObject(uriString, ExternalDescription.class);
            } catch (Exception ex2) {
                logger.error("Second request for description id={} failed. Exception = {}", id, ex.getMessage());
                return null;
            }
        }
    }
}