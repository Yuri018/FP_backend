package de.aittr.team24_FP_backend.parsing.news_pars_repositories;

import de.aittr.team24_FP_backend.parsing.news_pars_models.LocalNewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BerlinDeNewsCategoryRepository extends JpaRepository<LocalNewsCategory, Integer> {

    LocalNewsCategory findByTitle(String title);
}
