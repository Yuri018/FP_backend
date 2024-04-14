package de.aittr.team24_FP_backend.parsing.news_pars_models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "local_news_category")
public class LocalNewsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "localNewsCategory",cascade = CascadeType.ALL)
    private List<BerlinDeNewsParsObj> berlinNews;

    public LocalNewsCategory() {
    }

    public LocalNewsCategory(List<BerlinDeNewsParsObj> berlinNews) {
        this.berlinNews = berlinNews;
    }

    public LocalNewsCategory(String title) {
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

    public List<BerlinDeNewsParsObj> getBerlinNews() {
        return berlinNews;
    }

    public void setBerlinNews(List<BerlinDeNewsParsObj> berlinNews) {
        this.berlinNews = berlinNews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalNewsCategory that = (LocalNewsCategory) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(berlinNews, that.berlinNews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, berlinNews);
    }

    @Override
    public String toString() {
        return "LocalNewsCategory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
