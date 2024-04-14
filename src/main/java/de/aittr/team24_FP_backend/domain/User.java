package de.aittr.team24_FP_backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "children_info")
    private boolean children_info = false;

    @Column(name = "doctors_info")
    private boolean doctors_info = false;

    @Column(name = "hair_beauty_info")
    private boolean hair_beauty_info = false;

    @Column(name = "legal_services_info")
    private boolean legal_services_info = false;

    @Column(name = "restaurants_info")
    private boolean restaurants_info = false;

    @Column(name = "shops_info")
    private boolean shops_info = false;

    @Column(name = "translators_info")
    private boolean translators_info = false;

    @Column(name = "berlin")
    private boolean berlin = false;

    @Column(name = "muenchen")
    private boolean muenchen = false;

    @Column(name = "dusseldorf")
    private boolean dusseldorf = false;

    @Column(name = "hamburg")
    private boolean hamburg = false;

    @Column(name = "frankfurt")
    private boolean frankfurt = false;

    @OneToOne
    @JoinColumn(name = "user_login_id", referencedColumnName = "id")
    @JsonIgnore
    private UserLogin userLogin;

    public User() {
    }

    public User(Integer id, String firstname, String lastname, boolean children_info, boolean doctors_info, boolean hair_beauty_info, boolean legal_services_info, boolean restaurants_info, boolean shops_info, boolean translators_info, boolean berlin, boolean muenchen, boolean dusseldorf, boolean hamburg, boolean frankfurt, UserLogin userLogin) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.children_info = children_info;
        this.doctors_info = doctors_info;
        this.hair_beauty_info = hair_beauty_info;
        this.legal_services_info = legal_services_info;
        this.restaurants_info = restaurants_info;
        this.shops_info = shops_info;
        this.translators_info = translators_info;
        this.berlin = berlin;
        this.muenchen = muenchen;
        this.dusseldorf = dusseldorf;
        this.hamburg = hamburg;
        this.frankfurt = frankfurt;
        this.userLogin = userLogin;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isChildren_info() {
        return children_info;
    }

    public void setChildren_info(boolean children_info) {
        this.children_info = children_info;
    }

    public boolean isDoctors_info() {
        return doctors_info;
    }

    public void setDoctors_info(boolean doctors_info) {
        this.doctors_info = doctors_info;
    }

    public boolean isHair_beauty_info() {
        return hair_beauty_info;
    }

    public void setHair_beauty_info(boolean hair_beauty_info) {
        this.hair_beauty_info = hair_beauty_info;
    }

    public boolean isLegal_services_info() {
        return legal_services_info;
    }

    public void setLegal_services_info(boolean legal_services_info) {
        this.legal_services_info = legal_services_info;
    }

    public boolean isRestaurants_info() {
        return restaurants_info;
    }

    public void setRestaurants_info(boolean restaurants_info) {
        this.restaurants_info = restaurants_info;
    }

    public boolean isShops_info() {
        return shops_info;
    }

    public void setShops_info(boolean shops_info) {
        this.shops_info = shops_info;
    }

    public boolean isTranslators_info() {
        return translators_info;
    }

    public void setTranslators_info(boolean translators_info) {
        this.translators_info = translators_info;
    }

    public boolean isBerlin() {
        return berlin;
    }

    public void setBerlin(boolean berlin) {
        this.berlin = berlin;
    }

    public boolean isMuenchen() {
        return muenchen;
    }

    public void setMuenchen(boolean muenchen) {
        this.muenchen = muenchen;
    }

    public boolean isDusseldorf() {
        return dusseldorf;
    }

    public void setDusseldorf(boolean dusseldorf) {
        this.dusseldorf = dusseldorf;
    }

    public boolean isHamburg() {
        return hamburg;
    }

    public void setHamburg(boolean hamburg) {
        this.hamburg = hamburg;
    }

    public boolean isFrankfurt() {
        return frankfurt;
    }

    public void setFrankfurt(boolean frankfurt) {
        this.frankfurt = frankfurt;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (children_info != user.children_info) return false;
        if (doctors_info != user.doctors_info) return false;
        if (hair_beauty_info != user.hair_beauty_info) return false;
        if (legal_services_info != user.legal_services_info) return false;
        if (restaurants_info != user.restaurants_info) return false;
        if (shops_info != user.shops_info) return false;
        if (translators_info != user.translators_info) return false;
        if (berlin != user.berlin) return false;
        if (muenchen != user.muenchen) return false;
        if (dusseldorf != user.dusseldorf) return false;
        if (hamburg != user.hamburg) return false;
        if (frankfurt != user.frankfurt) return false;
        if (!Objects.equals(id, user.id)) return false;
        if (!Objects.equals(firstname, user.firstname)) return false;
        if (!Objects.equals(lastname, user.lastname)) return false;
        return Objects.equals(userLogin, user.userLogin);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (children_info ? 1 : 0);
        result = 31 * result + (doctors_info ? 1 : 0);
        result = 31 * result + (hair_beauty_info ? 1 : 0);
        result = 31 * result + (legal_services_info ? 1 : 0);
        result = 31 * result + (restaurants_info ? 1 : 0);
        result = 31 * result + (shops_info ? 1 : 0);
        result = 31 * result + (translators_info ? 1 : 0);
        result = 31 * result + (berlin ? 1 : 0);
        result = 31 * result + (muenchen ? 1 : 0);
        result = 31 * result + (dusseldorf ? 1 : 0);
        result = 31 * result + (hamburg ? 1 : 0);
        result = 31 * result + (frankfurt ? 1 : 0);
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", children_info=" + children_info +
                ", doctors_info=" + doctors_info +
                ", hair_beauty_info=" + hair_beauty_info +
                ", legal_services_info=" + legal_services_info +
                ", restaurants_info=" + restaurants_info +
                ", shops_info=" + shops_info +
                ", translators_info=" + translators_info +
                ", berlin=" + berlin +
                ", muenchen=" + muenchen +
                ", dusseldorf=" + dusseldorf +
                ", hamburg=" + hamburg +
                ", frankfurt=" + frankfurt +
                '}';
    }
}
