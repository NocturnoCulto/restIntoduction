package pl.umk.allegroworkshop.restIntroduction.domain.model;

public class Ingredient {
    private final String name;
    private final Integer calories;

    public Ingredient(String name, Integer calories) {
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
