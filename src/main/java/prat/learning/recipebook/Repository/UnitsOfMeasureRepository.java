package prat.learning.recipebook.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import prat.learning.recipebook.Model.UnitOfMeasure;

import java.util.Optional;

@Repository
public interface UnitsOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
    Optional<UnitOfMeasure> findAllByUom(String uom);
}
