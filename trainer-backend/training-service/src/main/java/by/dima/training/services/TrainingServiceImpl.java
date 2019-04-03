package by.dima.training.services;

import by.dima.training.exception.IncorrectIdException;
import by.dima.training.model.PassedSet;
import by.dima.training.model.Training;
import by.dima.training.model.TrainingComplex;
import by.dima.training.repository.*;
import by.dima.training.utils.ConstantHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private ComplexRepository complexRepository;

    @Autowired
    private PassedRepository passedRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private ConstantHolder constants;

    @Override
    public Iterable<TrainingComplex> getAllTrainings() {
        return this.getSavedTrainings(constants.getSystemUserId());
    }

    @Override
    public Set<TrainingComplex> getSavedTrainings(Integer userId) {
        Stream<Integer> trainingId = passedRepository.findAllByIdUserAndExecDateIsNull(userId)
                .stream()
                .map(PassedSet::getIdTraining);
        Set<TrainingComplex> complexes = new HashSet<>();

        trainingRepository.findAllByIdIn(trainingId.collect(Collectors.toSet()))
                .forEach(training -> {
                    if (!complexes.contains(training.getComplex())) {
                        complexes.add(training.getComplex());
                    }
                });

        return complexes;
    }

    @Override
    public TrainingComplex saveComplex(Integer complexId, Integer userId) {
        TrainingComplex source = complexRepository.findById(complexId).orElseThrow(() -> new IncorrectIdException("Complex with such id not "));

        source.getTraining().forEach(training -> training.getPassedSets().stream().filter(set -> set.getExecDate() == null).findFirst().ifPresent(set -> {
            PassedSet passedSet = new PassedSet();

            passedSet.setIdTraining(training.getId());
            passedSet.setIdUser(userId);
            passedSet.setExerciseSetId(set.getExerciseSetId());

            passedRepository.save(passedSet);
            training.getPassedSets().add(passedSet);
        }));

        return complexRepository.save(source);
    }

    @Override
    public void deleteComplex(Integer complexId, Integer userId) {
        TrainingComplex complex = complexRepository.findById(complexId).orElseThrow(() -> new IncorrectIdException("Complex with such id not "));

        passedRepository.deleteAllByIdUserAndIdTrainingInAndExecDateIsNull(userId, complex.getTraining().stream().map(Training::getId).collect(Collectors.toList()));
    }

    @Override
    public TrainingComplex createComplex(TrainingComplex complex, Integer userId) {
        complex.setId(null);

        complex.getTraining().forEach(training -> {
            training.setId(null);
            Set<PassedSet> passedSets = training.getPassedSets();
            training.setPassedSets(null);
            trainingRepository.save(training);
            passedSets.forEach(set -> {
                set.setId(null);
                set.setExecDate(null);
                set.setIdUser(userId);
                set.setIdTraining(training.getId());
                set.getExerciseSet().setId(null);
                setRepository.save(set.getExerciseSet());
                set.setExerciseSetId(set.getExerciseSet().getId());
                set.setExerciseSet(null);
                passedRepository.save(set);
            });
        });

        return complexRepository.save(complex);
    }

    @Override
    public TrainingComplex getFullInfo(Integer complexId, Integer userId) {
        TrainingComplex complex = complexRepository.findById(complexId).orElseThrow(() -> new IncorrectIdException("Complex with such id not "));

        complex.getTraining().forEach(training -> {
            training.setPassedSets(training.getPassedSets().stream().filter(set -> set.getExecDate() == null && set.getIdUser().equals(userId)).collect(Collectors.toSet()));
            training.getPassedSets().forEach(set -> set.setExerciseSet(setRepository.findById(set.getExerciseSetId()).get()));
        });

        return complex;
    }

    @Override
    public Integer getLastTrainingId(Integer complexId, Integer userId) {
        TrainingComplex complex = complexRepository.findById(complexId).orElseThrow(() -> new IncorrectIdException("Complex with such id not "));
        Optional<Integer> last = passedRepository.findLast(userId, complex.getTraining().stream().map(Training::getId).collect(Collectors.toList()));
        return last.orElse(-1);
    }
}
