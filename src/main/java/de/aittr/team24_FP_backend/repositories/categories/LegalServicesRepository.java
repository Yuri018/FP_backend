package de.aittr.team24_FP_backend.repositories.categories;

import de.aittr.team24_FP_backend.domain.categories.LegalServicesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LegalServicesRepository extends JpaRepository<LegalServicesInfo, Integer> {
    @Query(value = "SELECT i.* " +
            "FROM legal_services_info i " +
            "INNER JOIN city c ON i.city_id = c.id " +
            "WHERE c.name = :cityName " +
            "ORDER BY i.status DESC, i.title ASC", nativeQuery = true)
    List<LegalServicesInfo> findSortAll(String cityName);

    List<LegalServicesInfo> findByCityName(String name);

}
