package by.dima.training.controller;

import by.dima.training.model.PassedSet;
import by.dima.training.services.PassedSetService;
import by.dima.common.utils.UserInfoExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/set")
public class SetController {

    private final PassedSetService passedSetService;
    private final UserInfoExtractor infoExtractor;

    @Autowired
    public SetController(PassedSetService passedSetService, UserInfoExtractor infoExtractor) {
        this.passedSetService = passedSetService;
        this.infoExtractor = infoExtractor;
    }

    @PostMapping()
    public Object passSet(@RequestBody PassedSet passedSet, Authentication authentication) {
        passedSet.setExecDate(new Date());
        passedSetService.pass(passedSet, infoExtractor.getUserId(authentication));
        return ResponseEntity.ok(true);
    }

}
