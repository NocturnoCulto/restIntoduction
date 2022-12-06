package pl.umk.allegroworkshop.restIntroduction.domain.model;

import java.util.List;

public class MealToAdd {
    private final String name;
    private final List<Ingredient> ingredients;

    public MealToAdd(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
