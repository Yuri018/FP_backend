package de.aittr.team24_FP_backend.parsing.news_pars_repositories;

import de.aittr.team24_FP_backend.parsing.news_pars_models.LocalNewsCategory;
import de.aittr.team24_FP_backend.parsing.news_pars_models.BerlinDeNewsParsObj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BerlinDeNewsParsingRepository extends JpaRepository<BerlinDeNewsParsObj, Integer> {

    List<BerlinDeNewsParsObj> findAllByLocalNewsCategory (LocalNewsCategory localNewsCategory);
    List<BerlinDeNewsParsObj> findAllByLocalNewsCategoryNot(LocalNewsCategory localNewsCategory);
}
