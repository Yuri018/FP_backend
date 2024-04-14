package de.aittr.team24_FP_backend.controllers;

import de.aittr.team24_FP_backend.repositories.MultiEntitySpecification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class SearchByKeywordsController {

    private final MultiEntitySpecification multiEntitySpecification;

    public SearchByKeywordsController(MultiEntitySpecification multiEntitySpecification) {
        this.multiEntitySpecification = multiEntitySpecification;
    }

    @GetMapping("/search")
    public List<Object> searchByKeywords(@RequestParam String keywords) {
        return multiEntitySpecification.findByKeyword(keywords);
    }
}
