package de.aittr.team24_FP_backend.repositories.categories;

import de.aittr.team24_FP_backend.domain.categories.ShopsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<ShopsInfo, Integer> {

    @Query(value = "SELECT i.* " +
            "FROM shops_info i " +
            "INNER JOIN city c ON i.city_id = c.id " +
            "WHERE c.name = :cityName " +
            "ORDER BY i.status DESC, i.title ASC", nativeQuery = true)
    List<ShopsInfo> findSortAll(String cityName);

    List<ShopsInfo> findByCityName(String name);

}
