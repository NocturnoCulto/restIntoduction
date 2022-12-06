package pl.umk.allegroworkshop.restIntroduction.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class Meal {
    private final String id;
    private final String name;
    private final Integer calories;
    private final List<Ingredient> ingredients;
    private final LocalDateTime createdAt;

    public Meal(String id, String name, Integer calories, List<Ingredient> ingredients, LocalDateTime createdAt) {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
