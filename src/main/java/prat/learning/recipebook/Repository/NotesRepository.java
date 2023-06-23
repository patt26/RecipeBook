package prat.learning.recipebook.Repository;

import org.springframework.data.repository.CrudRepository;
import prat.learning.recipebook.Model.Notes;

public interface NotesRepository extends CrudRepository<Notes,Long> {
}
