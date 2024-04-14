package de.aittr.team24_FP_backend.services.categories;

import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.domain.categories.RestaurantsInfo;
import de.aittr.team24_FP_backend.exception_handling.exceptions.RestaurantUpdateException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.RestaurantValidationException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.RestaurantsNotFoundException;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.RestaurantRepository;
import de.aittr.team24_FP_backend.services.email.DatabaseChangeListenerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private CityRepository cityRepository;
    private DatabaseChangeListenerService databaseChangeListenerService;


    public RestaurantService(RestaurantRepository restaurantRepository, CityRepository cityRepository, DatabaseChangeListenerService databaseChangeListenerService) {
        this.restaurantRepository = restaurantRepository;
        this.cityRepository = cityRepository;
        this.databaseChangeListenerService = databaseChangeListenerService;
    }

    public RestaurantsInfo save(RestaurantsInfo restaurant, String cityName) {
        try {
            restaurant.setId(0);
            City city = cityRepository.findByName(cityName);
            restaurant.setCity(city);
            RestaurantsInfo savedRestaurantsInfo = restaurantRepository.save(restaurant);

            databaseChangeListenerService.handleDatabaseChangeRestaurantsInfo(cityName, savedRestaurantsInfo);
            return savedRestaurantsInfo;
        } catch (Exception e) {
            throw new RestaurantValidationException("Incorrect values of restaurant fields", e);
        }
    }

    public RestaurantsInfo findById (int id) {
        RestaurantsInfo restaurant = restaurantRepository.findById(id).orElse(null);

        if (restaurant == null) {
            throw new RestaurantsNotFoundException(String.format(
                    "There is no restaurants with id [%d] in the database", id));
        }
        return restaurant;
    }

    public List<RestaurantsInfo> findAll(String cityName) {
        return restaurantRepository.findByCityName(cityName);
    }
    public List<RestaurantsInfo> findSortAll(String cityName) {
        return restaurantRepository.findSortAll(cityName);
    }


    @Transactional
    public void setStatus(Integer id, Integer status) {
        RestaurantsInfo restaurant = restaurantRepository.findById(id).orElse(null);

        if (restaurant != null) {
            restaurant.setStatus(status);
        } else {
            throw new RestaurantsNotFoundException(String.format(
                    "There is no restaurants with id [%d] in the database", id));
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        RestaurantsInfo restaurant = restaurantRepository.findById(id).orElse(null);

        if (restaurant != null) {
            restaurantRepository.deleteById(id);
        } else {
            throw new RestaurantsNotFoundException(String.format(
                    "There is no restaurants with id [%d] in the database", id));
        }
    }

    public List<RestaurantsInfo> findByTitle(String title, String cityName) {
        List<RestaurantsInfo> list = findAll(cityName).stream().filter((r) -> r.getTitle().trim().equalsIgnoreCase(title.trim())).toList();
        if (list.isEmpty()) {
            throw new RestaurantsNotFoundException(String.format(
                    "There is no restaurants with name [%s] in the database", title));
        }
        return list;
    }

    public void update(RestaurantsInfo restaurant, String cityName) {
        try {
            City city = cityRepository.findByName(cityName);
            restaurant.setCity(city);
            restaurantRepository.save(restaurant);
        } catch (Exception e) {
            throw new RestaurantUpdateException("Error updating restaurant: " + e.getMessage());
        }

    }
}
