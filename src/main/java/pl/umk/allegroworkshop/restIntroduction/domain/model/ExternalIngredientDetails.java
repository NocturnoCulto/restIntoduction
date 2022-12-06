package pl.umk.allegroworkshop.restIntroduction.domain.model;

import java.util.List;

public class ExternalIngredientDetails {
    private final Long id;
    private final String name;
    private final Integer amount;
    private final String unit;
    private final List<String> possibleUnits;
    private final Float calories;

    public ExternalIngredientDetails(Long id, String name, Integer amount, String unit, List<String> possibleUnits, Float calories) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.possibleUnits = possibleUnits;
        this.calories = calories;
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

    public Float getCalories() {
        return calories;
    }
}
