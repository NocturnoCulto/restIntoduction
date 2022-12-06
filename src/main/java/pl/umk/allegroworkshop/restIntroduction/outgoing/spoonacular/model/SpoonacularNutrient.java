package pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpoonacularNutrient {
    private final String title;
    private final String name;
    private final Float amount;
    private final String unit;

    @JsonCreator
    public SpoonacularNutrient(
            @JsonProperty("title") String title,
            @JsonProperty("name") String name,
            @JsonProperty("amount") Float amount,
            @JsonProperty("unit") String unit) {
        this.title = title;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public Float getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }
}
