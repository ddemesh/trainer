package by.dima.training.repository;

import by.dima.training.dto.TrainingDTO;
import by.dima.training.model.PassedSet;
import by.dima.training.model.Training;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TrainingRepository extends CrudRepository<TrainingDTO, Integer> {
    Set<TrainingDTO> findAllByIdIn(Set<Integer> ids);
}
