package pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

public class SpoonacularClientConfiguration {

    // należy wczytać odpowiednie wartości z pliku konfiguracyjnego

    public SpoonacularClientConfiguration() {

    }

    //należy stworzyć dwie instancje restTemplate

    public RestTemplate spoonacularRestTemplate() {
        return null;
    }

    public RestTemplate spoonacularRestTemplateForRetry() {
        return null;
    }
}
