package de.aittr.team24_FP_backend.parsing.news_pars_repositories;

import de.aittr.team24_FP_backend.parsing.news_pars_models.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenNewsCategoryRepository extends JpaRepository<NewsCategory, Integer> {

    NewsCategory findByTitle(String title);
}
