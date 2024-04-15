package de.aittr.team24_FP_backend.parsing.news_pars_controllers;

import de.aittr.team24_FP_backend.parsing.news_pars_models.dtos.GeneralNewsParsDTO;
import de.aittr.team24_FP_backend.parsing.news_pars_services.GeneralNewsDTOService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/general_news")
public class GeneralNewsParsingDTOController {

    GeneralNewsDTOService generalNewsDtoService;

    public GeneralNewsParsingDTOController(GeneralNewsDTOService generalNewsDtoService) {
        this.generalNewsDtoService = generalNewsDtoService;
    }

    //    @Operation(summary = "findAllNewsByNewsCategory")
    @GetMapping("get_info_by/{categoryTitle}")
    public List<GeneralNewsParsDTO> findAllNewsByNewsCategory(@PathVariable String categoryTitle) {
        return generalNewsDtoService.findAllNewsByNewsCategory(categoryTitle);
    }

//    @Operation(summary = "findAllNewsExceptNewsCategory")
    @GetMapping("except/{categoryTitle}")
    public List<GeneralNewsParsDTO> findAllNewsExceptNewsCategory(@PathVariable String categoryTitle) {
        return generalNewsDtoService.findAllNewsExceptNewsCategory(categoryTitle);
    }

}
