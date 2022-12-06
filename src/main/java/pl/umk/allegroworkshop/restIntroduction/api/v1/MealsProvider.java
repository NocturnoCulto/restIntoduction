package pl.umk.allegroworkshop.restIntroduction.api.v1;

import org.springframework.stereotype.Component;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.InputMeal;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.IngredientDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.MealDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.MealsResponse;
import pl.umk.allegroworkshop.restIntroduction.domain.mealService.MealService;
import pl.umk.allegroworkshop.restIntroduction.domain.model.*;
import pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular.SpoonacularService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class MealsProvider {
    private final MealService mealService;
    private final SpoonacularService spoonacularService;

    public MealsProvider(MealService mealService, SpoonacularService spoonacularService) {
        this.mealService = mealService;
        this.spoonacularService = spoonacularService;
    }

    public MealsResponse getAllMeals() {
        return mapMealsToResponse(mealService.getAllMeals());
    }

    public MealsResponse getMealById(String id) {
        return new MealsResponse(Stream.of(mapMealToMealDTO(mealService.getMealById(id))).filter(Objects::nonNull).toList());
    }

    public MealsResponse addMeal(InputMeal inputMeal) {
        return new MealsResponse(Stream.of(mapMealToMealDTO(mealService.addMeal(new MealToAdd(inputMeal.getName(),
                inputMeal.getIngredients().stream().map(inputIngredient -> new Ingredient(inputIngredient.getName(), inputIngredient.getCalories())).toList())))).toList());
    }

    public ExternalIngredientsList getIngredientsListByName(String name) {
        return spoonacularService.getIngredientsByName(name);
    }

    public ExternalIngredientDetails getExternalIngredientsDetails(Long id) {
        return spoonacularService.getExternalIngredientDetails(id);
    }

    private MealsResponse mapMealsToResponse(List<Meal> meals) {
        return new MealsResponse(meals.stream().map(this::mapMealToMealDTO).toList());
    }

    private MealDTO mapMealToMealDTO(Meal meal) {
        if (meal == null) return null;
        return new MealDTO(meal.getId(), meal.getName(), meal.getCalories(), meal.getIngredients().stream().map(ingredient -> new IngredientDTO(ingredient.getName(), ingredient.getCalories())).toList(), meal.getCreatedAt());
    }

}
