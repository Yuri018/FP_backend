package de.aittr.team24_FP_backend.security.sec_service;

import de.aittr.team24_FP_backend.domain.UserLogin;
import de.aittr.team24_FP_backend.security.sec_dto.AuthInfo;
import de.aittr.team24_FP_backend.security.sec_dto.TokenResponseDto;
import de.aittr.team24_FP_backend.services.UserLoginService;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Nonnull;
import jakarta.security.auth.message.AuthException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private UserLoginService userService;
    private TokenService tokenService;
    private Map<String, String> refreshStorage;
    private BCryptPasswordEncoder encoder;

    public AuthService(UserLoginService userService, TokenService tokenService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.encoder = encoder;
        this.refreshStorage = new HashMap<>();
    }

    public TokenResponseDto login(@Nonnull UserLogin inboundUser) throws AuthException {
        String username = inboundUser.getUsername();
        UserLogin foundUser = (UserLogin) userService.loadUserByUsername(username);

        if (encoder.matches(inboundUser.getPassword(), foundUser.getPassword())) {
            String accessToken = tokenService.generateAccessToken(foundUser);
            String refreshToken = tokenService.generateRefreshToken(foundUser);
            refreshStorage.put(username, refreshToken);
            return new TokenResponseDto(accessToken, refreshToken);
        } else {
            throw new AuthException("Password is incorrect");
        }
    }

    public TokenResponseDto getAccessToken(@Nonnull String refreshToken) {
        if (tokenService.validateRefreshToken(refreshToken)) {
            Claims refreshClaims = tokenService.getRefreshClaims(refreshToken);
            String username = refreshClaims.getSubject();
            String savedRefreshToken = refreshStorage.get(username);

            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                UserLogin user = (UserLogin) userService.loadUserByUsername(username);
                String accessToken = tokenService.generateAccessToken(user);
                return new TokenResponseDto(accessToken, null);
            }
        }
        return new TokenResponseDto(null, null);
    }

    public AuthInfo getAuthInfo() {
        return (AuthInfo) SecurityContextHolder.getContext().getAuthentication();
    }
}