package by.dima.training.services.impl;

import by.dima.training.model.PassedSet;
import by.dima.training.repository.PassedRepository;
import by.dima.training.services.PassedSetService;
import by.dima.training.services.SetService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassedSetServiceImpl implements PassedSetService {

    private PassedRepository passedRepository;

    private SetService setService;

    public PassedSetServiceImpl(PassedRepository passedRepository, SetService setService) {
        this.passedRepository = passedRepository;
        this.setService = setService;
    }

    @Override
    public void save(PassedSet passedSet, Integer userId) {
        passedSet.setId(null);
        passedSet.setExecDate(null);
        passedSet.setIdUser(userId);
//        passedSet.setTraining(training.getId());
        passedSet.setExerciseSet(null);
        //todo -- check behaviour
        setService.save(passedSet.getSet());
//        passedSet.setExerciseSetId(set.getExerciseSet().getId());
        passedSet.setExerciseSet(passedSet.getSet().getId());
        passedRepository.save(passedSet);

    }

    @Override
    public PassedSet getById(Integer id) {
        return passedRepository.findById(id).get();
    }

    @Override
    public PassedSet pass(PassedSet set, Integer userId) {
        PassedSet passedSet = new PassedSet();

        passedSet.setIdTraining(set.getIdTraining());
        passedSet.setIdUser(userId);
        passedSet.setExerciseSet(set.getExerciseSet());
        passedSet.setExecDate(set.getExecDate());

        return passedRepository.save(passedSet);
    }

    @Override
    public List<PassedSet> getPassedSetsByDate(Integer userId, Date date) {
        return passedRepository.findAllByIdUserAndExecDate(userId, date).parallelStream().distinct().collect(Collectors.toList());
    }

    @Override
    public void removePassedSetsByTrainingsAndUser(Integer userId, List<Integer> trainings) {
        passedRepository.deleteAllByIdUserAndIdTrainingInAndExecDateIsNull(userId,
                trainings);
    }

    @Override
    public Integer getLastSetOfTrainings(List<Integer> trainings, Integer userId) {
        Optional<Integer> last = passedRepository.findLast(userId, trainings);
        return last.orElse(-1);
    }
}
