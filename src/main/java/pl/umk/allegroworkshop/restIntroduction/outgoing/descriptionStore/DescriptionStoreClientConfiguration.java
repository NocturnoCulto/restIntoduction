package pl.umk.allegroworkshop.restIntroduction.outgoing.descriptionStore;

import org.springframework.beans.factory.annotation.Value;

public class DescriptionStoreClientConfiguration {
    private final String url;
    private final String path;
    private final Long connectTimeout;
    private final Long readTimeout;
    private final Long retryReadTimeout;

    public DescriptionStoreClientConfiguration(String url,
                                               String path,
                                               Long connectTimeout,
                                               Long readTimeout,
                                               Long retryReadTimeout) {
        this.url = url;
        this.path = path;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.retryReadTimeout = retryReadTimeout;
    }

}
