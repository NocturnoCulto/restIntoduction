package pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular.model.SpoonacularIngredientInformation;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SpoonacularCacheConfiguration {

    // należy wczytać potrzebne wartości z pliku konfiguracyjnego i stworzyć instancję cacha

    public SpoonacularCacheConfiguration() {
    }

    public LoadingCache<Long, SpoonacularIngredientInformation> ingredientsInformationCache() {
        return null;
    }

}
