package prat.learning.recipebook.BootStrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import prat.learning.recipebook.Model.Category;
import prat.learning.recipebook.Model.Recipe;
import prat.learning.recipebook.Model.UnitOfMeasure;
import prat.learning.recipebook.Repository.CategoryRepository;
import prat.learning.recipebook.Repository.RecipeRepository;
import prat.learning.recipebook.Repository.UnitsOfMeasureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepsitory;
    private final RecipeRepository recipeRepository;
    private final UnitsOfMeasureRepository unitsOfMeasureRepository;

    public DataLoader(CategoryRepository categoryRepsitory, RecipeRepository recipeRepository, UnitsOfMeasureRepository unitsOfMeasureRepository) {
        this.categoryRepsitory = categoryRepsitory;
        this.recipeRepository = recipeRepository;
        this.unitsOfMeasureRepository = unitsOfMeasureRepository;
    }

    private List<Recipe> getAllRecipe() {
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUom = unitsOfMeasureRepository.findAllByUom("Each");
        if (eachUom.isEmpty()) {
            throw new RuntimeException("Expected UOM is not found");
        }
        Optional<UnitOfMeasure> tableSpoon = unitsOfMeasureRepository.findAllByUom("Tablespoon");
        if (tableSpoon.isEmpty()) {
            throw new RuntimeException("Tablespoon is not present");
        }

        Optional<UnitOfMeasure> teaSpoon = unitsOfMeasureRepository.findAllByUom("Teaspoon");
        if (teaSpoon.isEmpty()) {
            throw new RuntimeException("Teaspoon is not present");
        }

        Optional<UnitOfMeasure> cup = unitsOfMeasureRepository.findAllByUom("Cup");
        if (cup.isEmpty()) {
            throw new RuntimeException("Cup is not present");
        }

        Optional<UnitOfMeasure> pinch = unitsOfMeasureRepository.findAllByUom("Pinch");
        if (pinch.isEmpty()) {
            throw new RuntimeException("Pinch is not present");
        }

        Optional<UnitOfMeasure> kg = unitsOfMeasureRepository.findAllByUom("Kilogram");
        if (kg.isEmpty()) {
            throw new RuntimeException("Kilogram is not present");
        }

        UnitOfMeasure eachMeasure = eachUom.get();
        UnitOfMeasure tableSpoonMeasure = tableSpoon.get();
        UnitOfMeasure teaSpoonMeasure = teaSpoon.get();
        UnitOfMeasure cupMeasure = cup.get();
        UnitOfMeasure pinchMeasure = pinch.get();
        UnitOfMeasure kgMeasure = kg.get();

        Optional<Category> american = categoryRepsitory.findByCategoryName("American");
        if (american.isEmpty()) {
            throw new RuntimeException("American is not present");
        }

        Optional<Category> italian = categoryRepsitory.findByCategoryName("Italian");
        if (italian.isEmpty()) {
            throw new RuntimeException("Italian is not present");
        }

        Category americanCategory = american.get();
        Category italianCategory = italian.get();


        return recipes;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Loading Data");
    }
}
