package de.aittr.team24_FP_backend.services;

import de.aittr.team24_FP_backend.domain.ExampleParsingTranslate;
import de.aittr.team24_FP_backend.repositories.ExampleParsingTranslateRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExampleParsingTranslateService {

    private ExampleParsingTranslateRepository repository;
    private TranslationService translationService;
    private ParsingTextService parsingTextService;


    public ExampleParsingTranslateService(ExampleParsingTranslateRepository repository, TranslationService translationService, ParsingTextService parsingTextService) {
        this.repository = repository;
        this.translationService = translationService;
        this.parsingTextService = parsingTextService;
    }

    public ExampleParsingTranslate save() {
        ExampleParsingTranslate exampleParsingTranslate = new ExampleParsingTranslate();


        String title = parsingTextService.parsingAttemptText().get("title");
        String description = parsingTextService.parsingAttemptText().get("description");

        exampleParsingTranslate.setTitle(translationService.translateText(title));
        exampleParsingTranslate.setDescription(translationService.translateText(description));
        return repository.save(exampleParsingTranslate);
    }

}
