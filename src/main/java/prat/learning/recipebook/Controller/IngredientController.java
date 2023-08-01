package prat.learning.recipebook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import prat.learning.recipebook.Command.IngredientCommand;
import prat.learning.recipebook.Model.Recipe;
import prat.learning.recipebook.Repository.IngredientRepository;
import prat.learning.recipebook.Repository.RecipeRepository;
import prat.learning.recipebook.Service.IngredientService;

import java.util.Optional;

@Controller
public class IngredientController {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientService ingredientService;

    public IngredientController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, IngredientService ingredientService) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recipe/ingredient/{id}")
    public String listIngredients(@PathVariable Long id, Model model){
        model.addAttribute("recipe",recipeRepository.findById(id));
        return "ingredient/listIngredient";
    }

    @GetMapping("/recipe/ingredient/new/{id}")
    public String createNewIngredient(@PathVariable Long id,Model model){
        Optional<Recipe> newRecipe=recipeRepository.findById(id);
        if(newRecipe.isPresent()){
            IngredientCommand ingredientCommand=new IngredientCommand();
            ingredientCommand.setRecipeId(id);
            model.addAttribute("ingredient",ingredientCommand);

        }
        return "ingredient/createIngredient";

    }

    @Transactional
    @PostMapping("ingredient")
    public String saveRecipe(@ModelAttribute IngredientCommand ingredient){
        IngredientCommand ingredientCommand=ingredientService.saveIngredientCommand(ingredient);
        return "redirect:/recipe/"+ingredientCommand.getRecipeId()+"/ingredient/"+ingredientCommand.getId()+"/show";
    }
}
