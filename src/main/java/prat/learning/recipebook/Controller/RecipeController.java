package prat.learning.recipebook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import prat.learning.recipebook.Model.Recipe;
import prat.learning.recipebook.Repository.RecipeRepository;

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
   public String createNewRecipe(@ModelAttribute("recipe") Recipe recipe){
        Recipe newRecipe=recipeRepository.save(recipe);
        return "redirect:/recipe/find/"+newRecipe.getId();
   }

   @GetMapping("recipe/update/{id}")
   public String updateRecipe(@PathVariable Long id,Model model){
       Optional<Recipe> updateRecipe=recipeRepository.findById(id);
       if(updateRecipe.isPresent()){
           Recipe recipe=updateRecipe.get();
           model.addAttribute("recipe",recipe);
       }
        return "recipe/recipeCreateForm";
   }

   @GetMapping("/recipe/delete/{id}")
   public String deleteRecipe(@PathVariable Long id){
        recipeRepository.deleteById(id);
        return "redirect:/";
   }

//    @PostMapping("/new")
//    public Recipe createData(@RequestBody Recipe recipe){
//        return recipeRepository.save(recipe);
//    }
}
