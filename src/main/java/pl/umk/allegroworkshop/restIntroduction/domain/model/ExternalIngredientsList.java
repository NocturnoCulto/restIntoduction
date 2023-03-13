package pl.umk.allegroworkshop.restIntroduction.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExternalIngredientsList {
    private final List<ExternalIngredient> externalIngredientsList;

    @JsonCreator
    public ExternalIngredientsList(@JsonProperty("externalIngredientsList") List<ExternalIngredient> externalIngredientsList) {
        this.externalIngredientsList = externalIngredientsList;
    }

    public List<ExternalIngredient> getExternalIngredientsList() {
        return externalIngredientsList;
    }
}
