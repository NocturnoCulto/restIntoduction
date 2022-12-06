package pl.umk.allegroworkshop.restIntroduction.repository;

import org.springframework.stereotype.Repository;
import pl.umk.allegroworkshop.restIntroduction.domain.model.Ingredient;
import pl.umk.allegroworkshop.restIntroduction.domain.model.Meal;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class InMemoryMealsRepository implements MealsRepository {

    private final Map<String, Meal> mealsMap = new HashMap<>();

    @Override
    public List<Meal> getAllMeals() {
        return mealsMap.values().stream().toList();
    }

    @Override
    public Meal getMealById(String id) {
        return mealsMap.get(id);
    }

    @Override
    public Meal insertMeal(Meal meal) {
        mealsMap.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public String deleteMeal(String id) {
        mealsMap.remove(id);
        return id;
    }

    @Override
    public Meal updateMeal(String id, Meal newMeal) {
        return null;
    }

    @PostConstruct
    void fillMealsListForExamples() {
        mealsMap.put("125", new Meal("125", "Breakfast", 300, List.of(new Ingredient("płatki", 250), new Ingredient("kawa", 50)), LocalDateTime.now()));
        mealsMap.put("130", new Meal("130", "Lunch", 500, List.of(new Ingredient("omlet", 500)), LocalDateTime.now()));
        mealsMap.put("135", new Meal("135", "Dinner", 700, List.of(new Ingredient("schabowy", 400), new Ingredient("ziemniaki", 300)), LocalDateTime.now()));
        mealsMap.put("140", new Meal("140", "Supper", 300, List.of(new Ingredient("kanapka", 300)), LocalDateTime.now()));
    }

}
