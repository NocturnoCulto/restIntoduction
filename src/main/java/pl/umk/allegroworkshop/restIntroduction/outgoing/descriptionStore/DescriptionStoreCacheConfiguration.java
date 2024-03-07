package pl.umk.allegroworkshop.restIntroduction.outgoing.descriptionStore;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.umk.allegroworkshop.restIntroduction.outgoing.descriptionStore.model.ExternalDescription;

import java.util.concurrent.TimeUnit;

@Configuration
public class DescriptionStoreCacheConfiguration {
    private final Long descriptionStoreCacheSize;
    private final Long refreshAfterWrite;
    private final Long expireAfterWrite;
    private final DescriptionStoreClient descriptionStoreClient;

    public DescriptionStoreCacheConfiguration(@Value("${description_store.cache.size}") Long descriptionStoreCacheSize,
                                              @Value("${description_store.cache.refresh_after_write}") Long refreshAfterWrite,
                                              @Value("${description_store.cache.expire_after_write}") Long expireAfterWrite,
                                              DescriptionStoreClient descriptionStoreClient) {
        this.descriptionStoreCacheSize = descriptionStoreCacheSize;
        this.refreshAfterWrite = refreshAfterWrite;
        this.expireAfterWrite = expireAfterWrite;
        this.descriptionStoreClient = descriptionStoreClient;
    }

    @Bean
    public LoadingCache<String, ExternalDescription> descriptionStoreCache() {
        return Caffeine.newBuilder()
                .maximumSize(descriptionStoreCacheSize)
                .refreshAfterWrite(refreshAfterWrite, TimeUnit.SECONDS)
                .expireAfterWrite(expireAfterWrite, TimeUnit.SECONDS)
                .build(descriptionStoreClient::findDescriptionById);
    }

    public Long getDescriptionStoreCacheSize() {
        return descriptionStoreCacheSize;
    }

    public Long getRefreshAfterWrite() {
        return refreshAfterWrite;
    }

    public Long getExpireAfterWrite() {
        return expireAfterWrite;
    }
}
