package by.dima.auth.controller;

import by.dima.auth.model.User;
import by.dima.auth.service.ProfileImageService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ChangeImageController {

    private ProfileImageService imageService;

    public ChangeImageController(ProfileImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public void change(@RequestBody String image, Authentication authentication) {
        imageService.update(image, authentication.getPrincipal().toString());
    }
}
