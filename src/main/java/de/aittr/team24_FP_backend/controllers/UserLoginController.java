package de.aittr.team24_FP_backend.controllers;

import de.aittr.team24_FP_backend.domain.UserLogin;
import de.aittr.team24_FP_backend.services.UserLoginService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/admin")
    public List<UserLogin> getAllUsersLogin() {
        return service.getAllUsersLogin();
    }

    @GetMapping("/admin/{username}")
    public UserLogin findByUsername(@PathVariable String username) {
        return service.findByUsername(username);
    }

    @GetMapping("/admin/id/{id}")
    public UserLogin findById(@PathVariable int id) {
        return service.findById(id);
    }

}
