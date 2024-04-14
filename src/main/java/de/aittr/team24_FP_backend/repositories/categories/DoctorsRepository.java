package de.aittr.team24_FP_backend.repositories.categories;

import de.aittr.team24_FP_backend.domain.categories.DoctorsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorsRepository extends JpaRepository<DoctorsInfo, Integer> {
//    @Query(value = "SELECT i.* " +
//            "FROM doctors_info i " +
//            "INNER JOIN city c ON i.city_id = c.id " +
//            "WHERE c.name = :cityName " +
//            "ORDER BY i.status DESC, i.title ASC", nativeQuery = true)
//    List<DoctorsInfo> findSortAll(String cityName);

    @Query(value = "SELECT i.* " +
            "FROM doctors_info i " +
            "INNER JOIN city c ON i.city_id = c.id " +
            "INNER JOIN doctors_category dc ON i.doctors_category_id = dc.id " +
            "WHERE c.name = :cityName " +
            "AND dc.name = :doctorCategory " +
            "ORDER BY i.status DESC, i.title ASC", nativeQuery = true)
    List<DoctorsInfo> findSortAll(String cityName, String doctorCategory);


    @Query(value = "SELECT i.* " +
            "FROM doctors_info i " +
            "INNER JOIN city c ON i.city_id = c.id " +
            "INNER JOIN doctors_category dc ON i.doctors_category_id = dc.id " +
            "WHERE c.name = :cityName " +
            "AND dc.name = :doctorCategory ", nativeQuery = true)
    List<DoctorsInfo> findByCityNameAndDoctorCategory(String cityName, String doctorCategory);

    List<DoctorsInfo> findByCityName(String name);

}
