package de.aittr.team24_FP_backend.services.categories;

import de.aittr.team24_FP_backend.domain.categories.DoctorsCategory;
import de.aittr.team24_FP_backend.domain.categories.DoctorsInfo;
import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.exception_handling.exceptions.DoctorUpdateException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.DoctorValidationException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.DoctorNotFoundException;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.DoctorsCategoryRepository;
import de.aittr.team24_FP_backend.repositories.categories.DoctorsRepository;
import de.aittr.team24_FP_backend.services.email.DatabaseChangeListenerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorsService {

    private DoctorsRepository doctorsRepository;
    private CityRepository cityRepository;
    private DoctorsCategoryRepository doctorsCategoryRepository;
    private DatabaseChangeListenerService databaseChangeListenerService;

    public DoctorsService(DoctorsRepository doctorsRepository, CityRepository cityRepository, DoctorsCategoryRepository doctorsCategoryRepository, DatabaseChangeListenerService databaseChangeListenerService) {
        this.doctorsRepository = doctorsRepository;
        this.cityRepository = cityRepository;
        this.doctorsCategoryRepository = doctorsCategoryRepository;
        this.databaseChangeListenerService = databaseChangeListenerService;
    }

    public DoctorsInfo save(DoctorsInfo doctors, String cityName, String doctorCategory) {
        try {
            doctors.setId(0);
            City city = cityRepository.findByName(cityName);
            doctors.setCity(city);

            DoctorsCategory doctorsCategory = doctorsCategoryRepository.findByName(doctorCategory);
            doctors.setDoctorsCategory(doctorsCategory);

            DoctorsInfo savedDoctor = doctorsRepository.save(doctors);

            databaseChangeListenerService.handleDatabaseChangeDoctorsInfo(cityName, savedDoctor);
            return savedDoctor;
        } catch (Exception e) {
            throw new DoctorValidationException("Incorrect values of doctor fields", e);
        }
    }

    public DoctorsInfo findById (int id) {
        DoctorsInfo doctor = doctorsRepository.findById(id).orElse(null);

        if (doctor == null) {
            throw new DoctorNotFoundException(String.format(
                    "There is no doctor with id [%d] in the database", id));
        }
        return doctor;
    }

    public List<DoctorsInfo> findAll(String cityName, String doctorCategory) {
        return doctorsRepository.findByCityNameAndDoctorCategory(cityName, doctorCategory);
    }
    public List<DoctorsInfo> findSortAll(String cityName, String doctorCategory) {
        return doctorsRepository.findSortAll(cityName, doctorCategory);
    }


    @Transactional
    public void setStatus(Integer id, Integer status) {
        DoctorsInfo doctor = doctorsRepository.findById(id).orElse(null);

        if (doctor != null) {
            doctor.setStatus(status);
        } else {
            throw new DoctorNotFoundException(String.format(
                    "There is no doctors with id [%d] in the database", id));
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        DoctorsInfo doctor = doctorsRepository.findById(id).orElse(null);

        if (doctor != null) {
            doctorsRepository.deleteById(id);
        } else {
            throw new DoctorNotFoundException(String.format(
                    "There is no doctors with id [%d] in the database", id));
        }
    }

    public List<DoctorsInfo> findByTitle(String title, String cityName, String doctorCategory) {
        List<DoctorsInfo> list = findAll(cityName, doctorCategory).stream().filter((r) -> r.getTitle().trim().equalsIgnoreCase(title.trim())).toList();
        if (list.isEmpty()) {
            throw new DoctorNotFoundException(String.format(
                    "There is no doctors with name [%s] in the database", title));
        }
        return list;
    }

    public void update(DoctorsInfo doctor, String cityName, String doctorCategory) {
        try {
            City city = cityRepository.findByName(cityName);
            doctor.setCity(city);
            DoctorsCategory doctorsCategory = doctorsCategoryRepository.findByName(doctorCategory);
            doctor.setDoctorsCategory(doctorsCategory);

            doctorsRepository.save(doctor);
        } catch (Exception e) {
            throw new DoctorUpdateException("Error updating doctor: " + e.getMessage());
        }

    }

}
