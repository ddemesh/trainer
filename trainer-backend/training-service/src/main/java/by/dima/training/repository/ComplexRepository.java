package by.dima.training.repository;

import by.dima.training.model.PassedSet;
import by.dima.training.model.Tag;
import by.dima.training.model.TrainingComplex;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplexRepository extends PagingAndSortingRepository<TrainingComplex, Integer> {
    List<TrainingComplex> findAllByTagsContains(Tag tag, Pageable pageable);

}
