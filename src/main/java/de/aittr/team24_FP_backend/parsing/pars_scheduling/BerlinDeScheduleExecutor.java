package de.aittr.team24_FP_backend.parsing.pars_scheduling;

import de.aittr.team24_FP_backend.parsing.pars_services.BerlinDeCultEventsParsingService;
import de.aittr.team24_FP_backend.parsing.pars_services.BerlinDeExhibitionsParsingService;
import de.aittr.team24_FP_backend.parsing.pars_services.BerlinDePoliticActualsParsingServices;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class BerlinDeScheduleExecutor {

    private BerlinDeCultEventsParsingService cultEventsParsingService;
    private BerlinDeExhibitionsParsingService exhibitionsParsingService;
    private BerlinDePoliticActualsParsingServices politActualsParsngService;

    public BerlinDeScheduleExecutor(BerlinDeCultEventsParsingService cultEventsParsingService, BerlinDeExhibitionsParsingService exhibitionsParsingService, BerlinDePoliticActualsParsingServices politActualsParsngService) {
        this.cultEventsParsingService = cultEventsParsingService;
        this.exhibitionsParsingService = exhibitionsParsingService;
        this.politActualsParsngService = politActualsParsngService;
    }

    @Scheduled(cron = "10,30 * * * * *")
//    @Scheduled(cron = "0 45 13 * * *")
//    @Scheduled(cron = "0 0 7,18 * * *")
    public void saveToDbByCronTask1() {
        cultEventsParsingService.saveCultEvent1();
    }

    @Scheduled(cron = "10,30 * * * * *")
    public void saveToDbByCronTask2() {
        exhibitionsParsingService.saveExhibitEvent3();
    }

    @Scheduled(cron = "10,30 * * * * *")
    public void saveToDbByCronTask3() {
        politActualsParsngService.savePoliticActuals2();
    }

}


