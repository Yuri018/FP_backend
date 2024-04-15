package de.aittr.team24_FP_backend.parsing.news_pars_services;

import de.aittr.team24_FP_backend.parsing.news_pars_models.GeneralNewsParsObj;
import de.aittr.team24_FP_backend.parsing.news_pars_models.NewsCategory;
import de.aittr.team24_FP_backend.parsing.news_pars_models.dtos.GeneralNewsParsDTO;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.GenNewsCategoryRepository;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.GeneralNewsParsingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralNewsDTOService {

    GeneralNewsParsingRepository generalNewsParsingRepository;
    GenNewsCategoryRepository genNewsCategoryRepository;

    public GeneralNewsDTOService(GeneralNewsParsingRepository generalNewsParsingRepository, GenNewsCategoryRepository genNewsCategoryRepository) {
        this.generalNewsParsingRepository = generalNewsParsingRepository;
        this.genNewsCategoryRepository = genNewsCategoryRepository;
    }

    public List<GeneralNewsParsDTO> findAllNewsByNewsCategory(String categoryTitle) {
        NewsCategory category = genNewsCategoryRepository.findByTitle(categoryTitle);
        List<GeneralNewsParsObj> generalNews = generalNewsParsingRepository.findAllByNewsCategory(category);
        List<GeneralNewsParsDTO> newsDtos = new ArrayList<>();
        for (GeneralNewsParsObj newsParsObj : generalNews) {
            GeneralNewsParsDTO newsDTO = new GeneralNewsParsDTO();
            newsDTO.setId(newsParsObj.getId());
            newsDTO.setTitle(newsParsObj.getTitle());
            newsDTO.setShortDescription(newsParsObj.getShortDescription());
            newsDTO.setImgUrl(newsParsObj.getImgUrl());
            newsDTO.setContent(newsParsObj.getContent());
            newsDTO.setCategoryTitle(newsParsObj.getCategory().getTitle());
            newsDtos.add(newsDTO);
        }
        return newsDtos;
    }

    public List<GeneralNewsParsDTO> findAllNewsExceptNewsCategory(String categoryTitle) {
        NewsCategory category = genNewsCategoryRepository.findByTitle(categoryTitle);
        List<GeneralNewsParsObj> generalNews = generalNewsParsingRepository.findAllByNewsCategoryNot(category);
        List<GeneralNewsParsDTO> newsDtos = new ArrayList<>();
        for (GeneralNewsParsObj newsParsObj : generalNews) {
            GeneralNewsParsDTO newsDTO = new GeneralNewsParsDTO();
            newsDTO.setId(newsParsObj.getId());
            newsDTO.setTitle(newsParsObj.getTitle());
            newsDTO.setShortDescription(newsParsObj.getShortDescription());
            newsDTO.setImgUrl(newsParsObj.getImgUrl());
            newsDTO.setContent(newsParsObj.getContent());
            newsDTO.setCategoryTitle(newsParsObj.getCategory().getTitle());
            newsDtos.add(newsDTO);
        }
        return newsDtos;
    }
}
