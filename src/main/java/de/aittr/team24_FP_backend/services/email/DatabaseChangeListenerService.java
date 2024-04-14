package de.aittr.team24_FP_backend.services.email;

import de.aittr.team24_FP_backend.domain.UserLogin;
import de.aittr.team24_FP_backend.domain.categories.*;
import de.aittr.team24_FP_backend.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseChangeListenerService {

    private EmailService emailService;
    private UserLoginService userLoginService;
    @Autowired
    public DatabaseChangeListenerService(EmailService emailService, UserLoginService userLoginService) {
        this.emailService = emailService;
        this.userLoginService = userLoginService;
    }



    public void handleDatabaseChangeChildrenInfo(String cityName, ChildrenInfo childrenInfo) {
        // Получаем список пользователей, подписанных на таблицу shops_info и указанный город
        List<UserLogin> usersSubscribedToChildrenInfo = userLoginService.findAllByUserChildrenAndCityTrue(cityName);
        // Отправляем уведомления каждому из этих пользователей
        for (UserLogin userLogin : usersSubscribedToChildrenInfo) {
            String userEmail = userLogin.getUsername();
            String firstname = userLogin.getUser().getFirstname();
            String lastname = userLogin.getUser().getLastname();
            String subject = "Добавлена новая информация в категорию Дети";
            String message = "Здравствуйте, " + firstname + " " + lastname + "\nМы добавили новую информацию:\n" + childrenInfo.toStringEmail();
            emailService.sendEmail(userEmail, subject, message);
        }
    }
    public void handleDatabaseChangeDoctorsInfo(String cityName, DoctorsInfo doctorsInfo) {
        // Получаем список пользователей, подписанных на таблицу shops_info и указанный город
        List<UserLogin> usersSubscribedToChildrenInfo = userLoginService.findAllByUserDoctorsAndCityTrue(cityName);
        // Отправляем уведомления каждому из этих пользователей
        for (UserLogin userLogin : usersSubscribedToChildrenInfo) {
            String userEmail = userLogin.getUsername();
            String firstname = userLogin.getUser().getFirstname();
            String lastname = userLogin.getUser().getLastname();
            String subject = "Добавлена новая информация в категорию Врачи";
            String message = "Здравствуйте, " + firstname + " " + lastname + "\nМы добавили новую информацию:\n" + doctorsInfo.toStringEmail();
            emailService.sendEmail(userEmail, subject, message);
        }
    }
    public void handleDatabaseHairBeautyInfo(String cityName, HairBeautyInfo hairBeautyInfo) {
        // Получаем список пользователей, подписанных на таблицу shops_info и указанный город
        List<UserLogin> usersSubscribedToChildrenInfo = userLoginService.findAllByUserHairBeautyAndCityTrue(cityName);
        // Отправляем уведомления каждому из этих пользователей
        for (UserLogin userLogin : usersSubscribedToChildrenInfo) {
            String userEmail = userLogin.getUsername();
            String firstname = userLogin.getUser().getFirstname();
            String lastname = userLogin.getUser().getLastname();
            String subject = "Добавлена новая информация в категорию Парикмахеры и красота";
            String message = "Здравствуйте, " + firstname + " " + lastname + "\nМы добавили новую информацию:\n" + hairBeautyInfo.toStringEmail();
            emailService.sendEmail(userEmail, subject, message);
        }
    }
    public void handleDatabaseChangeLegalServicesInfo(String cityName, LegalServicesInfo legalServicesInfo) {
        // Получаем список пользователей, подписанных на таблицу shops_info и указанный город
        List<UserLogin> usersSubscribedToChildrenInfo = userLoginService.findAllByUserLegalServicesAndCityTrue(cityName);
        // Отправляем уведомления каждому из этих пользователей
        for (UserLogin userLogin : usersSubscribedToChildrenInfo) {
            String userEmail = userLogin.getUsername();
            String firstname = userLogin.getUser().getFirstname();
            String lastname = userLogin.getUser().getLastname();
            String subject = "Добавлена новая информация в категорию Юридические услуги";
            String message = "Здравствуйте, " + firstname + " " + lastname + "\nМы добавили новую информацию:\n" + legalServicesInfo.toStringEmail();
            emailService.sendEmail(userEmail, subject, message);
        }
    }
    public void handleDatabaseChangeRestaurantsInfo(String cityName, RestaurantsInfo restaurantsInfo) {
        // Получаем список пользователей, подписанных на таблицу shops_info и указанный город
        List<UserLogin> usersSubscribedToChildrenInfo = userLoginService.findAllByUserRestaurantsAndCityTrue(cityName);
        // Отправляем уведомления каждому из этих пользователей
        for (UserLogin userLogin : usersSubscribedToChildrenInfo) {
            String userEmail = userLogin.getUsername();
            String firstname = userLogin.getUser().getFirstname();
            String lastname = userLogin.getUser().getLastname();
            String subject = "Добавлена новая информация в категорию Рестораны";
            String message = "Здравствуйте, " + firstname + " " + lastname + "\nМы добавили новую информацию:\n" + restaurantsInfo.toStringEmail();
            emailService.sendEmail(userEmail, subject, message);
        }
    }
    public void handleDatabaseChangeShopsInfo(String cityName, ShopsInfo shopsInfo) {
        // Получаем список пользователей, подписанных на таблицу shops_info и указанный город
        List<UserLogin> usersSubscribedToChildrenInfo = userLoginService.findAllByUserShopsAndCityTrue(cityName);
        // Отправляем уведомления каждому из этих пользователей
        for (UserLogin userLogin : usersSubscribedToChildrenInfo) {
            String userEmail = userLogin.getUsername();
            String firstname = userLogin.getUser().getFirstname();
            String lastname = userLogin.getUser().getLastname();
            String subject = "Добавлена новая информация в категорию Магазины";
            String message = "Здравствуйте, " + firstname + " " + lastname + "\nМы добавили новую информацию:\n" + shopsInfo.toStringEmail();
            emailService.sendEmail(userEmail, subject, message);
        }
    }
    public void handleDatabaseChangeTranslatorsInfo(String cityName, TranslatorsInfo translatorsInfo) {
        // Получаем список пользователей, подписанных на таблицу shops_info и указанный город
        List<UserLogin> usersSubscribedToChildrenInfo = userLoginService.findAllByUserTranslatorsAndCityTrue(cityName);
        // Отправляем уведомления каждому из этих пользователей
        for (UserLogin userLogin : usersSubscribedToChildrenInfo) {
            String userEmail = userLogin.getUsername();
            String firstname = userLogin.getUser().getFirstname();
            String lastname = userLogin.getUser().getLastname();
            String subject = "Добавлена новая информация в категорию Переводчики";
            String message = "Здравствуйте, " + firstname + " " + lastname + "\nМы добавили новую информацию:\n" + translatorsInfo.toStringEmail();
            emailService.sendEmail(userEmail, subject, message);
        }
    }
//    public void handleDatabaseChangeNewsInfo(String cityName, NewsInfo newsInfo) {
//        // Получаем список пользователей, подписанных на таблицу shops_info и указанный город
//        List<UserLogin> usersSubscribedToChildrenInfo = userLoginService.findAllByUserChildrenAndCityTrue(cityName);
//        // Отправляем уведомления каждому из этих пользователей
//        for (UserLogin userLogin : usersSubscribedToChildrenInfo) {
//            String userEmail = userLogin.getUsername();
//            String subject = "Добавлена новая информация в категорию Дети";
//            String message = "Мы добавили новую информацию:\n" + newChildrenInfo.toStringEmail();
//            emailService.sendEmail(userEmail, "Новый элемент в базе данных", message);
//        }
//    }


}
