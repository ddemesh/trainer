package by.dima.training.repository;

import by.dima.training.model.Exercise;
import by.dima.training.model.ExerciseRepeating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepeatingRepository extends CrudRepository<ExerciseRepeating, Integer> {
}
