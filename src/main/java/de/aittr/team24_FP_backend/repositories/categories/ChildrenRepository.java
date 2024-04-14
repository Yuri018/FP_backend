package de.aittr.team24_FP_backend.repositories.categories;

import de.aittr.team24_FP_backend.domain.categories.ChildrenInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChildrenRepository extends JpaRepository<ChildrenInfo, Integer> {

    @Query(value = "SELECT i.* " +
            "FROM children_info i " +
            "INNER JOIN city c ON i.city_id = c.id " +
            "WHERE c.name = :cityName " +
            "ORDER BY i.status DESC, i.title ASC", nativeQuery = true)
    List<ChildrenInfo> findSortAll(String cityName);

    List<ChildrenInfo> findByCityName(String name);

}
