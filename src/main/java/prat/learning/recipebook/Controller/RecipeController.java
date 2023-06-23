package prat.learning.recipebook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import prat.learning.recipebook.Model.Recipe;
import prat.learning.recipebook.Repository.RecipeRepository;
import prat.learning.recipebook.Service.RecipeService;

import java.util.Optional;

@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;

    }

    @GetMapping("/recipe/find/{id}")
    public String showById(@PathVariable Long id, Model model){
       Optional<Recipe> newRecipe=recipeRepository.findById(id);
       if(newRecipe.isPresent()){
           model.addAttribute("recipe",newRecipe);
       }

        return "recipe/recipeShowForm";
    }

    @GetMapping("recipe/new")
   public String newRecipeForm(Model model){
        model.addAttribute("recipe",new Recipe());
        return "recipe/recipeCreateForm";
   }
   @PostMapping("recipe")
   public String createNewRecipe(@ModelAttribute Recipe recipe){
        Recipe newRecipe=recipeRepository.save(recipe);
        return "redirect:/recipe/find/"+newRecipe.getId();
   }

//    @PostMapping("/new")
//    public Recipe createData(@RequestBody Recipe recipe){
//        return recipeRepository.save(recipe);
//    }
}
