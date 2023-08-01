package prat.learning.recipebook.Converter;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import prat.learning.recipebook.Command.IngredientCommand;
import prat.learning.recipebook.Model.Ingredient;
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UomToUomCommand uom;

    public IngredientToIngredientCommand(UomToUomCommand uom) {
        this.uom = uom;
    }


    @Override
    @Nullable
    @Synchronized
    public IngredientCommand convert(Ingredient source) {
        if (source == null) {
            return null;
        }
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(source.getId());
        if (source.getRecipe() != null) {
            ingredientCommand.setRecipeId(source.getRecipe().getId());
        }
        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setUom(uom.convert(source.getUom()));
        return ingredientCommand;
    }
}
