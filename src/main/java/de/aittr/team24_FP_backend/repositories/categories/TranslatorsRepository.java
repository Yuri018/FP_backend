package de.aittr.team24_FP_backend.repositories.categories;

import de.aittr.team24_FP_backend.domain.categories.TranslatorsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TranslatorsRepository extends JpaRepository<TranslatorsInfo, Integer> {
    @Query(value = "SELECT i.* " +
            "FROM translators_info i " +
            "INNER JOIN city c ON i.city_id = c.id " +
            "WHERE c.name = :cityName " +
            "ORDER BY i.status DESC, i.title ASC", nativeQuery = true)
    List<TranslatorsInfo> findSortAll(String cityName);

    List<TranslatorsInfo> findByCityName(String name);

}
