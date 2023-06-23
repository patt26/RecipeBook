package prat.learning.recipebook.Repository;

import org.springframework.data.repository.CrudRepository;
import prat.learning.recipebook.Model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient,Long> {
}
