package by.dima.training.repository;

import by.dima.training.dto.TagDTO;
import by.dima.training.dto.TrainingComplexDTO;
import by.dima.training.model.Tag;
import by.dima.training.model.TrainingComplex;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplexRepository extends PagingAndSortingRepository<TrainingComplexDTO, Integer> {
    List<TrainingComplexDTO> findAllByTagDTOSContains(TagDTO tag, Pageable pageable);
}
