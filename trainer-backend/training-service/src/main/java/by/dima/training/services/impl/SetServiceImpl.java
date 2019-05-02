package by.dima.training.services.impl;

import by.dima.training.converters.Converter;
import by.dima.training.dto.ExerciseSetDTO;
import by.dima.training.model.ExerciseSet;
import by.dima.training.repository.SetRepository;
import by.dima.training.services.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetServiceImpl implements SetService {

    private final SetRepository setRepository;

    private final Converter<ExerciseSetDTO, ExerciseSet> setConverter;
    private final Converter<ExerciseSet, ExerciseSetDTO> setDTOConverter;

    @Autowired
    public SetServiceImpl(SetRepository setRepository, Converter<ExerciseSetDTO, ExerciseSet> setConverter, Converter<ExerciseSet, ExerciseSetDTO> setDTOConverter) {
        this.setRepository = setRepository;
        this.setConverter = setConverter;
        this.setDTOConverter = setDTOConverter;
    }

    @Override
    public ExerciseSet getById(Integer id) {
        return setConverter.convert(setRepository.findById(id).get());
    }

    @Override
    public void save(ExerciseSet set) {
        setRepository.save(setDTOConverter.convert(set));
    }
}
