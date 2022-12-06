package pl.umk.allegroworkshop.restIntroduction.repository;

import pl.umk.allegroworkshop.restIntroduction.domain.model.Meal;

import java.util.List;

public interface MealsRepository {

    List<Meal> getAllMeals();
    Meal getMealById(String id);

    Meal insertMeal(Meal meal);

    String deleteMeal(String id);

    Meal updateMeal(String id, Meal newMeal);

}
