package de.aittr.team24_FP_backend.security.sec_config;

import de.aittr.team24_FP_backend.security.sec_filter.TokenFilter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.core.io.buffer.DataBufferUtils.matcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private TokenFilter filter;

    public SecurityConfig(TokenFilter filter) {
        this.filter = filter;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(x -> x
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/user_login/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/user_login/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/user_login").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET, "/user_login/children_info_true").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/user_login/children_info_and_berlin_true").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/user_login/{cityName}/children_info_and_city_true").permitAll()

//                        .requestMatchers(HttpMethod.GET, "/user").permitAll()

                        .requestMatchers(HttpMethod.POST, "/example_parsing").permitAll()

                        .requestMatchers("/{city}/restaurants_info/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/{city}/restaurants_info").permitAll()
                        .requestMatchers(HttpMethod.GET, "/{city}/restaurants_info/find_by_title/{title}").permitAll()

                        .requestMatchers("/{city}/shops_info/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/{city}/shops_info").permitAll()
                        .requestMatchers(HttpMethod.GET, "/{city}/shops_info/find_by_title/{title}").permitAll()

                        .requestMatchers("/{city}/children_info/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/{city}/children_info").permitAll()
                        .requestMatchers(HttpMethod.GET, "/{city}/children_info/find_by_title/{title}").permitAll()

                        .requestMatchers("/{city}/doctors_info/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/{city}/doctors_info/{doctorCategory}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/{city}/doctors_info/find_by_title/{title}/{doctorCategory}").permitAll()

                        .requestMatchers("/{city}/hair_beauty_info/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/{city}/hair_beauty_info").permitAll()
                        .requestMatchers(HttpMethod.GET, "/{city}/hair_beauty_info/find_by_title/{title}").permitAll()

                        .requestMatchers("/{city}/legal_services_info/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/{city}/legal_services_info").permitAll()
                        .requestMatchers(HttpMethod.GET, "/{city}/legal_services_info/find_by_title/{title}").permitAll()

                        .requestMatchers("/{city}/translators_info/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/{city}/translators_info").permitAll()
                        .requestMatchers(HttpMethod.GET, "/{city}/translators_info/find_by_title/{title}").permitAll()

                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/refresh").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/get_auth_info").hasRole("USER")

                        .requestMatchers(HttpMethod.GET, "/search").permitAll()
                        .requestMatchers(HttpMethod.GET, "/general_news/get_info_by/{categoryTitle}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/general_news/except/{categoryTitle}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/berlin_news").permitAll()
                        .requestMatchers(HttpMethod.GET, "/berlin_news/get_info_by/{categoryTitle}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/berlin_news/except/{categoryTitle}").permitAll()

                        .anyRequest().authenticated())
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }


}
