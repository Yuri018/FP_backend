package de.aittr.team24_FP_backend.parsing.news_pars_controllers;

import de.aittr.team24_FP_backend.parsing.news_pars_models.dtos.BerlinDeNewsDTO;
import de.aittr.team24_FP_backend.parsing.news_pars_services.BerlinDeNewsDTOService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/berlin_news")
public class BerlinNewsParsingDTOController {

    BerlinDeNewsDTOService berlinDeNewsDTOService;

    public BerlinNewsParsingDTOController(BerlinDeNewsDTOService berlinDeNewsDTOService) {
        this.berlinDeNewsDTOService = berlinDeNewsDTOService;
    }

    @GetMapping
    public List<BerlinDeNewsDTO> findAllBerlinNews() {
        return berlinDeNewsDTOService.findAllBerlinNews();
    }

    @GetMapping("get_info_by/{categoryTitle}")
    public List<BerlinDeNewsDTO> findAllBerlinNewsByNewsCategory(@PathVariable String categoryTitle) {
        return berlinDeNewsDTOService.findAllBerlinNewsByNewsCategory(categoryTitle);
    }

    //    @Operation(summary = "findAllNewsExceptNewsCategory")
    @GetMapping("except/{categoryTitle}")
    public List<BerlinDeNewsDTO> findAllByLocalNewsExceptCategory(@PathVariable String categoryTitle) {
        return berlinDeNewsDTOService.findAllBerlinNewsExceptCategory(categoryTitle);
    }
}
