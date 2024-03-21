package de.aittr.team24_FP_backend.domain.interfaces;

import java.util.Date;

public interface User {

    int getId();

    void setId(int id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    Date getDateOfBirth();

    void setDateOfBirth(Date dateOfBirth);

    boolean isNews();

    void setNews(boolean news);

    boolean isCulturalLife();

    void setCulturalLife(boolean culturalLife);

    boolean isChild();

    void setChild(boolean child);

    boolean isHealth();

    void setHealth(boolean health);

    boolean isShop();

    void setShop(boolean shop);

    boolean isCafe();

    void setCafe(boolean cafe);

    boolean isService();

    void setService(boolean service);
}
