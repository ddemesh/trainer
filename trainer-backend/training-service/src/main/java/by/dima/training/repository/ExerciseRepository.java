package by.dima.training.repository;

import by.dima.training.model.Exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {
    Optional<Exercise> findByName(String name);
}
