package pl.umk.allegroworkshop.restIntroduction.outgoing.descriptionStore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class DescriptionStoreClientConfiguration {
    private final String url;
    private final String path;
    private final Long connectTimeout;
    private final Long readTimeout;
    private final Long retryReadTimeout;

    public DescriptionStoreClientConfiguration(@Value("${description_store.url}") String url,
                                               @Value("${description_store.path}") String path,
                                               @Value("${description_store.connect_timeout}") Long connectTimeout,
                                               @Value("${description_store.read_timeout}") Long readTimeout,
                                               @Value("${description_store.retry_read_timeout}") Long retryReadTimeout) {
        this.url = url;
        this.path = path;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.retryReadTimeout = retryReadTimeout;
    }

    @Bean
    public RestTemplate descriptionStoreRestTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    public RestTemplate descriptionStoreRestTemplateForRetry() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(retryReadTimeout))
                .build();
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public Long getConnectTimeout() {
        return connectTimeout;
    }

    public Long getReadTimeout() {
        return readTimeout;
    }

    public Long getRetryReadTimeout() {
        return retryReadTimeout;
    }
}
