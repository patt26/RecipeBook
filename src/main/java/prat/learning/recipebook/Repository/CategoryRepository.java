package prat.learning.recipebook.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import prat.learning.recipebook.Model.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    Optional<Category> findByCategoryName(String category);
}
