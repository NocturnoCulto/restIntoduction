package pl.umk.allegroworkshop.restIntroduction.api.v1.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MealDTO {

    private final String id;
    private final String name;
    private final Integer calories;
    private final List<IngredientDTO> ingredients;

    private final LocalDateTime createdAt;


    @JsonCreator
    public MealDTO(@JsonProperty("id") String id,
                   @JsonProperty("name") String name,
                   @JsonProperty("calories") Integer calories,
                   @JsonProperty("ingredients") List<IngredientDTO> ingredients,
                   @JsonProperty("createdAt") LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.ingredients = ingredients;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCalories() {
        return calories;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
