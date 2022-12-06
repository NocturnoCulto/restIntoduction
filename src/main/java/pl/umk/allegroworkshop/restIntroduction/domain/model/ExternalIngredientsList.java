package pl.umk.allegroworkshop.restIntroduction.domain.model;

import java.util.List;

public class ExternalIngredientsList {
    private final List<ExternalIngredient> externalIngredientsList;

    public ExternalIngredientsList(List<ExternalIngredient> externalIngredientsList) {
        this.externalIngredientsList = externalIngredientsList;
    }

    public List<ExternalIngredient> getExternalIngredientsList() {
        return externalIngredientsList;
    }
}
