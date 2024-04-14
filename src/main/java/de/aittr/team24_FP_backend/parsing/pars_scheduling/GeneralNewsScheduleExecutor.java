package de.aittr.team24_FP_backend.parsing.pars_scheduling;

import de.aittr.team24_FP_backend.parsing.news_pars_services.GeneralInfoParsingService;
import de.aittr.team24_FP_backend.parsing.news_pars_services.GeneralNewsParsingService;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class GeneralNewsScheduleExecutor {

    private GeneralNewsParsingService generalNewsParsingService;
    private GeneralInfoParsingService generalInfoParsingService;

    public GeneralNewsScheduleExecutor(GeneralNewsParsingService generalNewsParsingService, GeneralInfoParsingService generalInfoParsingService) {
        this.generalNewsParsingService = generalNewsParsingService;
        this.generalInfoParsingService = generalInfoParsingService;
    }

    //    @Scheduled(cron = "10,30 * * * * *")
    @Transactional
    public void saveGenNewsToDbByCronTask1() {
        generalNewsParsingService.savePolitNews1();
    }

//    @Scheduled(cron = "10,30 * * * * *")
    @Transactional
    public void saveGenNewsToDbByCronTask2() {
        generalNewsParsingService.savePolitNews2();
    }

//    @Scheduled(cron = "10,30 * * * * *")
    @Transactional
    public void saveGenNewsToDbByCronTask3() {
        generalNewsParsingService.saveEconomicNews1();
    }

//    @Scheduled(cron = "10,30 * * * * *")
    @Transactional
    public void saveGenNewsToDbByCronTask4() {
        generalNewsParsingService.saveEconomicNews2();
    }

//    @Scheduled(cron = "10,30 * * * * *")
    @Transactional
    public void saveGenInfoToDbByCronTask5() {
        generalInfoParsingService.saveGeneralInfo1();
    }

//    @Scheduled(cron = "10,30 * * * * *")
    @Transactional
    public void saveGenInfoToDbByCronTask6() {
        generalInfoParsingService.saveMinorGeneralInfo2();
    }

//    @Scheduled(cron = "10,30 * * * * *")
    @Transactional
    public void saveGenInfoToDbByCronTask7() {
        generalInfoParsingService.saveMinorGeneralInfo4();
    }

//    @Scheduled(cron = "10,30 * * * * *")
    @Transactional
    public void saveGenInfoToDbByCronTask8() {
        generalInfoParsingService.saveMinorGeneralInfo6();
    }

//    @Scheduled(cron = "10,30 * * * * *")
    @Transactional
    public void saveGenInfoToDbByCronTask9() {
        generalInfoParsingService.saveMinorGeneralInfo1();
    }

//    @Scheduled(cron = "10,30 * * * * *")
    @Transactional
    public void saveGenInfoToDbByCronTask10() {
        generalInfoParsingService.saveMinorGeneralInfo8();
    }

//    @Scheduled(cron = "10,30 * * * * *")
    @Transactional
    public void saveGenNewsToDbByCronTask11() {
        generalNewsParsingService.saveHealthNews7();
    }

//    @Scheduled(cron = "10,30 * * * * *")
    @Transactional
    public void saveGenNewsToDbByCronTask12() {
        generalNewsParsingService.saveSocNews2();
    }
}
