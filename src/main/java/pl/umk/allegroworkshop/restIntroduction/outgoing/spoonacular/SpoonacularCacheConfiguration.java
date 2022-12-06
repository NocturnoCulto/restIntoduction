package pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular.model.SpoonacularIngredientInformation;

import java.util.concurrent.TimeUnit;

@Configuration
public class SpoonacularCacheConfiguration {
    private final Long spoonacularIngredientsInformationCacheSize;
    private final Long refreshAfterWrite;
    private final Long expireAfterWrite;
    private final SpoonacularClient spoonacularClient;

    public SpoonacularCacheConfiguration(@Value("${spoonacular.cache.ingredients_information_size}") Long spoonacularIngredientsInformationCacheSize,
                                         @Value("${spoonacular.cache.refres_after_write}") Long refreshAfterWrite,
                                         @Value("${spoonacular.cache.expire_after_write}") Long expireAfterWrite,
                                         SpoonacularClient spoonacularClient) {
        this.spoonacularIngredientsInformationCacheSize = spoonacularIngredientsInformationCacheSize;
        this.refreshAfterWrite = refreshAfterWrite;
        this.expireAfterWrite = expireAfterWrite;
        this.spoonacularClient = spoonacularClient;
    }

    @Bean
    public LoadingCache<Long, SpoonacularIngredientInformation> ingredientsInformationCache() {
        return Caffeine.newBuilder()
                .maximumSize(spoonacularIngredientsInformationCacheSize)
                .refreshAfterWrite(refreshAfterWrite, TimeUnit.SECONDS)
                .expireAfterWrite(expireAfterWrite, TimeUnit.SECONDS)
                .build(spoonacularClient::getIngredientInformationById);
    }

    public Long getSpoonacularIngredientsInformationCacheSize() {
        return spoonacularIngredientsInformationCacheSize;
    }

    public Long getRefreshAfterWrite() {
        return refreshAfterWrite;
    }

    public Long getExpireAfterWrite() {
        return expireAfterWrite;
    }

    public SpoonacularClient getSpoonacularClient() {
        return spoonacularClient;
    }
}
