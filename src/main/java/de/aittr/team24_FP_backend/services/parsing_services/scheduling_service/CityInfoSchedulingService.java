package de.aittr.team24_FP_backend.services.parsing_services.scheduling_service;

import de.aittr.team24_FP_backend.services.parsing_services.categories_parsing.*;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CityInfoSchedulingService {
    DoctorsInfoBerlinParsingService doctorsInfoBerlinParsingService;
    ShopsInfoBerlinParsingService shopsInfoBerlinParsingService;
    ChildrenInfoParsingService childrenInfoParsingService;
    HairBeautyInfoParsingService hairBeautyInfoParsingService;
    TranslatorsInfoParsingService translatorsInfoParsingService;
    RestaurantsInfoParsingService restaurantsInfoParsingService;
    LegalServicesInfoParsingService legalServicesInfoParsingService;

    public CityInfoSchedulingService(
            DoctorsInfoBerlinParsingService doctorsInfoBerlinParsingService,
            ShopsInfoBerlinParsingService shopsInfoBerlinParsingService,
            ChildrenInfoParsingService childrenInfoParsingService,
            HairBeautyInfoParsingService hairBeautyInfoParsingService,
            TranslatorsInfoParsingService translatorsInfoParsingService,
            RestaurantsInfoParsingService restaurantsInfoParsingService,
            LegalServicesInfoParsingService legalServicesInfoParsingService) {
        this.doctorsInfoBerlinParsingService = doctorsInfoBerlinParsingService;
        this.shopsInfoBerlinParsingService = shopsInfoBerlinParsingService;
        this.childrenInfoParsingService = childrenInfoParsingService;
        this.hairBeautyInfoParsingService = hairBeautyInfoParsingService;
        this.translatorsInfoParsingService = translatorsInfoParsingService;
        this.restaurantsInfoParsingService = restaurantsInfoParsingService;
        this.legalServicesInfoParsingService = legalServicesInfoParsingService;
    }

    @Scheduled(cron = "10 * * * * *")
    @Transactional
    public void saveDocInfoToDbByCronTask1() {
        doctorsInfoBerlinParsingService.doctorsInfoBerlinTerapeutParsingSaveService();
    }

    @Scheduled(cron = "10 * * * * *")
    @Transactional
    public void saveShopInfoToDbByCronTask2() {
        shopsInfoBerlinParsingService.shopsInfoBerlinParsingSaveService();
    }

    @Scheduled(cron = "10 * * * * *")
    @Transactional
    public void saveDocInfoToDbByCronTask3() {
        doctorsInfoBerlinParsingService.doctorsInfoBerlinPediatristParsingSaveService();
    }

    @Scheduled(cron = "10 * * * * *")
    @Transactional
    public void saveDocInfoToDbByCronTask4() {
        doctorsInfoBerlinParsingService.doctorsInfoBerlinStomatologistParsingSaveService();
    }

    @Scheduled(cron = "10 * * * * *")
    @Transactional
    public void saveDocInfoToDbByCronTask5() {
        doctorsInfoBerlinParsingService.doctorsInfoBerlinOrthopedistParsingSaveService();
    }

    @Scheduled(cron = "10 * * * * *")
    @Transactional
    public void saveDocInfoToDbByCronTask6() {
        doctorsInfoBerlinParsingService.doctorsInfoBerlinCardiologistParsingSaveService();
    }

    @Scheduled(cron = "10 * * * * *")
    @Transactional
    public void saveDocInfoToDbByCronTask7() {
        doctorsInfoBerlinParsingService.doctorsInfoBerlinDermatologistParsingSaveService();
    }

    @Scheduled(cron = "10 * * * * *")
    @Transactional
    public void saveDocInfoToDbByCronTask8() {
        doctorsInfoBerlinParsingService.doctorsInfoBerlinOculistParsingSaveService();
    }

    @Scheduled(cron = "10 * * * * *")
    @Transactional
    public void saveChildrenInfoToDbByCronTask9() {
        childrenInfoParsingService.childrenInfoBerlinParsingSaveService();
    }

    @Scheduled(cron = "10 * * * * *")
    @Transactional
    public void saveHairBeautyInfoToDbByCronTask10() {
        hairBeautyInfoParsingService.hairBeautyInfoBerlinParsingSaveService();
    }

    @Scheduled(cron = "10 * * * * *")
    @Transactional
    public void saveTranslatorsInfoToDbByCronTask11() {
        translatorsInfoParsingService.translatorsInfoBerlinParsingSaveService();
    }

    @Scheduled(cron = "10 * * * * *")
    @Transactional
    public void saveRestaurantsInfoToDbByCronTask12() {
        restaurantsInfoParsingService.restaurantsInfoBerlinParsingSaveService();
    }

    @Scheduled(cron = "10 * * * * *")
    @Transactional
    public void saveLegalServicesInfoToDbByCronTask13() {
        legalServicesInfoParsingService.legalServicesInfoBerlinParsingSaveService();
    }
}
