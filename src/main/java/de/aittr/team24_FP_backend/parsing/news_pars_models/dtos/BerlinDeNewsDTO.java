package de.aittr.team24_FP_backend.parsing.news_pars_models.dtos;

import java.util.Objects;

public class BerlinDeNewsDTO {
    private Integer id;
    private String title;
    private String shortDescription;
    private String content;
    private String imgUrl;
    private String imgCopyright;
    private String categoryTitle;

    public BerlinDeNewsDTO() {
    }

    public BerlinDeNewsDTO(Integer id, String title, String shortDescription, String content, String imgUrl, String imgCopyright, String categoryTitle) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.content = content;
        this.imgUrl = imgUrl;
        this.imgCopyright = imgCopyright;
        this.categoryTitle = categoryTitle;
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

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BerlinDeNewsDTO that = (BerlinDeNewsDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(content, that.content) && Objects.equals(imgUrl, that.imgUrl) && Objects.equals(imgCopyright, that.imgCopyright) && Objects.equals(categoryTitle, that.categoryTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, shortDescription, content, imgUrl, imgCopyright, categoryTitle);
    }

    @Override
    public String toString() {
        return "BerlinDeNewsDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgCopyright='" + imgCopyright + '\'' +
                ", categoryTitle='" + categoryTitle + '\'' +
                '}';
    }
}
