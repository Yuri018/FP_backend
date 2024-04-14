package de.aittr.team24_FP_backend.services.categories;

import de.aittr.team24_FP_backend.domain.categories.TranslatorsInfo;
import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.exception_handling.exceptions.TranslatorNotFoundException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.TranslatorUpdateException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.TranslatorValidationException;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.TranslatorsRepository;
import de.aittr.team24_FP_backend.services.email.DatabaseChangeListenerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslatorsService {

    private TranslatorsRepository translatorsRepository;
    private CityRepository cityRepository;
    private DatabaseChangeListenerService databaseChangeListenerService;


    public TranslatorsService(TranslatorsRepository translatorsRepository, CityRepository cityRepository, DatabaseChangeListenerService databaseChangeListenerService) {
        this.translatorsRepository = translatorsRepository;
        this.cityRepository = cityRepository;
        this.databaseChangeListenerService = databaseChangeListenerService;
    }

    public TranslatorsInfo save(TranslatorsInfo translators, String cityName) {
        try {
            translators.setId(0);
            City city = cityRepository.findByName(cityName);
            translators.setCity(city);
            TranslatorsInfo saveddTranslatorsInfo = translatorsRepository.save(translators);

            databaseChangeListenerService.handleDatabaseChangeTranslatorsInfo(cityName, saveddTranslatorsInfo);
            return saveddTranslatorsInfo;
        } catch (Exception e) {
            throw new TranslatorValidationException("Incorrect values of translator fields", e);
        }
    }

    public TranslatorsInfo findById (int id) {
        TranslatorsInfo translator = translatorsRepository.findById(id).orElse(null);

        if (translator == null) {
            throw new TranslatorNotFoundException(String.format(
                    "There is no translator with id [%d] in the database", id));
        }
        return translator;
    }

    public List<TranslatorsInfo> findAll(String cityName) {
        return translatorsRepository.findByCityName(cityName);
    }
    public List<TranslatorsInfo> findSortAll(String cityName) {
        return translatorsRepository.findSortAll(cityName);
    }


    @Transactional
    public void setStatus(Integer id, Integer status) {
        TranslatorsInfo translator = translatorsRepository.findById(id).orElse(null);

        if (translator != null) {
            translator.setStatus(status);
        } else {
            throw new TranslatorNotFoundException(String.format(
                    "There is no translator with id [%d] in the database", id));
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        TranslatorsInfo translator = translatorsRepository.findById(id).orElse(null);

        if (translator != null) {
            translatorsRepository.deleteById(id);
        } else {
            throw new TranslatorNotFoundException(String.format(
                    "There is no translator with id [%d] in the database", id));
        }
    }

    public List<TranslatorsInfo> findByTitle(String title, String cityName) {
        List<TranslatorsInfo> list = findAll(cityName).stream().filter((r) -> r.getTitle().trim().equalsIgnoreCase(title.trim())).toList();
        if (list.isEmpty()) {
            throw new TranslatorNotFoundException(String.format(
                    "There is no translator with name [%s] in the database", title));
        }
        return list;
    }

    public void update(TranslatorsInfo translator, String cityName) {
        try {
            City city = cityRepository.findByName(cityName);
            translator.setCity(city);
            translatorsRepository.save(translator);
        } catch (Exception e) {
            throw new TranslatorUpdateException("Error updating translator: " + e.getMessage());
        }

    }

}
