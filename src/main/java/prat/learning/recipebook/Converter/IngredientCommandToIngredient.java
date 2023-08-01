package prat.learning.recipebook.Converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import prat.learning.recipebook.Command.IngredientCommand;
import prat.learning.recipebook.Model.Ingredient;
import prat.learning.recipebook.Model.Recipe;
@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UomCommandtoUom uom;

    public IngredientCommandToIngredient(UomCommandtoUom uom) {
        this.uom = uom;
    }

    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        if (source.getRecipeId() != null) {
            Recipe recipe = new Recipe();
            recipe.setId(source.getRecipeId());
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uom.convert(source.getUom()));
        return ingredient;
    }
}
