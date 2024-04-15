package de.aittr.team24_FP_backend.parsing.news_pars_models.dtos;

import java.util.Objects;

public class GeneralNewsParsDTO {
    private Integer id;
    private String title;
    private String shortDescription;
    private String content;
    private String imgUrl;
    private String categoryTitle;

    public GeneralNewsParsDTO() {
    }

    public GeneralNewsParsDTO(Integer id, String title, String shortDescription, String content, String imgUrl, String categoryTitle) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.content = content;
        this.imgUrl = imgUrl;
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
        GeneralNewsParsDTO that = (GeneralNewsParsDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(content, that.content) && Objects.equals(imgUrl, that.imgUrl) && Objects.equals(categoryTitle, that.categoryTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, shortDescription, content, imgUrl, categoryTitle);
    }

    @Override
    public String toString() {
        return "GeneralNewsParsDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", categoryTitle='" + categoryTitle + '\'' +
                '}';
    }
}
