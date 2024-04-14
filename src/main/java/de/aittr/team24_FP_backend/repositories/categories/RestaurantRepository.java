package de.aittr.team24_FP_backend.repositories.categories;

import de.aittr.team24_FP_backend.domain.categories.RestaurantsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantsInfo, Integer> {

    @Query(value = "SELECT ri.* " +
            "FROM restaurants_info ri " +
            "INNER JOIN city c ON ri.city_id = c.id " +
            "WHERE c.name = :cityName " +
            "ORDER BY ri.status DESC, ri.title ASC", nativeQuery = true)
    List<RestaurantsInfo> findSortAll(String cityName);

    List<RestaurantsInfo> findByCityName(String name);
}
