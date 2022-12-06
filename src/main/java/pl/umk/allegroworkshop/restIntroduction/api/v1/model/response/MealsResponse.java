package pl.umk.allegroworkshop.restIntroduction.api.v1.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MealsResponse {
    private List<MealDTO> meals;

    @JsonCreator
    public MealsResponse(@JsonProperty("meals") List<MealDTO> meals) {
        this.meals = meals;
    }

    public List<MealDTO> getMeals() {
        return meals;
    }

    public void setMeals(List<MealDTO> meals) {
        this.meals = meals;
    }
}
