//package de.aittr.team24_FP_backend.controllers;
//
//import de.aittr.team24_FP_backend.domain.UserLogin;
//import de.aittr.team24_FP_backend.services.UserService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/user")
//public class User {
//
//    UserService service;
//
//    public User(UserService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public List<UserLogin> findByChildrenInfoTrue() {
//        return service.findByChildrenInfoTrue();
//    }
//}
