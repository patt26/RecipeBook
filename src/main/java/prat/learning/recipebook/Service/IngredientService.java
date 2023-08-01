package prat.learning.recipebook.Service;

import prat.learning.recipebook.Command.IngredientCommand;

public interface IngredientService {
    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
