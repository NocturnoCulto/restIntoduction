package pl.umk.allegroworkshop.restIntroduction.domain.mealService;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import pl.umk.allegroworkshop.restIntroduction.domain.model.Ingredient;
import pl.umk.allegroworkshop.restIntroduction.domain.model.Meal;
import pl.umk.allegroworkshop.restIntroduction.domain.model.MealToAdd;
import pl.umk.allegroworkshop.restIntroduction.repository.MealsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MealService {
    private final MealsRepository mealsRepository;

    public MealService(MealsRepository mealsRepository) {
        this.mealsRepository = mealsRepository;
    }

    public List<Meal> getAllMeals() {
        return mealsRepository.getAllMeals();
    }

    public Meal getMealById(String id) {
        return mealsRepository.getMealById(id);
    }

    public Meal addMeal(MealToAdd mealToAdd) {
        Integer caloriesSum = mealToAdd.getIngredients().stream().map(Ingredient::getCalories).mapToInt(Integer::intValue).sum();
        return mealsRepository.insertMeal(new Meal(RandomStringUtils.random(8, true, true), mealToAdd.getName(), caloriesSum, mealToAdd.getIngredients(), LocalDateTime.now()));
    }

    public String removeMeal(String idToRemove) {
        return mealsRepository.deleteMeal(idToRemove);
    }

    public Meal updateMeal(String mealToUpdate, Meal updatedMeal) {
        return mealsRepository.updateMeal(mealToUpdate, updatedMeal);
    }
}
