package by.dima.training.repository;

import by.dima.training.dto.MuscleDTO;
import by.dima.training.model.Exercise;
import by.dima.training.model.Muscle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MuscleRepository extends CrudRepository<MuscleDTO, Integer> {
    Optional<MuscleDTO> findByName(String name);
}
