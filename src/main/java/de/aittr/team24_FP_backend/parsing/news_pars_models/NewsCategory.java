package de.aittr.team24_FP_backend.parsing.news_pars_models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "news_category")
public class NewsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "newsCategory",cascade = CascadeType.ALL)
    private List<GeneralNewsParsObj> generalNews;

    public NewsCategory() {
    }

    public NewsCategory(List<GeneralNewsParsObj> generalNews) {
        this.generalNews = generalNews;
    }

    public NewsCategory(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GeneralNewsParsObj> getGeneralNews() {
        return generalNews;
    }

    public void setGeneralNews(List<GeneralNewsParsObj> generalNews) {
        this.generalNews = generalNews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsCategory newsCategory = (NewsCategory) o;
        return Objects.equals(id, newsCategory.id) && Objects.equals(title, newsCategory.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "NewsCategory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
