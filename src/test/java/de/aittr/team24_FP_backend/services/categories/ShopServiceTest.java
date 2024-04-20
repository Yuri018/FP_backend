package de.aittr.team24_FP_backend.services.categories;

import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.domain.categories.ShopsInfo;
import de.aittr.team24_FP_backend.exception_handling.exceptions.*;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.ShopRepository;
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

class ShopServiceTest {
    @Mock
    private ShopRepository shopRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private ShopService shopService;

    private ShopsInfo shopsInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shopsInfo = new ShopsInfo();

    }

//    @Test
//    void saveTest() {
//        shopsInfo.setId(1);
//        when(cityRepository.findByName(anyString())).thenReturn(new City());
//        when(shopRepository.save(any())).thenReturn(shopsInfo);
//
//        ShopsInfo savedShopsInfo = shopService.save(new ShopsInfo(), "Berlin");
//
//        assertEquals(1, savedShopsInfo.getId());
//    }

    @Test
    void findByExistingIdTest() {
        shopsInfo.setId(1);
        when(shopRepository.findById(1)).thenReturn(Optional.of(shopsInfo));

        ShopsInfo foundShopsInfo = shopService.findById(1);

        assertEquals(1, foundShopsInfo.getId());
    }

    @Test
    void findByNotExistingIdTest() {
        when(shopRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ShopsNotFoundException.class, () -> shopService.findById(1));
    }

    @Test
    void findAllTest() {
        List<ShopsInfo> shopsList = new ArrayList<>();
        shopsList.add(new ShopsInfo());
        shopsList.add(new ShopsInfo());
        when(shopRepository.findByCityName(anyString())).thenReturn(shopsList);

        List<ShopsInfo> foundShopsList = shopService.findAll("Berlin");

        assertEquals(shopsList.size(), foundShopsList.size());
        verify(shopRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    void findSortAllTest() {
        List<ShopsInfo> shopsList = new ArrayList<>();
        shopsList.add(new ShopsInfo("a title"));
        shopsList.add(new ShopsInfo("b title"));
        shopsList.add(new ShopsInfo("c title"));

        when(shopRepository.findSortAll("Berlin")).thenReturn(shopsList);

        List<ShopsInfo> sortedShopsList = shopService.findSortAll("Berlin");

        assertEquals("a title", sortedShopsList.get(0).getTitle());
        assertEquals("b title", sortedShopsList.get(1).getTitle());
        assertEquals("c title", sortedShopsList.get(2).getTitle());

        verify(shopRepository, times(1)).findSortAll("Berlin");
    }


    @Test
    void deleteByExistingIdTest() {
        when(shopRepository.findById(1)).thenReturn(Optional.of(new ShopsInfo()));

        shopService.deleteById(1);

        verify(shopRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteByNotExistingIdTest() {
        when(shopRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ShopsNotFoundException.class, () -> shopService.deleteById(1));
    }


    @Test
    void findByValidTitleTest() {
        List<ShopsInfo> shopsList = new ArrayList<>();
        shopsList.add(new ShopsInfo("a title"));
        shopsList.add(new ShopsInfo("b title"));
        shopsList.add(new ShopsInfo("a title"));

        when(shopRepository.findByCityName("Berlin")).thenReturn(shopsList);

        List<ShopsInfo> foundShopsList = shopService.findByTitle("a title", "Berlin");

        assertEquals(2, foundShopsList.size());
        assertEquals("a title", foundShopsList.get(0).getTitle());
        assertEquals("a title", foundShopsList.get(1).getTitle());

        verify(shopRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    void findByNotValidTitleTest() {
        String title = "title";
        when(shopRepository.findByCityName("Berlin")).thenReturn(new ArrayList<>());

        assertThrows(ShopsNotFoundException.class, () -> shopService.findByTitle(String.format(
                "There is no Shop with name [%s] in the database", title), "Berlin"));

        verify(shopRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    public void setStatusTest() {
        shopsInfo.setId(1);
        shopsInfo.setStatus(0);

        when(shopRepository.findById(any(Integer.class))).thenReturn(Optional.of(shopsInfo));

        shopService.setStatus(1, 1);

        assertEquals(shopsInfo.getStatus(), Integer.valueOf(1));
        verify(shopRepository, times(1)).findById(1);
    }

    @Test
    void updateValidInfo() {
        shopsInfo.setId(1);

        when(shopRepository.save(shopsInfo)).thenReturn(shopsInfo);
        when(cityRepository.findByName("Berlin")).thenReturn(new City());

        shopService.update(shopsInfo, "Berlin");

        verify(shopRepository, times(1)).save(shopsInfo);
    }

//    @Test
//    void updateInvalidInfo() {
//        shopsInfo.setId(1);
//
//        when(cityRepository.findByName("Derlin")).thenReturn(null);
//
//        assertThrows(ShopUpdateException.class, () -> shopService.update(shopsInfo, "Derlin"));
//
//        verify(cityRepository, times(1)).findByName("Derlin");
//    }
}