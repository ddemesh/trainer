package by.dima.training.repository;

import by.dima.training.model.Muscle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MuscleRepository extends CrudRepository<Muscle, Integer> {
    Optional<Muscle> findByName(String name);
}
