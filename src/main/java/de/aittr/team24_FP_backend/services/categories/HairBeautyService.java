package de.aittr.team24_FP_backend.services.categories;

import de.aittr.team24_FP_backend.domain.categories.HairBeautyInfo;
import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.exception_handling.exceptions.HairBeautyUpdateException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.HairBeautyValidationException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.HairBeautyNotFoundException;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.HairBeautyRepository;
import de.aittr.team24_FP_backend.services.email.DatabaseChangeListenerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HairBeautyService {

    private HairBeautyRepository hairBeautyRepository;
    private CityRepository cityRepository;
    private DatabaseChangeListenerService databaseChangeListenerService;

    public HairBeautyService(HairBeautyRepository hairBeautyRepository, CityRepository cityRepository, DatabaseChangeListenerService databaseChangeListenerService) {
        this.hairBeautyRepository = hairBeautyRepository;
        this.cityRepository = cityRepository;
        this.databaseChangeListenerService = databaseChangeListenerService;
    }

    public HairBeautyInfo save(HairBeautyInfo hairBeauty, String cityName) {
        try {
            hairBeauty.setId(0);
            City city = cityRepository.findByName(cityName);
            hairBeauty.setCity(city);
            HairBeautyInfo savedHairBeautyInfo = hairBeautyRepository.save(hairBeauty);

            databaseChangeListenerService.handleDatabaseHairBeautyInfo(cityName, savedHairBeautyInfo);
            return savedHairBeautyInfo;
        } catch (Exception e) {
            throw new HairBeautyValidationException("Incorrect values of hairBeauty fields", e);
        }
    }

    public HairBeautyInfo findById (int id) {
        HairBeautyInfo hairBeauty = hairBeautyRepository.findById(id).orElse(null);

        if (hairBeauty == null) {
            throw new HairBeautyNotFoundException(String.format(
                    "There is no hairBeauty with id [%d] in the database", id));
        }
        return hairBeauty;
    }

    public List<HairBeautyInfo> findAll(String cityName) {
        return hairBeautyRepository.findByCityName(cityName);
    }
    public List<HairBeautyInfo> findSortAll(String cityName) {
        return hairBeautyRepository.findSortAll(cityName);
    }


    @Transactional
    public void setStatus(Integer id, Integer status) {
        HairBeautyInfo hairBeauty = hairBeautyRepository.findById(id).orElse(null);

        if (hairBeauty != null) {
            hairBeauty.setStatus(status);
        } else {
            throw new HairBeautyNotFoundException(String.format(
                    "There is no hairBeauty with id [%d] in the database", id));
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        HairBeautyInfo hairBeauty = hairBeautyRepository.findById(id).orElse(null);

        if (hairBeauty != null) {
            hairBeautyRepository.deleteById(id);
        } else {
            throw new HairBeautyNotFoundException(String.format(
                    "There is no hairBeauty with id [%d] in the database", id));
        }
    }

    public List<HairBeautyInfo> findByTitle(String title, String cityName) {
        List<HairBeautyInfo> list = findAll(cityName).stream().filter((r) -> r.getTitle().trim().equalsIgnoreCase(title.trim())).toList();
        if (list.isEmpty()) {
            throw new HairBeautyNotFoundException(String.format(
                    "There is no hairBeauty with name [%s] in the database", title));
        }
        return list;
    }

    public void update(HairBeautyInfo hairBeauty, String cityName) {
        try {
            City city = cityRepository.findByName(cityName);
            hairBeauty.setCity(city);
            hairBeautyRepository.save(hairBeauty);
        } catch (Exception e) {
            throw new HairBeautyUpdateException("Error updating hairBeauty: " + e.getMessage());
        }

    }

}
