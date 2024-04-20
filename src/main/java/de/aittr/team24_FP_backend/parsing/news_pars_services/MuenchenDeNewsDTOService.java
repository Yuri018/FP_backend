package de.aittr.team24_FP_backend.parsing.news_pars_services;

import de.aittr.team24_FP_backend.parsing.news_pars_models.LocalNewsCategory;
import de.aittr.team24_FP_backend.parsing.news_pars_models.MuenchenDeNewsParsObj;
import de.aittr.team24_FP_backend.parsing.news_pars_models.dtos.MuenchenDeNewsDTO;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.LocalNewsCategoryRepository;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.MuenchenDeNewsParsingRepository;

import java.util.ArrayList;
import java.util.List;

public class MuenchenDeNewsDTOService {

    MuenchenDeNewsParsingRepository muenchenDeNewsParsingRepository;
    LocalNewsCategoryRepository localNewsCategoryRepository;

    public MuenchenDeNewsDTOService(MuenchenDeNewsParsingRepository muenchenDeNewsParsingRepository, LocalNewsCategoryRepository localNewsCategoryRepository) {
        this.muenchenDeNewsParsingRepository = muenchenDeNewsParsingRepository;
        this.localNewsCategoryRepository = localNewsCategoryRepository;
    }

    public List<MuenchenDeNewsDTO> findAllMuenchenNews() {
        List<MuenchenDeNewsParsObj> muenchenNews = muenchenDeNewsParsingRepository.findAll();
        List<MuenchenDeNewsDTO> newsDtos = new ArrayList<>();
        for (MuenchenDeNewsParsObj muenchenNewsParsObj : muenchenNews) {
            MuenchenDeNewsDTO newsDTO = new MuenchenDeNewsDTO();
            newsDTO.setId(muenchenNewsParsObj.getId());
            newsDTO.setTitle(muenchenNewsParsObj.getTitle());
            newsDTO.setShortDescription(muenchenNewsParsObj.getShortDescription());
            newsDTO.setImgUrl(muenchenNewsParsObj.getImgUrl());
            newsDTO.setImgCopyright(muenchenNewsParsObj.getImgCopyright());
            newsDTO.setContent(muenchenNewsParsObj.getContent());
            newsDTO.setCategoryTitle(muenchenNewsParsObj.getLocalNewsCategory().getTitle());
            newsDtos.add(newsDTO);
        }
        return newsDtos;
    }

    public List<MuenchenDeNewsDTO> findAllMuenchenNewsByNewsCategory(String categoryTitle) {
        LocalNewsCategory category = localNewsCategoryRepository.findByTitle(categoryTitle);
        List<MuenchenDeNewsParsObj> muenchenNews = muenchenDeNewsParsingRepository.findAllByLocalNewsCategory(category);
        List<MuenchenDeNewsDTO> newsDtos = new ArrayList<>();
        for (MuenchenDeNewsParsObj muenchenNewsParsObj : muenchenNews) {
            MuenchenDeNewsDTO newsDTO = new MuenchenDeNewsDTO();
            newsDTO.setId(muenchenNewsParsObj.getId());
            newsDTO.setTitle(muenchenNewsParsObj.getTitle());
            newsDTO.setShortDescription(muenchenNewsParsObj.getShortDescription());
            newsDTO.setImgUrl(muenchenNewsParsObj.getImgUrl());
            newsDTO.setContent(muenchenNewsParsObj.getContent());
            newsDTO.setCategoryTitle(muenchenNewsParsObj.getLocalNewsCategory().getTitle());
            newsDtos.add(newsDTO);
        }
        return newsDtos;
    }

    public List<MuenchenDeNewsDTO> findAllMuenchenNewsExceptCategory(String categoryTitle) {
        LocalNewsCategory category = localNewsCategoryRepository.findByTitle(categoryTitle);
        List<MuenchenDeNewsParsObj> muenchenNews = muenchenDeNewsParsingRepository.findAllByLocalNewsCategoryNot(category);
        List<MuenchenDeNewsDTO> newsDtos = new ArrayList<>();
        for (MuenchenDeNewsParsObj newsParsObj : muenchenNews) {
            MuenchenDeNewsDTO newsDTO = new MuenchenDeNewsDTO();
            newsDTO.setId(newsParsObj.getId());
            newsDTO.setTitle(newsParsObj.getTitle());
            newsDTO.setShortDescription(newsParsObj.getShortDescription());
            newsDTO.setImgUrl(newsParsObj.getImgUrl());
            newsDTO.setContent(newsParsObj.getContent());
            newsDTO.setCategoryTitle(newsParsObj.getLocalNewsCategory().getTitle());
            newsDtos.add(newsDTO);
        }
        return newsDtos;
    }
}
