package by.dima.training.repository;

import by.dima.training.dto.ExerciseSetDTO;
import by.dima.training.model.ExerciseSet;
import by.dima.training.model.Muscle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends CrudRepository<ExerciseSetDTO, Integer> {
}
