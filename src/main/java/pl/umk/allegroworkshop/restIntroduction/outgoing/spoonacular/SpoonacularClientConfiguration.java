package pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class SpoonacularClientConfiguration {
    private final String apiKey;
    private final String url;
    private final String searchPath;
    private final Integer searchLimit;
    private final String detailsPath;
    private final Long connectTimeout;
    private final Long readTimeout;
    private final Long retryReadTimeout;

    public SpoonacularClientConfiguration(@Value("${spoonacular.api_key}") String apiKey,
                                          @Value("${spoonacular.url}") String url,
                                          @Value("${spoonacular.search_path}") String searchPath,
                                          @Value("${spoonacular.search_limit}") Integer searchLimit,
                                          @Value("${spoonacular.details_path}") String detailsPath,
                                          @Value("${spoonacular.connect_timeout}") Long connectTimeout,
                                          @Value("${spoonacular.read_timeout}") Long readTimeout,
                                          @Value("${spoonacular.retry_read_timeout}") Long retryReadTimeout) {
        this.apiKey = apiKey;
        this.url = url;
        this.searchPath = searchPath;
        this.searchLimit = searchLimit;
        this.detailsPath = detailsPath;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.retryReadTimeout = retryReadTimeout;
    }

    @Bean
    public RestTemplate spoonacularRestTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    public RestTemplate spoonacularRestTemplateForRetry() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(retryReadTimeout))
                .build();
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUrl() {
        return url;
    }

    public String getSearchPath() {
        return searchPath;
    }

    public Integer getSearchLimit() {
        return searchLimit;
    }

    public String getDetailsPath() {
        return detailsPath;
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
