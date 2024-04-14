//package de.aittr.team24_FP_backend.services.email;
//
//import de.aittr.team24_FP_backend.domain.User;
//import de.aittr.team24_FP_backend.domain.UserLogin;
//import de.aittr.team24_FP_backend.domain.categories.ChildrenInfo;
//import de.aittr.team24_FP_backend.repositories.UserRepository;
//import de.aittr.team24_FP_backend.services.UserLoginService;
//import de.aittr.team24_FP_backend.services.categories.ChildrenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class DatabaseChangeListener {
//
//    private EmailService emailService;
//    private UserLoginService userLoginService;
//    private ChildrenService childrenService;
//    @Autowired
//    public DatabaseChangeListener(EmailService emailService, UserLoginService userLoginService, ChildrenService childrenService) {
//        this.emailService = emailService;
//        this.userLoginService = userLoginService;
//        this.childrenService = childrenService;
//    }
//
//
//    @EventListener
//    public void handleDatabaseChange(DatabaseChangeEvent event) {
//        // Проверяем, какая таблица была изменена
//        if (event.getTableName().equals("children_info")) {
//            // Получаем информацию о новом элементе в базе данных о детях
//            ChildrenInfo newChildrenInfo = childrenService.getNewChildrenInfo();
//            // Получаем список пользователей, подписанных на таблицу shops_info и berlin
//            List<UserLogin> usersSubscribedToChildrenInfo = userLoginService.findAllByUserChildrenAndBerlinTrue();
//            // Отправляем уведомления каждому из этих пользователей
//            for (UserLogin userLogin : usersSubscribedToChildrenInfo) {
//                String userEmail = userLogin.getUsername();
//                String subject = "Добавлен новый элемент в базу данных о детях";
//                String message = "Добавлен новый элемент в базу данных о детях:\n" + newChildrenInfo.toStringEmail();
//                emailService.sendEmail(userEmail, "Новый элемент в базе данных", message);            }
//        }
//
//        // Повторите этот блок для других таблиц, если требуется отслеживать изменения в них
//    }
//}
