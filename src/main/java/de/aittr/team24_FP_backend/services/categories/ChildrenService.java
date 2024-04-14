package de.aittr.team24_FP_backend.services.categories;

import de.aittr.team24_FP_backend.domain.categories.ChildrenInfo;
import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.exception_handling.exceptions.ChildrenNotFoundException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.ChildrenUpdateException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.ChildrenValidationException;
import de.aittr.team24_FP_backend.repositories.categories.ChildrenRepository;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.services.email.DatabaseChangeListenerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildrenService {
    private ChildrenRepository childrenRepository;
    private CityRepository cityRepository;
    private DatabaseChangeListenerService databaseChangeListenerService;

    public ChildrenService(ChildrenRepository childrenRepository, CityRepository cityRepository, DatabaseChangeListenerService databaseChangeListenerService) {
        this.childrenRepository = childrenRepository;
        this.cityRepository = cityRepository;
        this.databaseChangeListenerService = databaseChangeListenerService;
    }

    public ChildrenInfo save(ChildrenInfo childrenInfo, String cityName) {
        try {
            childrenInfo.setId(0);
            City city = cityRepository.findByName(cityName);
            childrenInfo.setCity(city);
            ChildrenInfo savedChildrenInfo = childrenRepository.save(childrenInfo);

            databaseChangeListenerService.handleDatabaseChangeChildrenInfo(cityName, savedChildrenInfo);
            return savedChildrenInfo;

        } catch (Exception e) {
            throw new ChildrenValidationException("Incorrect values of children fields", e);
        }
    }

    public ChildrenInfo findById (int id) {
        ChildrenInfo childrenInfo = childrenRepository.findById(id).orElse(null);

        if (childrenInfo == null) {
            throw new ChildrenNotFoundException(String.format(
                    "There is no children with id [%d] in the database", id));
        }
        return childrenInfo;
    }

    public List<ChildrenInfo> findAll(String cityName) {
        return childrenRepository.findByCityName(cityName);
    }
    public List<ChildrenInfo> findSortAll(String cityName) {
        return childrenRepository.findSortAll(cityName);
    }


    @Transactional
    public void setStatus(Integer id, Integer status) {
        ChildrenInfo childrenInfo = childrenRepository.findById(id).orElse(null);

        if (childrenInfo != null) {
            childrenInfo.setStatus(status);
        } else {
            throw new ChildrenNotFoundException(String.format(
                    "There is no children with id [%d] in the database", id));
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        ChildrenInfo childrenInfo = childrenRepository.findById(id).orElse(null);

        if (childrenInfo != null) {
            childrenRepository.deleteById(id);
        } else {
            throw new ChildrenNotFoundException(String.format(
                    "There is no children with id [%d] in the database", id));
        }
    }

    public List<ChildrenInfo> findByTitle(String title, String cityName) {
        List<ChildrenInfo> list = findAll(cityName).stream().filter((r) -> r.getTitle().trim().equalsIgnoreCase(title.trim())).toList();
        if (list.isEmpty()) {
            throw new ChildrenNotFoundException(String.format(
                    "There is no children with name [%s] in the database", title));
        }
        return list;
    }

    public void update(ChildrenInfo children, String cityName) {
        try {
            City city = cityRepository.findByName(cityName);
            children.setCity(city);
            childrenRepository.save(children);
        } catch (Exception e) {
            throw new ChildrenUpdateException("Error updating children: " + e.getMessage());
        }

    }

}
