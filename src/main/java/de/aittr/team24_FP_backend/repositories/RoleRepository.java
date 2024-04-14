package de.aittr.team24_FP_backend.repositories;

import de.aittr.team24_FP_backend.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
