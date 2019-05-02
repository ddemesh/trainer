package by.dima.training.repository;

import by.dima.training.dto.ExerciseRepeatingDTO;
import by.dima.training.model.Exercise;
import by.dima.training.model.ExerciseRepeating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepeatingRepository extends CrudRepository<ExerciseRepeatingDTO, Integer> {
}
