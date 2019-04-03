package by.dima.training.controller;

import by.dima.training.model.PassedSet;
import by.dima.training.services.SetService;
import by.dima.training.utils.UserInfoExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/set")
public class SetController {

    @Autowired
    private SetService setService;

    @Autowired
    private UserInfoExtractor infoExtractor;

    @PostMapping()
    public Object passSet(@RequestBody PassedSet passedSet, Authentication authentication) {
        setService.passSet(passedSet, infoExtractor.getUserId(authentication));
        return ResponseEntity.ok(true);
    }

}
