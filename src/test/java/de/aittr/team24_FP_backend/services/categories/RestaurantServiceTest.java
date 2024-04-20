package de.aittr.team24_FP_backend.services.categories;

import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.domain.categories.RestaurantsInfo;
import de.aittr.team24_FP_backend.exception_handling.exceptions.*;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class RestaurantServiceTest {
    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    private RestaurantsInfo restaurantsInfo;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantsInfo = new RestaurantsInfo();

    }

//    @Test
//    void saveTest() {
//        restaurantsInfo.setId(1);
//        when(cityRepository.findByName(anyString())).thenReturn(new City());
//        when(restaurantRepository.save(any())).thenReturn(restaurantsInfo);
//
//        RestaurantsInfo savedRestaurantsInfo = restaurantService.save(new RestaurantsInfo(), "Berlin");
//
//        assertEquals(1, savedRestaurantsInfo.getId());
//    }

    @Test
    void findByExistingIdTest() {
        restaurantsInfo.setId(1);
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurantsInfo));

        RestaurantsInfo foundRestaurantsInfo = restaurantService.findById(1);

        assertEquals(1, foundRestaurantsInfo.getId());
    }

    @Test
    void findByNotExistingIdTest() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RestaurantsNotFoundException.class, () -> restaurantService.findById(1));
    }

    @Test
    void findAllTest() {
        List<RestaurantsInfo> restaurantsList = new ArrayList<>();
        restaurantsList.add(new RestaurantsInfo());
        restaurantsList.add(new RestaurantsInfo());
        when(restaurantRepository.findByCityName(anyString())).thenReturn(restaurantsList);

        List<RestaurantsInfo> foundRestaurantsList = restaurantService.findAll("Berlin");

        assertEquals(restaurantsList.size(), foundRestaurantsList.size());
        verify(restaurantRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    void findSortAllTest() {
        List<RestaurantsInfo> restaurantsList = new ArrayList<>();
        restaurantsList.add(new RestaurantsInfo("a title"));
        restaurantsList.add(new RestaurantsInfo("b title"));
        restaurantsList.add(new RestaurantsInfo("c title"));

        when(restaurantRepository.findSortAll("Berlin")).thenReturn(restaurantsList);

        List<RestaurantsInfo> sortedRestaurantsList = restaurantService.findSortAll("Berlin");

        assertEquals("a title", sortedRestaurantsList.get(0).getTitle());
        assertEquals("b title", sortedRestaurantsList.get(1).getTitle());
        assertEquals("c title", sortedRestaurantsList.get(2).getTitle());

        verify(restaurantRepository, times(1)).findSortAll("Berlin");
    }


    @Test
    void deleteByExistingIdTest() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(new RestaurantsInfo()));

        restaurantService.deleteById(1);

        verify(restaurantRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteByNotExistingIdTest() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RestaurantsNotFoundException.class, () -> restaurantService.deleteById(1));
    }


    @Test
    void findByValidTitleTest() {
        List<RestaurantsInfo> restaurantsList = new ArrayList<>();
        restaurantsList.add(new RestaurantsInfo("a title"));
        restaurantsList.add(new RestaurantsInfo("b title"));
        restaurantsList.add(new RestaurantsInfo("a title"));

        when(restaurantRepository.findByCityName("Berlin")).thenReturn(restaurantsList);

        List<RestaurantsInfo> foundRestaurantsList = restaurantService.findByTitle("a title", "Berlin");

        assertEquals(2, foundRestaurantsList.size());
        assertEquals("a title", foundRestaurantsList.get(0).getTitle());
        assertEquals("a title", foundRestaurantsList.get(1).getTitle());

        verify(restaurantRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    void findByNotValidTitleTest() {
        String title = "title";
        when(restaurantRepository.findByCityName("Berlin")).thenReturn(new ArrayList<>());

        assertThrows(RestaurantsNotFoundException.class, () -> restaurantService.findByTitle(String.format(
                "There is no Restaurant with name [%s] in the database", title), "Berlin"));

        verify(restaurantRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    public void setStatusTest() {
        restaurantsInfo.setId(1);
        restaurantsInfo.setStatus(0);

        when(restaurantRepository.findById(any(Integer.class))).thenReturn(Optional.of(restaurantsInfo));

        restaurantService.setStatus(1, 1);

        assertEquals(restaurantsInfo.getStatus(), Integer.valueOf(1));
        verify(restaurantRepository, times(1)).findById(1);
    }

    @Test
    void updateValidInfo() {
        restaurantsInfo.setId(1);

        when(restaurantRepository.save(restaurantsInfo)).thenReturn(restaurantsInfo);
        when(cityRepository.findByName("Berlin")).thenReturn(new City());

        restaurantService.update(restaurantsInfo, "Berlin");

        verify(restaurantRepository, times(1)).save(restaurantsInfo);
    }

//    @Test
//    void updateInvalidInfo() {
//        restaurantsInfo.setId(1);
//
//        when(cityRepository.findByName("Derlin")).thenReturn(null);
//
//        assertThrows(RestaurantUpdateException.class, () -> restaurantService.update(restaurantsInfo, "Derlin"));
//
//        verify(cityRepository, times(1)).findByName("Derlin");
//    }
}