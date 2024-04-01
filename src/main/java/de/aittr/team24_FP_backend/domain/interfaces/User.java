package de.aittr.team24_FP_backend.domain.interfaces;

public interface User {
    int getId();

    void setId(int id);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);
}
