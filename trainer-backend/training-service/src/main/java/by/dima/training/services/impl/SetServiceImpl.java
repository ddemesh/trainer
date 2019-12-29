package by.dima.training.services.impl;

import by.dima.training.model.ExerciseSet;
import by.dima.training.repository.SetRepository;
import by.dima.training.services.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetServiceImpl implements SetService {

    private final SetRepository setRepository;

    @Autowired
    public SetServiceImpl(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    @Override
    public ExerciseSet getById(Integer id) {
        return setRepository.findById(id).get();
    }

    @Override
    public void save(ExerciseSet set) {
        setRepository.save(set);
    }
}
