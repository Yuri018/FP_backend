package de.aittr.team24_FP_backend.repositories;

import de.aittr.team24_FP_backend.domain.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {

    UserLogin findByUsername(String username);

    @Query(value = "SELECT ul.* " +
            "FROM user_login ul " +
            "JOIN user u ON ul.id = u.user_login_id " +
            "WHERE u.children_info = true", nativeQuery = true)
    List<UserLogin> findAllByUserChildrenInfoTrue();

    @Query(value = "SELECT ul.* " +
            "FROM user_login ul " +
            "JOIN user u ON ul.id = u.user_login_id " +
            "WHERE u.doctors_info = true", nativeQuery = true)
    List<UserLogin> findAllByUserDoctorsInfoTrue();

    @Query(value = "SELECT ul.* " +
            "FROM user_login ul " +
            "JOIN user u ON ul.id = u.user_login_id " +
            "WHERE u.hair_beauty_info = true", nativeQuery = true)
    List<UserLogin> findAllByUserHairBeautyInfoTrue();

    @Query(value = "SELECT ul.* " +
            "FROM user_login ul " +
            "JOIN user u ON ul.id = u.user_login_id " +
            "WHERE u.legal_services_info = true", nativeQuery = true)
    List<UserLogin> findAllByUserLegalServicesInfoTrue();

//    @Query(value = "SELECT ul.* " +
//            "FROM user_login ul " +
//            "JOIN user u ON ul.id = u.user_login_id " +
//            "WHERE u.pharmacies_info = true", nativeQuery = true)
//    List<UserLogin> findAllByUserPharmaciesInfoTrue();

    @Query(value = "SELECT ul.* " +
            "FROM user_login ul " +
            "JOIN user u ON ul.id = u.user_login_id " +
            "WHERE u.restaurants_info = true", nativeQuery = true)
    List<UserLogin> findAllByUserRestaurantsInfoTrue();

    @Query(value = "SELECT ul.* " +
            "FROM user_login ul " +
            "JOIN user u ON ul.id = u.user_login_id " +
            "WHERE u.shops_info = true", nativeQuery = true)
    List<UserLogin> findAllByUserShopsInfoTrue();

    @Query(value = "SELECT ul.* " +
            "FROM user_login ul " +
            "JOIN user u ON ul.id = u.user_login_id " +
            "WHERE u.translators_info = true", nativeQuery = true)
    List<UserLogin> findAllByUserTranslatorsInfoTrue();

    @Query(value = "SELECT ul.* " +
            "FROM user_login ul " +
            "JOIN user u ON ul.id = u.user_login_id " +
            "WHERE u.news_info = true", nativeQuery = true)
    List<UserLogin> findAllByUserNewsInfoTrue();




//    @Query(value = "SELECT ul.* " +
//            "FROM user_login ul " +
//            "JOIN user u ON ul.id = u.user_login_id " +
//            "WHERE u.children_info = true AND u.berlin = true", nativeQuery = true)
//    List<UserLogin> findAllByUserChildrenAndBerlinTrue();

//    @Query(value = "SELECT ul.* " +
//            "FROM user_login ul " +
//            "JOIN user u ON ul.id = u.user_login_id " +
//            "WHERE u.children_info = true AND u.muenchen = true", nativeQuery = true)
//
//    List<UserLogin> findAllByUserChildrenAndMuenchenTrue();



//    @Query(value = "SELECT ul.* " +
//            "FROM user_login ul " +
//            "JOIN user u ON ul.id = u.user_login_id " +
//            "WHERE u.children_info = true AND u." + ":cityName" + " = true", nativeQuery = true)
//    List<UserLogin> findAllByUserChildrenAndCityTrue(@Param("cityName") String cityName);

}
