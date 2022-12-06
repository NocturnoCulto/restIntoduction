package pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpoonacularNutrition {
    private final List<SpoonacularNutrient> nutrients;

    @JsonCreator
    public SpoonacularNutrition(@JsonProperty("nutrients") List<SpoonacularNutrient> nutrients) {
        this.nutrients = nutrients;
    }

    public List<SpoonacularNutrient> getNutrients() {
        return nutrients;
    }
}
