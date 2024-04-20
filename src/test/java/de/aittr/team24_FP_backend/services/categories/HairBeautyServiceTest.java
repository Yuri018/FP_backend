package de.aittr.team24_FP_backend.services.categories;

import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.domain.categories.HairBeautyInfo;
import de.aittr.team24_FP_backend.exception_handling.exceptions.HairBeautyNotFoundException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.HairBeautyUpdateException;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.HairBeautyRepository;
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

class HairBeautyServiceTest {
    @Mock
    private HairBeautyRepository hairBeautyRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private HairBeautyService hairBeautyService;

    private HairBeautyInfo hairBeautyInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hairBeautyInfo = new HairBeautyInfo();

    }

//    @Test
//    void saveTest() {
//        hairBeautyInfo.setId(1);
//        when(cityRepository.findByName(anyString())).thenReturn(new City());
//        when(hairBeautyRepository.save(any())).thenReturn(hairBeautyInfo);
//
//        HairBeautyInfo savedHairBeautyInfo = hairBeautyService.save(new HairBeautyInfo(), "Berlin");
//
//        assertEquals(1, savedHairBeautyInfo.getId());
//    }

    @Test
    void findByExistingIdTest() {
        hairBeautyInfo.setId(1);
        when(hairBeautyRepository.findById(1)).thenReturn(Optional.of(hairBeautyInfo));

        HairBeautyInfo foundHairBeautyInfo = hairBeautyService.findById(1);

        assertEquals(1, foundHairBeautyInfo.getId());
    }

    @Test
    void findByNotExistingIdTest() {
        when(hairBeautyRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(HairBeautyNotFoundException.class, () -> hairBeautyService.findById(1));
    }

    @Test
    void findAllTest() {
        List<HairBeautyInfo> hairBeautyList = new ArrayList<>();
        hairBeautyList.add(new HairBeautyInfo());
        hairBeautyList.add(new HairBeautyInfo());
        when(hairBeautyRepository.findByCityName(anyString())).thenReturn(hairBeautyList);

        List<HairBeautyInfo> foundHairBeautyList = hairBeautyService.findAll("Berlin");

        assertEquals(hairBeautyList.size(), foundHairBeautyList.size());
        verify(hairBeautyRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    void findSortAllTest() {
        List<HairBeautyInfo> hairBeautyList = new ArrayList<>();
        hairBeautyList.add(new HairBeautyInfo("a title"));
        hairBeautyList.add(new HairBeautyInfo("b title"));
        hairBeautyList.add(new HairBeautyInfo("c title"));

        when(hairBeautyRepository.findSortAll("Berlin")).thenReturn(hairBeautyList);

        List<HairBeautyInfo> sortedHairBeautyList = hairBeautyService.findSortAll("Berlin");

        assertEquals("a title", sortedHairBeautyList.get(0).getTitle());
        assertEquals("b title", sortedHairBeautyList.get(1).getTitle());
        assertEquals("c title", sortedHairBeautyList.get(2).getTitle());

        verify(hairBeautyRepository, times(1)).findSortAll("Berlin");
    }


    @Test
    void deleteByExistingIdTest() {
        when(hairBeautyRepository.findById(1)).thenReturn(Optional.of(new HairBeautyInfo()));

        hairBeautyService.deleteById(1);

        verify(hairBeautyRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteByNotExistingIdTest() {
        when(hairBeautyRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(HairBeautyNotFoundException.class, () -> hairBeautyService.deleteById(1));
    }


    @Test
    void findByValidTitleTest() {
        List<HairBeautyInfo> hairBeautyList = new ArrayList<>();
        hairBeautyList.add(new HairBeautyInfo("a title"));
        hairBeautyList.add(new HairBeautyInfo("b title"));
        hairBeautyList.add(new HairBeautyInfo("a title"));

        when(hairBeautyRepository.findByCityName("Berlin")).thenReturn(hairBeautyList);

        List<HairBeautyInfo> foundHairBeautyList = hairBeautyService.findByTitle("a title", "Berlin");

        assertEquals(2, foundHairBeautyList.size());
        assertEquals("a title", foundHairBeautyList.get(0).getTitle());
        assertEquals("a title", foundHairBeautyList.get(1).getTitle());

        verify(hairBeautyRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    void findByNotValidTitleTest() {
        String title = "title";
        when(hairBeautyRepository.findByCityName("Berlin")).thenReturn(new ArrayList<>());

        assertThrows(HairBeautyNotFoundException.class, () -> hairBeautyService.findByTitle(String.format(
                "There is no hairBeautyService with name [%s] in the database", title), "Berlin"));

        verify(hairBeautyRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    public void setStatusTest() {
        hairBeautyInfo.setId(1);
        hairBeautyInfo.setStatus(0);

        when(hairBeautyRepository.findById(any(Integer.class))).thenReturn(Optional.of(hairBeautyInfo));

        hairBeautyService.setStatus(1, 1);

        assertEquals(hairBeautyInfo.getStatus(), Integer.valueOf(1));
        verify(hairBeautyRepository, times(1)).findById(1);
    }

    @Test
    void updateValidInfo() {
        hairBeautyInfo.setId(1);

        when(hairBeautyRepository.save(hairBeautyInfo)).thenReturn(hairBeautyInfo);
        when(cityRepository.findByName("Berlin")).thenReturn(new City());

        hairBeautyService.update(hairBeautyInfo, "Berlin");

        verify(hairBeautyRepository, times(1)).save(hairBeautyInfo);
    }

//    @Test
//    void updateInvalidInfo() {
//        hairBeautyInfo.setId(1);
//
//        when(cityRepository.findByName("Derlin")).thenReturn(null);
//
//        assertThrows(HairBeautyUpdateException.class, () -> hairBeautyService.update(hairBeautyInfo, "Derlin"));
//
//        verify(cityRepository, times(1)).findByName("Derlin");
//
//    }

}