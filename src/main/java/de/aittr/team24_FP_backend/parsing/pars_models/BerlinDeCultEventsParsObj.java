package de.aittr.team24_FP_backend.parsing.pars_models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "berlin_cult_events_news")
public class BerlinDeCultEventsParsObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "img_url")
    private String imageUrl;

    @Column(name = "img_copyright")
    private String imgCopyright;

    public BerlinDeCultEventsParsObj() {
    }

    public BerlinDeCultEventsParsObj(String title, String content, String imageUrl, String imgCopyright) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.imgCopyright = imgCopyright;
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

    public String getContent() {
        return content;
    }

    public void setContent(String description) {
        this.content = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImgCopyright() {
        return imgCopyright;
    }

    public void setImgCopyright(String imgCopyright) {
        this.imgCopyright = imgCopyright;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BerlinDeCultEventsParsObj that = (BerlinDeCultEventsParsObj) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(imgCopyright, that.imgCopyright);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, imageUrl, imgCopyright);
    }

    @Override
    public String toString() {
        return "BerlinDeCultEventsParsObj{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imgCopyright='" + imgCopyright + '\'' +
                '}';
    }
}
