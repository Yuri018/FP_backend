package de.aittr.team24_FP_backend.controllers;

import de.aittr.team24_FP_backend.domain.User;
import de.aittr.team24_FP_backend.domain.UserLogin;
import de.aittr.team24_FP_backend.security.sec_service.AuthService;
import de.aittr.team24_FP_backend.services.UserLoginService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_login")
public class UserLoginController {

    private UserLoginService service;
    private AuthService authService;

    public UserLoginController(UserLoginService service, AuthService authService) {
        this.service = service;
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserLogin register(@RequestBody UserLogin user) {
        return service.register(user);
    }

    @PostMapping("/admin/register")
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

    @DeleteMapping("/admin/{id}")
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }


    @PutMapping
    public void updateUserSubscriptions(@RequestBody User newUser) {
        String userName = authService.getAuthInfo().getName();
        service.updateUserSubscriptionsByToken(newUser, userName);
    }


//    @GetMapping("/children_info_true")
//    public List<UserLogin> findAllByUserChildrenInfoTrue() {
//        return service.findAllByUserChildrenInfoTrue();
//    }
//
//    @GetMapping("/{cityName}/children_info_and_city_true")
//    public List<UserLogin> findAllByUserChildrenAndCityTrue(@PathVariable String cityName) {
//        return service.findAllByUserChildrenAndCityTrue(cityName);
//    }
}
