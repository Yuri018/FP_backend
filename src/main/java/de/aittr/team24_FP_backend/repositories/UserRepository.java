package de.aittr.team24_FP_backend.repositories;

import de.aittr.team24_FP_backend.domain.User;
import de.aittr.team24_FP_backend.domain.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

}
