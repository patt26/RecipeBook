package prat.learning.recipebook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import prat.learning.recipebook.Model.Recipe;
import prat.learning.recipebook.Repository.RecipeRepository;
import prat.learning.recipebook.Service.RecipeService;

@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;

    }

    @PostMapping("/recipe")
    public String createOrUpdate(@ModelAttribute("recipe") Recipe recipe){
        Recipe savedRecipe=recipeRepository.save(recipe);
        return "redirect:recipe/recipeShowForm/"+savedRecipe.getId();
    }
    @GetMapping("/recipe/find/{id}")
    public String showById(@PathVariable Long id, Model model){
        model.addAttribute("recipe",recipeRepository.findById(id));
        return "recipe/recipeShowForm";
    }
}
