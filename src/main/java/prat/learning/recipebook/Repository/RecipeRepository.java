package prat.learning.recipebook.Repository;

import org.springframework.data.repository.CrudRepository;
import prat.learning.recipebook.Model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
