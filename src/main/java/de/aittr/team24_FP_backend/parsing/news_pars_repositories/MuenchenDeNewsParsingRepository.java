package de.aittr.team24_FP_backend.parsing.news_pars_repositories;

import de.aittr.team24_FP_backend.parsing.news_pars_models.BerlinDeNewsParsObj;
import de.aittr.team24_FP_backend.parsing.news_pars_models.LocalNewsCategory;
import de.aittr.team24_FP_backend.parsing.news_pars_models.MuenchenDeNewsParsObj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MuenchenDeNewsParsingRepository extends JpaRepository<MuenchenDeNewsParsObj, Integer> {
    List<MuenchenDeNewsParsObj> findAllByLocalNewsCategory (LocalNewsCategory localNewsCategory);
    List<MuenchenDeNewsParsObj> findAllByLocalNewsCategoryNot(LocalNewsCategory localNewsCategory);
}
