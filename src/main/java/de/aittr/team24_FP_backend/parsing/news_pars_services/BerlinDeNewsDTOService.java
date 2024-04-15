package de.aittr.team24_FP_backend.parsing.news_pars_services;

import de.aittr.team24_FP_backend.parsing.news_pars_models.BerlinDeNewsParsObj;
import de.aittr.team24_FP_backend.parsing.news_pars_models.LocalNewsCategory;
import de.aittr.team24_FP_backend.parsing.news_pars_models.dtos.BerlinDeNewsDTO;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.BerlinDeNewsParsingRepository;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.LocalNewsCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BerlinDeNewsDTOService {

    BerlinDeNewsParsingRepository berlinDeNewsParsingRepository;
    LocalNewsCategoryRepository localNewsCategoryRepository;

    public BerlinDeNewsDTOService(BerlinDeNewsParsingRepository berlinDeNewsParsingRepository, LocalNewsCategoryRepository localNewsCategoryRepository) {
        this.berlinDeNewsParsingRepository = berlinDeNewsParsingRepository;
        this.localNewsCategoryRepository = localNewsCategoryRepository;
    }

    public List<BerlinDeNewsDTO> findAllBerlinNews() {
        List<BerlinDeNewsParsObj> berlinNews = berlinDeNewsParsingRepository.findAll();
        List<BerlinDeNewsDTO> newsDtos = new ArrayList<>();
        for (BerlinDeNewsParsObj berlinNewsParsObj : berlinNews) {
            BerlinDeNewsDTO newsDTO = new BerlinDeNewsDTO();
            newsDTO.setId(berlinNewsParsObj.getId());
            newsDTO.setTitle(berlinNewsParsObj.getTitle());
            newsDTO.setShortDescription(berlinNewsParsObj.getShortDescription());
            newsDTO.setImgUrl(berlinNewsParsObj.getImgUrl());
            newsDTO.setContent(berlinNewsParsObj.getContent());
            newsDTO.setCategoryTitle(berlinNewsParsObj.getBerlinNewsCategory().getTitle());
            newsDtos.add(newsDTO);
        }
        return newsDtos;
    }

    public List<BerlinDeNewsDTO> findAllBerlinNewsByNewsCategory(String categoryTitle) {
        LocalNewsCategory category = localNewsCategoryRepository.findByTitle(categoryTitle);
        List<BerlinDeNewsParsObj> berlinNews = berlinDeNewsParsingRepository.findAllByLocalNewsCategory(category);
        List<BerlinDeNewsDTO> newsDtos = new ArrayList<>();
        for (BerlinDeNewsParsObj berlinNewsParsObj : berlinNews) {
            BerlinDeNewsDTO newsDTO = new BerlinDeNewsDTO();
            newsDTO.setId(berlinNewsParsObj.getId());
            newsDTO.setTitle(berlinNewsParsObj.getTitle());
            newsDTO.setShortDescription(berlinNewsParsObj.getShortDescription());
            newsDTO.setImgUrl(berlinNewsParsObj.getImgUrl());
            newsDTO.setContent(berlinNewsParsObj.getContent());
            newsDTO.setCategoryTitle(berlinNewsParsObj.getBerlinNewsCategory().getTitle());
            newsDtos.add(newsDTO);
        }
        return newsDtos;
    }

    public List<BerlinDeNewsDTO> findAllBerlinNewsExceptCategory(String categoryTitle) {
        LocalNewsCategory category = localNewsCategoryRepository.findByTitle(categoryTitle);
        List<BerlinDeNewsParsObj> berlinNews = berlinDeNewsParsingRepository.findAllByLocalNewsCategoryNot(category);
        List<BerlinDeNewsDTO> newsDtos = new ArrayList<>();
        for (BerlinDeNewsParsObj newsParsObj : berlinNews) {
            BerlinDeNewsDTO newsDTO = new BerlinDeNewsDTO();
            newsDTO.setId(newsParsObj.getId());
            newsDTO.setTitle(newsParsObj.getTitle());
            newsDTO.setShortDescription(newsParsObj.getShortDescription());
            newsDTO.setImgUrl(newsParsObj.getImgUrl());
            newsDTO.setContent(newsParsObj.getContent());
            newsDTO.setCategoryTitle(newsParsObj.getBerlinNewsCategory().getTitle());
            newsDtos.add(newsDTO);
        }
        return newsDtos;
    }

}
