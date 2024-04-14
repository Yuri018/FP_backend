package de.aittr.team24_FP_backend.repositories.categories;

import de.aittr.team24_FP_backend.domain.categories.HairBeautyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HairBeautyRepository extends JpaRepository<HairBeautyInfo, Integer> {
    @Query(value = "SELECT i.* " +
            "FROM hair_beauty_info i " +
            "INNER JOIN city c ON i.city_id = c.id " +
            "WHERE c.name = :cityName " +
            "ORDER BY i.status DESC, i.title ASC", nativeQuery = true)
    List<HairBeautyInfo> findSortAll(String cityName);

    List<HairBeautyInfo> findByCityName(String name);

}
