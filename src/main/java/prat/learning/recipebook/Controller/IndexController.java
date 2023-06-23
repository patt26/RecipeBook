package prat.learning.recipebook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import prat.learning.recipebook.Service.RecipeService;

@Controller
public class IndexController {

   private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/index","","/"})
    public String getHomePage(Model model){
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}
