package de.aittr.team24_FP_backend.domain.categories;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<RestaurantsInfo> restaurants;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<ShopsInfo> shops;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<ChildrenInfo> children;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<DoctorsInfo> doctors;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<HairBeautyInfo> hairBeauty;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<LegalServicesInfo> legalServices;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<TranslatorsInfo> translators;


    public City() {
    }

    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (!Objects.equals(id, city.id)) return false;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
