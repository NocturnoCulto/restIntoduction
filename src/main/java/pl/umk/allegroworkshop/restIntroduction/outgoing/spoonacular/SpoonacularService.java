package pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular;

import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.stereotype.Component;
import pl.umk.allegroworkshop.restIntroduction.domain.model.ExternalIngredient;
import pl.umk.allegroworkshop.restIntroduction.domain.model.ExternalIngredientDetails;
import pl.umk.allegroworkshop.restIntroduction.domain.model.ExternalIngredientsList;
import pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular.model.SpoonacularIngredientInformation;
import pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular.model.SpoonacularNutrient;
import pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular.model.SpoonacularNutrition;

@Component
public class SpoonacularService {
    private final SpoonacularClient spoonacularClient;
//    private final LoadingCache<Long, SpoonacularIngredientInformation> ingredientsInformationCache;

    public SpoonacularService(SpoonacularClient spoonacularClient
//                              LoadingCache<Long,SpoonacularIngredientInformation> ingredientsInformationCache
    ) {
        this.spoonacularClient = spoonacularClient;
//        this.ingredientsInformationCache = ingredientsInformationCache;
    }

    public ExternalIngredientsList getIngredientsByName(String name) {
        return new ExternalIngredientsList(spoonacularClient.findIngredientsByName(name).getResults().stream().map(
                searchItem -> new ExternalIngredient(searchItem.getId(), searchItem.getName())).toList());
    }

    public ExternalIngredientDetails getExternalIngredientDetails(Long id) {

        return null;
    }

    private Float getCalories(SpoonacularNutrition nutrition) {
        SpoonacularNutrient caloriesNutrient = nutrition.getNutrients().stream().filter(it -> it.getName().equals("Calories")).findFirst().orElse(null);
        return caloriesNutrient == null ? null : caloriesNutrient.getAmount();
    }

}
