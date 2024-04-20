package de.aittr.team24_FP_backend.services.categories;

import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.domain.categories.LegalServicesInfo;
import de.aittr.team24_FP_backend.exception_handling.exceptions.LegalServiceNotFoundException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.LegalServiceUpdateException;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.LegalServicesRepository;
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

class LegalServicesServiceTest {
    @Mock
    private LegalServicesRepository legalServicesRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private LegalServicesService legalServicesService;

    private LegalServicesInfo legalServicesInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        legalServicesInfo = new LegalServicesInfo();

    }

//    @Test
//    void saveTest() {
//        legalServicesInfo.setId(1);
//        when(cityRepository.findByName(anyString())).thenReturn(new City());
//        when(legalServicesRepository.save(any())).thenReturn(legalServicesInfo);
//
//        LegalServicesInfo savedLegalServicesInfo = legalServicesService.save(new LegalServicesInfo(), "Berlin");
//
//        assertEquals(1, savedLegalServicesInfo.getId());
//    }

    @Test
    void findByExistingIdTest() {
        legalServicesInfo.setId(1);
        when(legalServicesRepository.findById(1)).thenReturn(Optional.of(legalServicesInfo));

        LegalServicesInfo foundLegalServicesInfo = legalServicesService.findById(1);

        assertEquals(1, foundLegalServicesInfo.getId());
    }

    @Test
    void findByNotExistingIdTest() {
        when(legalServicesRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(LegalServiceNotFoundException.class, () -> legalServicesService.findById(1));
    }

    @Test
    void findAllTest() {
        List<LegalServicesInfo> legalServicesList = new ArrayList<>();
        legalServicesList.add(new LegalServicesInfo());
        legalServicesList.add(new LegalServicesInfo());
        when(legalServicesRepository.findByCityName(anyString())).thenReturn(legalServicesList);

        List<LegalServicesInfo> foundLegalServicesList = legalServicesService.findAll("Berlin");

        assertEquals(legalServicesList.size(), foundLegalServicesList.size());
        verify(legalServicesRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    void findSortAllTest() {
        List<LegalServicesInfo> legalServicesList = new ArrayList<>();
        legalServicesList.add(new LegalServicesInfo("a title"));
        legalServicesList.add(new LegalServicesInfo("b title"));
        legalServicesList.add(new LegalServicesInfo("c title"));

        when(legalServicesRepository.findSortAll("Berlin")).thenReturn(legalServicesList);

        List<LegalServicesInfo> sortedLegalServicesList = legalServicesService.findSortAll("Berlin");

        assertEquals("a title", sortedLegalServicesList.get(0).getTitle());
        assertEquals("b title", sortedLegalServicesList.get(1).getTitle());
        assertEquals("c title", sortedLegalServicesList.get(2).getTitle());

        verify(legalServicesRepository, times(1)).findSortAll("Berlin");
    }


    @Test
    void deleteByExistingIdTest() {
        when(legalServicesRepository.findById(1)).thenReturn(Optional.of(new LegalServicesInfo()));

        legalServicesService.deleteById(1);

        verify(legalServicesRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteByNotExistingIdTest() {
        when(legalServicesRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(LegalServiceNotFoundException.class, () -> legalServicesService.deleteById(1));
    }


    @Test
    void findByValidTitleTest() {
        List<LegalServicesInfo> legalServicesList = new ArrayList<>();
        legalServicesList.add(new LegalServicesInfo("a title"));
        legalServicesList.add(new LegalServicesInfo("b title"));
        legalServicesList.add(new LegalServicesInfo("a title"));

        when(legalServicesRepository.findByCityName("Berlin")).thenReturn(legalServicesList);

        List<LegalServicesInfo> foundLegalServicesList = legalServicesService.findByTitle("a title", "Berlin");

        assertEquals(2, foundLegalServicesList.size());
        assertEquals("a title", foundLegalServicesList.get(0).getTitle());
        assertEquals("a title", foundLegalServicesList.get(1).getTitle());

        verify(legalServicesRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    void findByNotValidTitleTest() {
        String title = "title";
        when(legalServicesRepository.findByCityName("Berlin")).thenReturn(new ArrayList<>());

        assertThrows(LegalServiceNotFoundException.class, () -> legalServicesService.findByTitle(String.format(
                "There is no legal Services with name [%s] in the database", title), "Berlin"));

        verify(legalServicesRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    public void setStatusTest() {
        legalServicesInfo.setId(1);
        legalServicesInfo.setStatus(0);

        when(legalServicesRepository.findById(any(Integer.class))).thenReturn(Optional.of(legalServicesInfo));

        legalServicesService.setStatus(1, 1);

        assertEquals(legalServicesInfo.getStatus(), Integer.valueOf(1));
        verify(legalServicesRepository, times(1)).findById(1);
    }

    @Test
    void updateValidInfo() {
        legalServicesInfo.setId(1);

        when(legalServicesRepository.save(legalServicesInfo)).thenReturn(legalServicesInfo);
        when(cityRepository.findByName("Berlin")).thenReturn(new City());

        legalServicesService.update(legalServicesInfo, "Berlin");

        verify(legalServicesRepository, times(1)).save(legalServicesInfo);
    }

//    @Test
//    void updateInvalidInfo() {
//        legalServicesInfo.setId(1);
//
//        when(cityRepository.findByName("Derlin")).thenReturn(null);
//
//        assertThrows(LegalServiceUpdateException.class, () -> legalServicesService.update(legalServicesInfo, "Derlin"));
//
//        verify(cityRepository, times(1)).findByName("Derlin");
//
//    }

}