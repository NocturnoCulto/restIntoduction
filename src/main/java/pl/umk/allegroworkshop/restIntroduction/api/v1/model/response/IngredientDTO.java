package pl.umk.allegroworkshop.restIntroduction.api.v1.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class IngredientDTO {
    private final String name;
    private final Integer calories;

    @JsonCreator
    public IngredientDTO(@JsonProperty("name") String name,
                         @JsonProperty("calories") Integer calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public Integer getCalories() {
        return calories;
    }
}
