package pl.umk.allegroworkshop.restIntroduction.api.v1.model.request;

import java.util.List;

public class InputMeal {
    private String name;
    private List<InputIngredient> ingredients;

    public InputMeal(String name, List<InputIngredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<InputIngredient> getIngredients() {
        return ingredients;
    }
}
