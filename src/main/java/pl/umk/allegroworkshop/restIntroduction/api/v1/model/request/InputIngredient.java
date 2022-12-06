package pl.umk.allegroworkshop.restIntroduction.api.v1.model.request;

public class InputIngredient {
    private final String name;
    private final Integer calories;

    public InputIngredient(String name, Integer calories) {
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
