package by.dima.training.controller;

import by.dima.training.converters.Converter;
import by.dima.training.dto.PassedSetDTO;
import by.dima.training.model.PassedSet;
import by.dima.training.services.PassedSetService;
import by.dima.training.utils.UserInfoExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/set")
public class SetController {

    private final PassedSetService passedSetService;
    private final UserInfoExtractor infoExtractor;
    private final Converter<PassedSet, PassedSetDTO> passedSetDTOConverter;

    @Autowired
    public SetController(PassedSetService passedSetService, UserInfoExtractor infoExtractor, Converter<PassedSet, PassedSetDTO> passedSetDTOConverter) {
        this.passedSetService = passedSetService;
        this.infoExtractor = infoExtractor;
        this.passedSetDTOConverter = passedSetDTOConverter;
    }

    @PostMapping()
    public Object passSet(@RequestBody PassedSet passedSet, Authentication authentication) {
        passedSetService.pass(passedSetDTOConverter.convert(passedSet), infoExtractor.getUserId(authentication));
        return ResponseEntity.ok(true);
    }

}
