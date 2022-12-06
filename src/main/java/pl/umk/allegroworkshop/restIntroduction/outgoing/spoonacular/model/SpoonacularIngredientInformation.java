package pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SpoonacularIngredientInformation {
    private final Long id;
    private final String name;
    private final Integer amount;
    private final String unit;
    private final List<String> possibleUnits;
    private final SpoonacularNutrition nutrition;

    @JsonCreator
    public SpoonacularIngredientInformation(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("amount") Integer amount,
            @JsonProperty("unit") String unit,
            @JsonProperty("possibleUnits") List<String> possibleUnits,
            @JsonProperty("nutrion") SpoonacularNutrition nutrition) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.possibleUnits = possibleUnits;
        this.nutrition = nutrition;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public List<String> getPossibleUnits() {
        return possibleUnits;
    }

    public SpoonacularNutrition getNutrition() {
        return nutrition;
    }
}
