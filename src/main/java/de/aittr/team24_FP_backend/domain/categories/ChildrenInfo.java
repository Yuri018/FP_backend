package de.aittr.team24_FP_backend.domain.categories;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "children_info")
public class ChildrenInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
    @Column(name = "address")
    private String address;
    @Column(name = "tel")
    private String tel;
    @Column(name = "link")
    private String link;
    @Column(name = "status")
    private Integer status = 0;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;


    public ChildrenInfo() {
    }

    public ChildrenInfo(Integer id, String title, String description, String address, City city) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.city = city;
    }

    public ChildrenInfo(Integer id, String title, String description, String address, String tel, City city) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.tel = tel;
        this.city = city;
    }

    public ChildrenInfo(Integer id, String title, String description, String address, String tel, String link, City city) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.tel = tel;
        this.link = link;
        this.city = city;
    }

    public ChildrenInfo(String title) {
        this.title = title;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChildrenInfo that = (ChildrenInfo) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(address, that.address)) return false;
        if (!Objects.equals(tel, that.tel)) return false;
        if (!Objects.equals(link, that.link)) return false;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BerlinChildrenInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", link='" + link + '\'' +
                ", status=" + status +
                '}';
    }

    public String toStringEmail() {
        return "Название: " + title + "\n" +
                "Описание: " + description + "\n" +
                "Адрес: " + address + "\n" +
                "Телефон: " + tel + "\n" +
                "Ссылка: " + link + "\n" +
                "Город: " + city.getName().toLowerCase();
    }
}

