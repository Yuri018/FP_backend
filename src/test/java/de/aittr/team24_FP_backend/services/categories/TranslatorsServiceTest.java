package de.aittr.team24_FP_backend.services.categories;

import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.domain.categories.TranslatorsInfo;
import de.aittr.team24_FP_backend.exception_handling.exceptions.*;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.TranslatorsRepository;
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

class TranslatorsServiceTest {
    @Mock
    private TranslatorsRepository translatorsRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private TranslatorsService translatorsService;

    private TranslatorsInfo translatorsInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        translatorsInfo = new TranslatorsInfo();

    }

//    @Test
//    void saveTest() {
//        translatorsInfo.setId(1);
//        when(cityRepository.findByName(anyString())).thenReturn(new City());
//        when(translatorsRepository.save(any())).thenReturn(translatorsInfo);
//
//        TranslatorsInfo savedTranslatorsInfo = translatorsService.save(new TranslatorsInfo(), "Berlin");
//
//        assertEquals(1, savedTranslatorsInfo.getId());
//    }

    @Test
    void findByExistingIdTest() {
        translatorsInfo.setId(1);
        when(translatorsRepository.findById(1)).thenReturn(Optional.of(translatorsInfo));

        TranslatorsInfo foundTranslatorsInfo = translatorsService.findById(1);

        assertEquals(1, foundTranslatorsInfo.getId());
    }

    @Test
    void findByNotExistingIdTest() {
        when(translatorsRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(TranslatorNotFoundException.class, () -> translatorsService.findById(1));
    }

    @Test
    void findAllTest() {
        List<TranslatorsInfo> translatorsList = new ArrayList<>();
        translatorsList.add(new TranslatorsInfo());
        translatorsList.add(new TranslatorsInfo());
        when(translatorsRepository.findByCityName(anyString())).thenReturn(translatorsList);

        List<TranslatorsInfo> foundTranslatorsList = translatorsService.findAll("Berlin");

        assertEquals(translatorsList.size(), foundTranslatorsList.size());
        verify(translatorsRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    void findSortAllTest() {
        List<TranslatorsInfo> translatorsList = new ArrayList<>();
        translatorsList.add(new TranslatorsInfo("a title"));
        translatorsList.add(new TranslatorsInfo("b title"));
        translatorsList.add(new TranslatorsInfo("c title"));

        when(translatorsRepository.findSortAll("Berlin")).thenReturn(translatorsList);

        List<TranslatorsInfo> sortedTranslatorsList = translatorsService.findSortAll("Berlin");

        assertEquals("a title", sortedTranslatorsList.get(0).getTitle());
        assertEquals("b title", sortedTranslatorsList.get(1).getTitle());
        assertEquals("c title", sortedTranslatorsList.get(2).getTitle());

        verify(translatorsRepository, times(1)).findSortAll("Berlin");
    }


    @Test
    void deleteByExistingIdTest() {
        when(translatorsRepository.findById(1)).thenReturn(Optional.of(new TranslatorsInfo()));

        translatorsService.deleteById(1);

        verify(translatorsRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteByNotExistingIdTest() {
        when(translatorsRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(TranslatorNotFoundException.class, () -> translatorsService.deleteById(1));
    }


    @Test
    void findByValidTitleTest() {
        List<TranslatorsInfo> translatorsList = new ArrayList<>();
        translatorsList.add(new TranslatorsInfo("a title"));
        translatorsList.add(new TranslatorsInfo("b title"));
        translatorsList.add(new TranslatorsInfo("a title"));

        when(translatorsRepository.findByCityName("Berlin")).thenReturn(translatorsList);

        List<TranslatorsInfo> foundTranslatorsList = translatorsService.findByTitle("a title", "Berlin");

        assertEquals(2, foundTranslatorsList.size());
        assertEquals("a title", foundTranslatorsList.get(0).getTitle());
        assertEquals("a title", foundTranslatorsList.get(1).getTitle());

        verify(translatorsRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    void findByNotValidTitleTest() {
        String title = "title";
        when(translatorsRepository.findByCityName("Berlin")).thenReturn(new ArrayList<>());

        assertThrows(TranslatorNotFoundException.class, () -> translatorsService.findByTitle(String.format(
                "There is no Translator with name [%s] in the database", title), "Berlin"));

        verify(translatorsRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    public void setStatusTest() {
        translatorsInfo.setId(1);
        translatorsInfo.setStatus(0);

        when(translatorsRepository.findById(any(Integer.class))).thenReturn(Optional.of(translatorsInfo));

        translatorsService.setStatus(1, 1);

        assertEquals(translatorsInfo.getStatus(), Integer.valueOf(1));
        verify(translatorsRepository, times(1)).findById(1);
    }

    @Test
    void updateValidInfo() {
        translatorsInfo.setId(1);

        when(translatorsRepository.save(translatorsInfo)).thenReturn(translatorsInfo);
        when(cityRepository.findByName("Berlin")).thenReturn(new City());

        translatorsService.update(translatorsInfo, "Berlin");

        verify(translatorsRepository, times(1)).save(translatorsInfo);
    }

//    @Test
//    void updateInvalidInfo() {
//        translatorsInfo.setId(1);
//
//        when(cityRepository.findByName("Derlin")).thenReturn(null);
//
//        assertThrows(TranslatorUpdateException.class, () -> translatorsService.update(translatorsInfo, "Derlin"));
//
//        verify(cityRepository, times(1)).findByName("Derlin");
//    }
}