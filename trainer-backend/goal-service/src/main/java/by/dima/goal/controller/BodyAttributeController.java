package by.dima.goal.controller;

import by.dima.common.utils.UserInfoExtractor;
import by.dima.goal.model.BodyAttribute;
import by.dima.goal.model.Progress;
import by.dima.goal.service.BodyAttributeService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/body")
public class BodyAttributeController {
    private BodyAttributeService attributeService;
    private UserInfoExtractor infoExtractor;

    public BodyAttributeController(BodyAttributeService attributeService, UserInfoExtractor infoExtractor) {
        this.attributeService = attributeService;
        this.infoExtractor = infoExtractor;
    }

    @GetMapping
    public Iterable<BodyAttribute> getAll() {
        return attributeService.getAll();
    }

    @PostMapping("/{attributeId}")
    public void update(@PathVariable Integer attributeId, @RequestBody Progress progress, Authentication authentication) {
        attributeService.update(attributeService.get(attributeId), progress, infoExtractor.getUserId(authentication));
    }
}
