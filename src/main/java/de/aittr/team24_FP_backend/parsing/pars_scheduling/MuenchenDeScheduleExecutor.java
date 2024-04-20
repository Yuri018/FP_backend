package de.aittr.team24_FP_backend.parsing.pars_scheduling;

import de.aittr.team24_FP_backend.parsing.news_pars_services.MuenchenDeNewsParsingTranslateService;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class MuenchenDeScheduleExecutor {

    MuenchenDeNewsParsingTranslateService muenchenDeNewsParsingTranslateService;

    public MuenchenDeScheduleExecutor(MuenchenDeNewsParsingTranslateService muenchenDeNewsParsingTranslateService) {
        this.muenchenDeNewsParsingTranslateService = muenchenDeNewsParsingTranslateService;
    }

//    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void saveMuenchenDeNewsToDbTask1() {
        muenchenDeNewsParsingTranslateService.muenchenEvent1ParsingTranslateSave();
    }

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void saveMuenchenDeNewsToDbTask2() {
        muenchenDeNewsParsingTranslateService.muenchenEvent2ParsingTranslateSave();
    }
}
