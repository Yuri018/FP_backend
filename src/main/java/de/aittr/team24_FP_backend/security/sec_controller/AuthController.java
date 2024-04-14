package de.aittr.team24_FP_backend.security.sec_controller;

import de.aittr.team24_FP_backend.domain.User;
import de.aittr.team24_FP_backend.domain.UserLogin;
import de.aittr.team24_FP_backend.security.sec_dto.AuthInfo;
import de.aittr.team24_FP_backend.security.sec_dto.RefreshRequestDto;
import de.aittr.team24_FP_backend.security.sec_dto.TokenResponseDto;
import de.aittr.team24_FP_backend.security.sec_service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authorization controller", description = "Controller for security operations, login/logout, getting new tokens etc")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @Operation(
            summary = "Login",
            description = "Logging into the system"
    )
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Object of an user that logging in") UserLogin user,
            @Parameter(description = "Object of a response that will be transferred to a client") HttpServletResponse response
    ) {
        try {
            TokenResponseDto tokenDto = service.login(user);

            Cookie cookie = new Cookie("Access-Token", tokenDto.getAccessToken());
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            return ResponseEntity.ok(tokenDto);
        } catch (AuthException e) {
            TokenResponseDto tokenDto = new TokenResponseDto(e.getMessage());
            return new ResponseEntity<>(tokenDto, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Get new access token",
            description = "Receiving a new access token through providing an existing refresh token"
    )
    @PostMapping("/access")
    public ResponseEntity<TokenResponseDto> getNewAccessToken(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Object of an inbound request that contains a refresh token") RefreshRequestDto request
    ) {
        TokenResponseDto accessToken = service.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(accessToken);
    }

    @Operation(
            summary = "Logout",
            description = "Logging out from the system"
    )
    @GetMapping("/logout")
    public void logout(
            @Parameter(description = "Object of a response that will be transferred to a client") HttpServletResponse response
    ) {
        Cookie cookie = new Cookie("Access-Token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @GetMapping("/get_auth_info")
    public AuthInfo getAuthInfo() {
        return service.getAuthInfo();
    }

//    // Ваш метод для обновления подписок пользователя
//    @PostMapping("/update-user-subscriptions")
//    public ResponseEntity<String> updateUserSubscriptions(@RequestBody User updatedUser) {
//        // Получаем информацию о текущем аутентифицированном пользователе
//        AuthInfo authInfo = service.getAuthInfo();
//
//        // Проверяем, аутентифицирован ли пользователь
//        if (authInfo == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
//        }
//
//        // Получаем имя текущего пользователя
//        UserLogin currentUser = authInfo.getUser();
//
//        // Проверяем, получен ли текущий пользователь
//        if (currentUser == null) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Current user not found");
//        }
//
//        // Получаем его id
//        int userId = currentUser.getId();
//
//        // Обновляем подписки пользователя
//        try {
//            authService.updateUserSubscriptions(userId, updatedUser);
//            return ResponseEntity.ok("User subscriptions updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user subscriptions: " + e.getMessage());
//        }
//    }
}