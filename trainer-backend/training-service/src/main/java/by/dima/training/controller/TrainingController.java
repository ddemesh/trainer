package by.dima.training.controller;

import by.dima.training.model.TrainingComplex;
import by.dima.training.services.TrainingService;
import by.dima.training.utils.UserInfoExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private UserInfoExtractor infoExtractor;

    @Autowired
    private TrainingService trainingService;

    @GetMapping("/me")
    public Object getTrainingComplexesOfUser(Authentication authentication) {
        return trainingService.getSavedTrainings(infoExtractor.getUserId((authentication)));
    }

    @PostMapping("/me")
    public Object create(@RequestBody TrainingComplex complex, Authentication authentication) {
        return trainingService.createComplex(complex, infoExtractor.getUserId((authentication)));
    }

    @GetMapping("/me/{complexId}")
    public Object getLastTraining(@PathVariable Integer complexId, Authentication authentication) {
        return trainingService.getLastTrainingId(complexId, infoExtractor.getUserId((authentication)));
    }

    @PostMapping("/me/{complexId}")
    public Object add(@PathVariable Integer complexId, Authentication authentication) {
        return trainingService.saveComplex(complexId, infoExtractor.getUserId((authentication)));
    }

    @DeleteMapping("/me/{complexId}")
    public Object delete(@PathVariable Integer complexId, Authentication authentication) {
        trainingService.deleteComplex(complexId, infoExtractor.getUserId((authentication)));
        return ResponseEntity.ok().body(true);
    }

    @GetMapping
    public Object getAllTrainingComplexes() {
        return trainingService.getAllTrainings();
    }

    @GetMapping("/{complexId}/info")
    public Object getTrainingComplexeInfo(@PathVariable Integer complexId, Authentication authentication) {
        return trainingService.getFullInfo(complexId,  infoExtractor.getUserId((authentication)));
    }
}
