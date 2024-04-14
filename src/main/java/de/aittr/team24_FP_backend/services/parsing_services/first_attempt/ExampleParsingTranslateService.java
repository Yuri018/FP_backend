package de.aittr.team24_FP_backend.services.parsing_services.first_attempt;

/*
import de.aittr.team24_FP_backend.domain.ExampleParsingTranslate;
import de.aittr.team24_FP_backend.repositories.ExampleParsingTranslateRepository;
import de.aittr.team24_FP_backend.services.translation_service.TranslationService;

//@Service
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
*/
