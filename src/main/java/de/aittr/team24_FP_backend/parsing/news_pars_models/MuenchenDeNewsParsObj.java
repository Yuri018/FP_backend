package de.aittr.team24_FP_backend.parsing.news_pars_models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "muenchen_news")
public class MuenchenDeNewsParsObj {

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

    @Column(name = "img_copyright")
    private String imgCopyright;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "local_news_category_id")
    private LocalNewsCategory localNewsCategory;

    public MuenchenDeNewsParsObj() {
    }

    public MuenchenDeNewsParsObj(Integer id, String title, String shortDescription, String content, String imgUrl, String imgCopyright, LocalNewsCategory localNewsCategory) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.content = content;
        this.imgUrl = imgUrl;
        this.imgCopyright = imgCopyright;
        this.localNewsCategory = localNewsCategory;
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

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgCopyright() {
        return imgCopyright;
    }

    public void setImgCopyright(String imgCopyright) {
        this.imgCopyright = imgCopyright;
    }

    public LocalNewsCategory getLocalNewsCategory() {
        return localNewsCategory;
    }

    public void setLocalNewsCategory(LocalNewsCategory localNewsCategory) {
        this.localNewsCategory = localNewsCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MuenchenDeNewsParsObj that = (MuenchenDeNewsParsObj) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(content, that.content) && Objects.equals(imgUrl, that.imgUrl) && Objects.equals(imgCopyright, that.imgCopyright) && Objects.equals(localNewsCategory, that.localNewsCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, shortDescription, content, imgUrl, imgCopyright, localNewsCategory);
    }

    @Override
    public String toString() {
        return "MuenchenDeNewsParsObj{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgCopyright='" + imgCopyright + '\'' +
                ", localNewsCategory=" + localNewsCategory +
                '}';
    }
}
