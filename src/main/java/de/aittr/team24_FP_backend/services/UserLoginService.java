package de.aittr.team24_FP_backend.services;

import de.aittr.team24_FP_backend.domain.Role;
import de.aittr.team24_FP_backend.domain.UserLogin;
import de.aittr.team24_FP_backend.exception_handling.exceptions.UserAlreadyExistsException;
import de.aittr.team24_FP_backend.repositories.UserLoginRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserDetailsService {

    private UserLoginRepository repository;

    private BCryptPasswordEncoder encoder;

    public UserLoginService(UserLoginRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin userLogin = repository.findByUsername(username);

        if (userLogin == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userLogin;
    }

    public UserLogin register(UserLogin userLogin) {
        UserLogin foundUserLogin = repository.findByUsername(userLogin.getUsername());

        if (foundUserLogin != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует!");
        }
        userLogin.setId(0);
        userLogin.clearRoles();

        Role role = new Role(2, "ROLE_USER");
        userLogin.addRole(role);

        String encodedPassword = encoder.encode(userLogin.getPassword());
        userLogin.setPassword(encodedPassword);

        return repository.save(userLogin);
    }
    public UserLogin registerAdmin(UserLogin userLogin) {
        UserLogin foundUserLogin = repository.findByUsername(userLogin.getUsername());

        if (foundUserLogin != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует!");
        }
        userLogin.setId(0);
        userLogin.clearRoles();

        Role role1 = new Role(1, "ROLE_ADMIN");
        Role role2 = new Role(2, "ROLE_USER");
        userLogin.addRole(role1);
        userLogin.addRole(role2);

        String encodedPassword = encoder.encode(userLogin.getPassword());
        userLogin.setPassword(encodedPassword);

        return repository.save(userLogin);
    }
}
