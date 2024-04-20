package de.aittr.team24_FP_backend.parsing.news_pars_controllers;

import de.aittr.team24_FP_backend.parsing.news_pars_models.dtos.BerlinDeNewsDTO;
import de.aittr.team24_FP_backend.parsing.news_pars_models.dtos.MuenchenDeNewsDTO;
import de.aittr.team24_FP_backend.parsing.news_pars_services.MuenchenDeNewsDTOService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/muenchen_news")
public class MuenchenNewsParsingDTOController {
    MuenchenDeNewsDTOService muenchenDeNewsDTOService;

    public MuenchenNewsParsingDTOController(MuenchenDeNewsDTOService muenchenDeNewsDTOService) {
        this.muenchenDeNewsDTOService = muenchenDeNewsDTOService;
    }

    @GetMapping
    public List<MuenchenDeNewsDTO> findAllMuenchenNews() {
        return muenchenDeNewsDTOService.findAllMuenchenNews();
    }

    @GetMapping("get_info_by/{categoryTitle}")
    public List<MuenchenDeNewsDTO> findAllMuenchenNewsByNewsCategory(@PathVariable String categoryTitle) {
        return muenchenDeNewsDTOService.findAllMuenchenNewsByNewsCategory(categoryTitle);
    }

    //    @Operation(summary = "findAllNewsExceptNewsCategory")
    @GetMapping("except/{categoryTitle}")
    public List<MuenchenDeNewsDTO> findAllByLocalNewsExceptCategory(@PathVariable String categoryTitle) {
        return muenchenDeNewsDTOService.findAllMuenchenNewsExceptCategory(categoryTitle);
    }
}
