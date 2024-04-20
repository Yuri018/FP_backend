package de.aittr.team24_FP_backend.services.categories;

import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.exception_handling.exceptions.ChildrenUpdateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import de.aittr.team24_FP_backend.domain.categories.ChildrenInfo;
import de.aittr.team24_FP_backend.exception_handling.exceptions.ChildrenNotFoundException;
import de.aittr.team24_FP_backend.repositories.categories.ChildrenRepository;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ChildrenServiceTest {

    @Mock
    private ChildrenRepository childrenRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private ChildrenService childrenService;

    private ChildrenInfo childrenInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        childrenInfo = new ChildrenInfo();

    }

//    @Test
//    void saveTest() {
//        childrenInfo.setId(1);
//        when(cityRepository.findByName(anyString())).thenReturn(new City());
//        when(childrenRepository.save(any())).thenReturn(childrenInfo);
//
//        ChildrenInfo savedChildrenInfo = childrenService.save(new ChildrenInfo(), "Berlin");
//
//        assertEquals(1, savedChildrenInfo.getId());
//    }

    @Test
    void findByExistingIdTest() {
        childrenInfo.setId(1);
        when(childrenRepository.findById(1)).thenReturn(Optional.of(childrenInfo));

        ChildrenInfo foundChildrenInfo = childrenService.findById(1);

        assertEquals(1, foundChildrenInfo.getId());
    }

    @Test
    void findByNotExistingIdTest() {
        when(childrenRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ChildrenNotFoundException.class, () -> childrenService.findById(1));
    }

    @Test
    void findAllTest() {
        List<ChildrenInfo> childrenList = new ArrayList<>();
        childrenList.add(new ChildrenInfo());
        childrenList.add(new ChildrenInfo());
        when(childrenRepository.findByCityName(anyString())).thenReturn(childrenList);

        List<ChildrenInfo> foundChildrenList = childrenService.findAll("Berlin");

        assertEquals(childrenList.size(), foundChildrenList.size());
        verify(childrenRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    void findSortAllTest() {
        List<ChildrenInfo> childrenList = new ArrayList<>();
        childrenList.add(new ChildrenInfo("a title"));
        childrenList.add(new ChildrenInfo("b title"));
        childrenList.add(new ChildrenInfo("c title"));

        when(childrenRepository.findSortAll("Berlin")).thenReturn(childrenList);

        List<ChildrenInfo> sortedChildrenList = childrenService.findSortAll("Berlin");

        assertEquals("a title", sortedChildrenList.get(0).getTitle());
        assertEquals("b title", sortedChildrenList.get(1).getTitle());
        assertEquals("c title", sortedChildrenList.get(2).getTitle());

        verify(childrenRepository, times(1)).findSortAll("Berlin");
    }


    @Test
    void deleteByExistingIdTest() {
        when(childrenRepository.findById(1)).thenReturn(Optional.of(new ChildrenInfo()));

        childrenService.deleteById(1);

        verify(childrenRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteByNotExistingIdTest() {
        when(childrenRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ChildrenNotFoundException.class, () -> childrenService.deleteById(1));
    }


    @Test
    void findByValidTitleTest() {
        List<ChildrenInfo> childrenList = new ArrayList<>();
        childrenList.add(new ChildrenInfo("a title"));
        childrenList.add(new ChildrenInfo("b title"));
        childrenList.add(new ChildrenInfo("a title"));

        when(childrenRepository.findByCityName("Berlin")).thenReturn(childrenList);

        List<ChildrenInfo> foundChildrenList = childrenService.findByTitle("a title", "Berlin");

        assertEquals(2, foundChildrenList.size());
        assertEquals("a title", foundChildrenList.get(0).getTitle());
        assertEquals("a title", foundChildrenList.get(1).getTitle());

        verify(childrenRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    void findByNotValidTitleTest() {
        String title = "title";
        when(childrenRepository.findByCityName("Berlin")).thenReturn(new ArrayList<>());

        assertThrows(ChildrenNotFoundException.class, () -> childrenService.findByTitle(String.format(
                "There is no children with name [%s] in the database", title), "Berlin"));

        verify(childrenRepository, times(1)).findByCityName("Berlin");
    }

    @Test
    public void setStatusTest() {
        childrenInfo.setId(1);
        childrenInfo.setStatus(0);

        when(childrenRepository.findById(any(Integer.class))).thenReturn(Optional.of(childrenInfo));

        childrenService.setStatus(1, 1);

        assertEquals(childrenInfo.getStatus(), Integer.valueOf(1));
        verify(childrenRepository, times(1)).findById(1);
    }

    @Test
    void updateValidInfo() {
        childrenInfo.setId(1);

        when(childrenRepository.save(childrenInfo)).thenReturn(childrenInfo);
        when(cityRepository.findByName("Berlin")).thenReturn(new City());

        childrenService.update(childrenInfo, "Berlin");

        verify(childrenRepository, times(1)).save(childrenInfo);
    }

//    @Test
//    void updateInvalidInfo() {
//        ChildrenInfo childrenInfo = new ChildrenInfo();
//        childrenInfo.setId(1);
//
//        when(cityRepository.findByName("Derlin")).thenReturn(null);
//
//        assertThrows(ChildrenUpdateException.class, () -> childrenService.update(childrenInfo, "Derlin"));
//
//        verify(cityRepository, times(1)).findByName("Derlin");
//
//    }
}
