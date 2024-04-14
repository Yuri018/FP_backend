package de.aittr.team24_FP_backend.repositories.categories;

import de.aittr.team24_FP_backend.domain.categories.DoctorsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorsCategoryRepository extends JpaRepository<DoctorsCategory, Integer> {
    DoctorsCategory findByName(String name);
}
