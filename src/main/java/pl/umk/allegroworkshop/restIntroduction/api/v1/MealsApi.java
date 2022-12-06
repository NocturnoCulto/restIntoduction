package pl.umk.allegroworkshop.restIntroduction.api.v1;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.InputMeal;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.MealsResponse;
import pl.umk.allegroworkshop.restIntroduction.domain.model.ExternalIngredientDetails;
import pl.umk.allegroworkshop.restIntroduction.domain.model.ExternalIngredientsList;

import java.util.Objects;

@RestController()
public class MealsApi {
    private final MealsProvider mealsProvider;
    public static final String apiVersionAccept = "application/v1+json";

    public MealsApi(MealsProvider mealsProvider) {
        this.mealsProvider = mealsProvider;
    }

    // getMeals
    public ResponseEntity<MealsResponse> getAllMeals() {
        return null;
    }

    // getMeal/{id}
    public ResponseEntity<MealsResponse> getMealById() {
        return null;
    }

    // addMeal - przyjmuje json w modelu InputMeal
    public ResponseEntity<MealsResponse> addMeal() {
        return null;
    }

    // searchIngredients by name
    public ResponseEntity<ExternalIngredientsList> searchIngredients() {
        return null;
    }

    // ingredientDetails by id
    public ResponseEntity<ExternalIngredientDetails> searchDetails() {
        return null;
    }
}
