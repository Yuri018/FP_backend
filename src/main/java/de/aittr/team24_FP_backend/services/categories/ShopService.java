package de.aittr.team24_FP_backend.services.categories;

import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.domain.categories.ShopsInfo;
import de.aittr.team24_FP_backend.exception_handling.exceptions.*;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.ShopRepository;
import de.aittr.team24_FP_backend.services.email.DatabaseChangeListenerService;
import de.aittr.team24_FP_backend.services.email.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private ShopRepository shopRepository;
    private CityRepository cityRepository;
    private DatabaseChangeListenerService databaseChangeListenerService;

    public ShopService(ShopRepository shopRepository, CityRepository cityRepository, DatabaseChangeListenerService databaseChangeListenerService) {
        this.shopRepository = shopRepository;
        this.cityRepository = cityRepository;
        this.databaseChangeListenerService = databaseChangeListenerService;
    }

    public ShopsInfo save(ShopsInfo shopsInfo, String cityName) {
        try {
            shopsInfo.setId(0);
            City city = cityRepository.findByName(cityName);
            shopsInfo.setCity(city);
            ShopsInfo savedShopsInfo = shopRepository.save(shopsInfo);

            databaseChangeListenerService.handleDatabaseChangeShopsInfo(cityName, savedShopsInfo);
            return savedShopsInfo;
        } catch (Exception e) {
            throw new ShopValidationException("Incorrect values of shop fields", e);
        }
    }

    public ShopsInfo findById (int id) {
        ShopsInfo shop = shopRepository.findById(id).orElse(null);

        if (shop == null) {
            throw new ShopsNotFoundException(String.format(
                    "There is no shops with id [%d] in the database", id));
        }
        return shop;
    }

    public List<ShopsInfo> findAll(String cityName) {
        return shopRepository.findByCityName(cityName);
    }
    public List<ShopsInfo> findSortAll(String cityName) {
        return shopRepository.findSortAll(cityName);
    }


    @Transactional
    public void setStatus(Integer id, Integer status) {
        ShopsInfo shop = shopRepository.findById(id).orElse(null);

        if (shop != null) {
            shop.setStatus(status);
        } else {
            throw new ShopsNotFoundException(String.format(
                    "There is no shops with id [%d] in the database", id));
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        ShopsInfo shop = shopRepository.findById(id).orElse(null);

        if (shop != null) {
            shopRepository.deleteById(id);
        } else {
            throw new ShopsNotFoundException(String.format(
                    "There is no shops with id [%d] in the database", id));
        }
    }

    public List<ShopsInfo> findByTitle(String title, String cityName) {
        List<ShopsInfo> list = findAll(cityName).stream().filter((r) -> r.getTitle().trim().equalsIgnoreCase(title.trim())).toList();
        if (list.isEmpty()) {
            throw new ShopsNotFoundException(String.format(
                    "There is no shops with name [%s] in the database", title));
        }
        return list;
    }

    public void update(ShopsInfo shop, String cityName) {
        try {
            City city = cityRepository.findByName(cityName);
            shop.setCity(city);
            shopRepository.save(shop);
        } catch (Exception e) {
            throw new ShopUpdateException("Error updating shop: " + e.getMessage());
        }

    }
}
