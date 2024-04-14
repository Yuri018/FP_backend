package de.aittr.team24_FP_backend.domain.categories;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "doctors_category")
public class DoctorsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "doctorsCategory", cascade = CascadeType.ALL)
    private List<DoctorsInfo> doctors;

    public DoctorsCategory() {
    }

    public DoctorsCategory(Integer id, String name) {
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

        DoctorsCategory that = (DoctorsCategory) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(doctors, that.doctors);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (doctors != null ? doctors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + "\n";
    }
}
