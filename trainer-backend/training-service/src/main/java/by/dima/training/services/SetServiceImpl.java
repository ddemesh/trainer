package by.dima.training.services;

import by.dima.training.model.PassedSet;
import by.dima.training.repository.PassedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SetServiceImpl implements SetService {

    @Autowired
    private PassedRepository passedRepository;

    @Override
    public void passSet(PassedSet set, Integer userId) {
        set.setId(null);
        set.setIdUser(userId);
        set.setExecDate(new Date());
        set.setExerciseSet(null);

        passedRepository.save(set);
    }
}
