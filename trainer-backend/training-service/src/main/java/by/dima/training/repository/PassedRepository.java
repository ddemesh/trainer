package by.dima.training.repository;

import by.dima.training.dto.PassedSetDTO;
import by.dima.training.model.Exercise;
import by.dima.training.model.PassedSet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PassedRepository extends CrudRepository<PassedSetDTO, Integer> {
    List<PassedSetDTO> findAllByIdUserAndExecDate(Integer userId, Date execDate);
    List<PassedSetDTO> findAllByIdUserAndExecDateIsNull(Integer userId);
    @Query("select ps.idTraining from PassedSetDTO as ps where ps.idUser = ?1 and ps.idTraining in ?2 and ps.execDate = (select max(p.execDate) from PassedSetDTO as p)")
    Optional<Integer> findLast(Integer userId, List<Integer> trainingIds);
    @Transactional
    void deleteAllByIdUserAndIdTrainingInAndExecDateIsNull(Integer userId, List<Integer> trainingIds);
}
