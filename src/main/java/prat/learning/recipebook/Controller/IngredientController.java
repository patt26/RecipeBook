package prat.learning.recipebook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import prat.learning.recipebook.Repository.RecipeRepository;

@Controller
public class IngredientController {

    private final RecipeRepository recipeRepository;

    public IngredientController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipe/ingredient/{id}")
    public String listIngredients(@PathVariable Long id, Model model){
        model.addAttribute("recipe",recipeRepository.findById(id));
        return "ingredient/listIngredient";
    }
}
