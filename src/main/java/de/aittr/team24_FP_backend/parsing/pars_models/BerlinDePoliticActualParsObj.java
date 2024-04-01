package de.aittr.team24_FP_backend.parsing.pars_models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "berlin_polics_actual_news")
public class BerlinDePoliticActualParsObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private  String title;

    @Column(name = "content")
    private String content;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "img_copyright")
    private String imgCopyright;

    public BerlinDePoliticActualParsObj() {
    }

    public BerlinDePoliticActualParsObj(String title, String content, String imgUrl, String imgCopyright) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BerlinDePoliticActualParsObj that = (BerlinDePoliticActualParsObj) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(imgUrl, that.imgUrl) && Objects.equals(imgCopyright, that.imgCopyright);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, imgUrl, imgCopyright);
    }

    @Override
    public String toString() {
        return "BerlinDePoliticActualParsObj{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgCopyright='" + imgCopyright + '\'' +
                '}';
    }
}
