package prat.learning.recipebook.Service;

import org.springframework.stereotype.Component;
import prat.learning.recipebook.Command.IngredientCommand;
import prat.learning.recipebook.Converter.IngredientCommandToIngredient;
import prat.learning.recipebook.Converter.IngredientToIngredientCommand;
import prat.learning.recipebook.Model.Ingredient;
import prat.learning.recipebook.Model.Recipe;
import prat.learning.recipebook.Repository.RecipeRepository;
import prat.learning.recipebook.Repository.UnitsOfMeasureRepository;

import java.util.Optional;
@Component
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final UnitsOfMeasureRepository unitsOfMeasureRepository;
    private final IngredientCommandToIngredient toIngredient;
    private final IngredientToIngredientCommand toIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitsOfMeasureRepository unitsOfMeasureRepository, IngredientCommandToIngredient toIngredient, IngredientToIngredientCommand toIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.unitsOfMeasureRepository = unitsOfMeasureRepository;
        this.toIngredient = toIngredient;
        this.toIngredientCommand = toIngredientCommand;
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(command.getRecipeId());

        if (optionalRecipe.isEmpty()) {
            return new IngredientCommand();
        } else {
            Recipe recipe = optionalRecipe.get();
            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();
            if (ingredientOptional.isPresent()) {
                Ingredient presentIngredient = ingredientOptional.get();
                presentIngredient.setDescription(command.getDescription());
                presentIngredient.setAmount(command.getAmount());
                presentIngredient.setUom(unitsOfMeasureRepository.findById(command.getUom().getId()).orElseThrow(()
                        -> new RuntimeException("UOM not found")));
            } else {
                Ingredient ingredient = toIngredient.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }
            Recipe savedRecipe = recipeRepository.save(recipe);
            Optional<Ingredient> optionalIngredient = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredient -> recipeIngredient.getId().equals(command.getId()))
                    .findFirst();

            if (!optionalIngredient.isPresent()) {
                optionalIngredient = savedRecipe.getIngredients().stream()
                        .filter(rcipeI -> rcipeI.getDescription().equals(command.getDescription()))
                        .filter(recipeI -> recipeI.getAmount().equals(command.getAmount()))
                        .filter(recipeI -> recipeI.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }


            return toIngredientCommand.convert(optionalIngredient.get());
        }

    }


}
