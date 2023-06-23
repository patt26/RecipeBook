package prat.learning.recipebook.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import prat.learning.recipebook.Model.Recipe;
import prat.learning.recipebook.Repository.RecipeRepository;

import java.util.HashSet;
import java.util.Set;
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("In Service");
        Set<Recipe> recipes=new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;

    }
}
