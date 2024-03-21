package de.aittr.team24_FP_backend.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user")
public class User implements de.aittr.team24_FP_backend.domain.interfaces.User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "news")
    private boolean isNews;

    @Column(name = "cultural_life")
    private boolean isCulturalLife;

    @Column(name = "child")
    private boolean isChild;

    @Column(name = "health")
    private boolean isHealth;

    @Column(name = "shop")
    private boolean isShop;

    @Column(name = "cafe")
    private boolean isCafe;

    @Column(name = "service")
    private boolean isService;

    public User() {
    }

    public User(int id, String email, String password, String firstName, String lastName, Date dateOfBirth, boolean isNews, boolean isCulturalLife, boolean isChild, boolean isHealth, boolean isShop, boolean isCafe, boolean isService) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.isNews = isNews;
        this.isCulturalLife = isCulturalLife;
        this.isChild = isChild;
        this.isHealth = isHealth;
        this.isShop = isShop;
        this.isCafe = isCafe;
        this.isService = isService;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public boolean isNews() {
        return isNews;
    }

    @Override
    public void setNews(boolean news) {
        isNews = news;
    }

    @Override
    public boolean isCulturalLife() {
        return isCulturalLife;
    }

    @Override
    public void setCulturalLife(boolean culturalLife) {
        isCulturalLife = culturalLife;
    }

    @Override
    public boolean isChild() {
        return isChild;
    }

    @Override
    public void setChild(boolean child) {
        isChild = child;
    }

    @Override
    public boolean isHealth() {
        return isHealth;
    }

    @Override
    public void setHealth(boolean health) {
        isHealth = health;
    }

    @Override
    public boolean isShop() {
        return isShop;
    }

    @Override
    public void setShop(boolean shop) {
        isShop = shop;
    }

    @Override
    public boolean isCafe() {
        return isCafe;
    }

    @Override
    public void setCafe(boolean cafe) {
        isCafe = cafe;
    }

    @Override
    public boolean isService() {
        return isService;
    }

    @Override
    public void setService(boolean service) {
        isService = service;
    }

    @Override
    public String toString() {
        return "User: id = %d, firstName = %s, lastName = %s, email = %s".formatted(id, firstName, lastName, email);
//        return String.format("User: id = %d, firstName = %s, lastName = %s", email = %s, id, firstName, lastName, email);
    }
}
