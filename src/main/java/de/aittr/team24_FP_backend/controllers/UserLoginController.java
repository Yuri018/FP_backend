package de.aittr.team24_FP_backend.controllers;

import de.aittr.team24_FP_backend.domain.UserLogin;
import de.aittr.team24_FP_backend.services.UserLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_login")
public class UserLoginController {

    private UserLoginService service;

    public UserLoginController(UserLoginService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public UserLogin register(@RequestBody UserLogin user) {
        return service.register(user);
    }

    @PostMapping("/register/admin")
    public UserLogin registerAdmin(@RequestBody UserLogin user) {
        return service.registerAdmin(user);
    }

}
