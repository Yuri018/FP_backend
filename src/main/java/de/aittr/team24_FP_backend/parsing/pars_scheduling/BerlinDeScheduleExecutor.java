package de.aittr.team24_FP_backend.parsing.pars_scheduling;

import de.aittr.team24_FP_backend.parsing.news_pars_services.BerlinDeNewsParsingTranslateService;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class BerlinDeScheduleExecutor {

//    private BerlinDeCultEventsParsingService cultEventsParsingService;
//    private BerlinDeExhibitionsParsingService exhibitionsParsingService;
//    private BerlinDePoliticActualsParsingServices politActualsParsngService;


    /*public BerlinDeScheduleExecutor(BerlinDeCultEventsParsingService cultEventsParsingService, BerlinDeExhibitionsParsingService exhibitionsParsingService, BerlinDePoliticActualsParsingServices politActualsParsngService) {
        this.cultEventsParsingService = cultEventsParsingService;
        this.exhibitionsParsingService = exhibitionsParsingService;
        this.politActualsParsngService = politActualsParsngService;
    }*/

    BerlinDeNewsParsingTranslateService berlinDeNewsParsingTranslateService;

    public BerlinDeScheduleExecutor(BerlinDeNewsParsingTranslateService berlinDeNewsParsingTranslateService) {
        this.berlinDeNewsParsingTranslateService = berlinDeNewsParsingTranslateService;
    }

//    @Scheduled(cron = "0 30 * * * *")
//    @Scheduled(cron = "0 45 13 * * *")
//    @Scheduled(cron = "0 0 7,18 * * *")
    @Transactional
    public void saveBerlinDeNewsToDbTask1() {
        berlinDeNewsParsingTranslateService.BerlinCultEvents2ParsingTranslateSave();
    }

//    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void saveBerlinDeNewsToDbTask2() {
        berlinDeNewsParsingTranslateService.BerlinExhibitHighlightsEvent3ParsingTranslateSave();
    }

//    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void saveBerlinDeNewsToDbTask3() {
        berlinDeNewsParsingTranslateService.BerlinExhibitHighlightsEvent4ParsingTranslateSave();
    }

//    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void saveBerlinDeNewsToDbTask4() {
        berlinDeNewsParsingTranslateService.BerlinCultEvents4ParsingTranslateSave();
    }

//    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void saveBerlinDeNewsToDbTask5() {
        berlinDeNewsParsingTranslateService.BerlinActualNews1ParsingTranslateSave();
    }

//    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void saveBerlinDeNewsToDbTask6() {
        berlinDeNewsParsingTranslateService.BerlinActualNews2ParsingTranslateSave();
    }

//    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void saveBerlinDeNewsToDbTask7() {
        berlinDeNewsParsingTranslateService.BerlinActualNews3ParsingTranslateSave();
    }

//    @Scheduled(cron = "10,30 * * * * *")
//    public void saveToDbByCronTask2() {
//        exhibitionsParsingService.saveExhibitEvent3();
//    }

//    @Scheduled(cron = "10,30 * * * * *")
//    public void saveToDbByCronTask3() {
//        politActualsParsngService.savePoliticActuals2();
//    }

}


