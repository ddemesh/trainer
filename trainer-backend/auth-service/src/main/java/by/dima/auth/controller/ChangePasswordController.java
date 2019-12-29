package by.dima.auth.controller;

import by.dima.auth.model.User;
import by.dima.auth.service.ChangePasswordService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/password")
public class ChangePasswordController {

    private ChangePasswordService passwordService;

    public ChangePasswordController(ChangePasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void change(@RequestBody Map<String, String> body, Authentication authentication) {
        passwordService.change(body.get("oldPass"), body.get("newPass"), authentication.getPrincipal().toString());
    }
}
