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

    @GetMapping(value = "/getMeals", produces = apiVersionAccept)
    public ResponseEntity<MealsResponse> getAllMeals(@RequestHeader HttpHeaders headers) {
        if (!Objects.equals(headers.getFirst("accept"), apiVersionAccept)) return ResponseEntity.status(406).body(null);
        return ResponseEntity
                .status(200)
                .body(mealsProvider.getAllMeals());
    }

    @GetMapping(value = "/getMeal/{id}", produces = apiVersionAccept)
    public ResponseEntity<MealsResponse> getMealById(@PathVariable String id) {
        return ResponseEntity
                .status(200)
                .body(mealsProvider.getMealById(id));
    }

    @PutMapping(value = "/addMeal", consumes = MediaType.APPLICATION_JSON_VALUE, produces = apiVersionAccept)
    public ResponseEntity<MealsResponse> addMeal(@RequestBody InputMeal inputMeal) {
        return ResponseEntity
                .status(201)
                .body(mealsProvider.addMeal(inputMeal));
    }

    @GetMapping(value = "/searchIngredients", produces = apiVersionAccept)
    public ResponseEntity<ExternalIngredientsList> searchIngredients(@RequestParam("name") String name) {
        return ResponseEntity
                .status(200)
                .body(mealsProvider.getIngredientsListByName(name));
    }

    @GetMapping(value = "/ingredientDetails", produces = apiVersionAccept)
    public ResponseEntity<ExternalIngredientDetails> searchDetails(@RequestParam("id") Long id) {
        return ResponseEntity
                .status(200)
                .body(mealsProvider.getExternalIngredientsDetails(id));
    }
}
