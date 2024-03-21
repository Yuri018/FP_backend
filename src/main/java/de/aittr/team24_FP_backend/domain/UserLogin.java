package de.aittr.team24_FP_backend.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "user_login")
public class UserLogin implements de.aittr.team24_FP_backend.domain.interfaces.UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public UserLogin() {
    }

    public UserLogin(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User: id = %d, email = %s".formatted(id, email);
    }
}
