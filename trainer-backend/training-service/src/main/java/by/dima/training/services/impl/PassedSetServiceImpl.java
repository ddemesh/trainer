package by.dima.training.services.impl;

import by.dima.training.converters.Converter;
import by.dima.training.dto.PassedSetDTO;
import by.dima.training.model.PassedSet;
import by.dima.training.repository.PassedRepository;
import by.dima.training.services.PassedSetService;
import by.dima.training.services.SetService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassedSetServiceImpl implements PassedSetService {

    private PassedRepository passedRepository;
    private Converter<PassedSetDTO, PassedSet> passedSetConverter;
    private Converter<PassedSet, PassedSetDTO> passedSetDTOConverter;

    private SetService setService;

    public PassedSetServiceImpl(PassedRepository passedRepository, Converter<PassedSetDTO, PassedSet> passedSetConverter, Converter<PassedSet, PassedSetDTO> passedSetDTOConverter, SetService setService) {
        this.passedRepository = passedRepository;
        this.passedSetConverter = passedSetConverter;
        this.passedSetDTOConverter = passedSetDTOConverter;
        this.setService = setService;
    }

    @Override
    public void save(PassedSet passedSet, Integer userId) {
        passedSet.setId(null);
        passedSet.setExecDate(null);
        passedSet.setIdUser(userId);
//        passedSet.setTraining(training.getId());
        passedSet.getExerciseSet().setId(null);
        setService.save(passedSet.getExerciseSet());
//        passedSet.setExerciseSetId(set.getExerciseSet().getId());
        passedSet.setExerciseSet(null);
        passedRepository.save(passedSetDTOConverter.convert(passedSet));

    }

    @Override
    public PassedSet getById(Integer id) {
        return passedSetConverter.convert(passedRepository.findById(id).get());
    }

    @Override
    public PassedSetDTO pass(PassedSetDTO set, Integer userId) {
        PassedSetDTO passedSetDTO = new PassedSetDTO();

        passedSetDTO.setIdTraining(set.getIdTraining());
        passedSetDTO.setIdUser(userId);
        passedSetDTO.setExerciseSetId(set.getExerciseSetId());

        return passedRepository.save(passedSetDTO);
    }

    @Override
    public List<PassedSet> getPassedSetsByDate(Integer userId, Date date) {
        return passedRepository.findAllByIdUserAndExecDate(userId, date).parallelStream()
                .map(passedSetConverter::convert)
                .collect(Collectors.toList());
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
