package de.aittr.team24_FP_backend.repositories;

import de.aittr.team24_FP_backend.domain.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {

    UserLogin findByUsername(String username);
}
