package de.aittr.team24_FP_backend.parsing.news_pars_models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "general_news")
public class GeneralNewsParsObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "short_descr")
    private String shortDescription;

    @Column(name = "content")
    private String content;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "news_category_id")
    private NewsCategory newsCategory;

    public GeneralNewsParsObj() {
    }

    public GeneralNewsParsObj(Integer id, String title, String shortDescription, String content, String imgUrl, NewsCategory newsCategory) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.content = content;
        this.imgUrl = imgUrl;
        this.newsCategory = newsCategory;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imageUrl) {
        this.imgUrl = imageUrl;
    }

    public NewsCategory getCategory() {
        return newsCategory;
    }

    public void setCategory(NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralNewsParsObj that = (GeneralNewsParsObj) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(content, that.content) && Objects.equals(imgUrl, that.imgUrl) && Objects.equals(newsCategory, that.newsCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, shortDescription, content, imgUrl, newsCategory);
    }

    @Override
    public String toString() {
        return "GeneralNewsParsObj{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", newsCategory=" + newsCategory +
                '}';
    }
}
