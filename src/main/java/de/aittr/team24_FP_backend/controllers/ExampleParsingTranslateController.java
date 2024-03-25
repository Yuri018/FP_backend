package de.aittr.team24_FP_backend.controllers;

import de.aittr.team24_FP_backend.domain.ExampleParsingTranslate;
import de.aittr.team24_FP_backend.services.ExampleParsingTranslateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example_parsing")
public class ExampleParsingTranslateController {

    private ExampleParsingTranslateService service;

    public ExampleParsingTranslateController(ExampleParsingTranslateService service) {
        this.service = service;
    }

    @PostMapping
    public ExampleParsingTranslate save() {
        return service.save();
    }
}
