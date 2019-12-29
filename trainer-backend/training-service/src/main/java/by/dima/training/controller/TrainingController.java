package by.dima.training.controller;

import by.dima.training.model.TrainingComplex;
import by.dima.training.services.ComplexService;
import by.dima.common.utils.UserInfoExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private UserInfoExtractor infoExtractor;

    @Autowired
    private ComplexService complexService;

    @GetMapping("/me")
    public Object getTrainingComplexesOfUser(Authentication authentication) {
        return complexService.getFavourites(infoExtractor.getUserId((authentication)));
    }

    @PostMapping("/me")
    public ResponseEntity create(@RequestBody TrainingComplex complex, Authentication authentication) {
        complexService.save(complex, infoExtractor.getUserId((authentication)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/me/{complexId}")
    public Object getLastTraining(@PathVariable Integer complexId, Authentication authentication) {
        return complexService.getLastPassedTrainingId(complexId, infoExtractor.getUserId((authentication)));
    }

    @PostMapping("/me/{complexId}")
    public ResponseEntity add(@PathVariable Integer complexId, Authentication authentication) {
        complexService.addToFavourites(complexService.getById(complexId), infoExtractor.getUserId((authentication)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/me/{complexId}")
    public Object delete(@PathVariable Integer complexId, Authentication authentication) {
        complexService.removeFromFavourites(complexId, infoExtractor.getUserId((authentication)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public Object getAllTrainingComplexes() {
        return complexService.getAvailable();
    }

    @GetMapping("/{complexId}/info")
    public Object getTrainingComplexInfo(@PathVariable Integer complexId, Authentication authentication) {
        return complexService.getById(complexId,  infoExtractor.getUserId((authentication)));
    }
}
