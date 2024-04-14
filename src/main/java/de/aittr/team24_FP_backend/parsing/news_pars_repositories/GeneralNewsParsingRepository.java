package de.aittr.team24_FP_backend.parsing.news_pars_repositories;

import de.aittr.team24_FP_backend.parsing.news_pars_models.NewsCategory;
import de.aittr.team24_FP_backend.parsing.news_pars_models.GeneralNewsParsObj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneralNewsParsingRepository extends JpaRepository<GeneralNewsParsObj, Integer> {

    List<GeneralNewsParsObj> findAllByNewsCategory(NewsCategory newsCategory);
}
