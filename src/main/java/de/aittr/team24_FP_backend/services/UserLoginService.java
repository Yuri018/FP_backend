package de.aittr.team24_FP_backend.services;

import de.aittr.team24_FP_backend.domain.Role;
import de.aittr.team24_FP_backend.domain.User;
import de.aittr.team24_FP_backend.domain.UserLogin;
import de.aittr.team24_FP_backend.exception_handling.exceptions.UserAlreadyExistsException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.UserLoginNotFoundException;
import de.aittr.team24_FP_backend.exception_handling.exceptions.UserLoginValidationException;
import de.aittr.team24_FP_backend.repositories.UserLoginRepository;
import de.aittr.team24_FP_backend.repositories.UserRepository;
import de.aittr.team24_FP_backend.security.sec_service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLoginService implements UserDetailsService {

    private UserLoginRepository userLoginRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    public UserLoginService(UserLoginRepository userLoginRepository, UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userLoginRepository = userLoginRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin userLogin = userLoginRepository.findByUsername(username);

        if (userLogin == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userLogin;
    }

    @Transactional
    public UserLogin register(UserLogin userLogin) {
        UserLogin foundUserLogin = userLoginRepository.findByUsername(userLogin.getUsername());

        if (foundUserLogin != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует!");
        }
        userLogin.setId(0);
        userLogin.clearRoles();

        Role role = new Role(2, "ROLE_USER");
        userLogin.addRole(role);

        User user = new User();

        userLogin.setUser(user);
        user.setUserLogin(userLogin);

        String encodedPassword = encoder.encode(userLogin.getPassword());
        userLogin.setPassword(encodedPassword);

        try {
            return userLoginRepository.save(userLogin);

        } catch (Exception e) {
            throw new UserLoginValidationException("Ошибка валидации пользователя: " + e.getMessage());
        }
    }
    public UserLogin registerAdmin(UserLogin userLogin) {
        UserLogin foundUserLogin = userLoginRepository.findByUsername(userLogin.getUsername());

        if (foundUserLogin != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует!");
        }
        userLogin.setId(0);
        userLogin.clearRoles();

        Role role1 = new Role(1, "ROLE_ADMIN");
        Role role2 = new Role(2, "ROLE_USER");
        userLogin.addRole(role1);
        userLogin.addRole(role2);

        String encodedPassword = encoder.encode(userLogin.getPassword());
        userLogin.setPassword(encodedPassword);
        try {
            return userLoginRepository.save(userLogin);

        } catch (Exception e) {
            throw new UserLoginValidationException("Ошибка валидации пользователя: " + e.getMessage());
        }

    }

    public List<UserLogin> getAllUsersLogin() {
        return userLoginRepository.findAll();
    }

    public UserLogin findByUsername(String username) {
        UserLogin user = userLoginRepository.findByUsername(username);

        if (user == null) {
            throw new UserLoginNotFoundException(String.format(
                    "There are no users username [%s] in the database", username));
        }
        return user;
}

    public UserLogin findById(int id) {
        UserLogin user = userLoginRepository.findById(id).orElse(null);

        if (user == null) {
            throw new UserLoginNotFoundException(String.format(
                    "There are no users named [%d] in the database", id));
        }
        return user;
    }

    @Transactional
    public void deleteById(int id) {
        UserLogin user = userLoginRepository.findById(id).orElse(null);

        if (user != null) {
            userLoginRepository.delete(user);
        } else {
            throw new UserLoginNotFoundException(String.format(
                    "There are no users named [%d] in the database", id));
        }

    }

    public List<UserLogin> findAllByUserChildrenInfoTrue() {
        return userLoginRepository.findAllByUserChildrenInfoTrue();
    }


    public List<UserLogin> findAllByUserChildrenAndCityTrue(String cityName) {
        List<UserLogin> usersWithChildrenInfo = userLoginRepository.findAllByUserChildrenInfoTrue();
        return usersWithChildrenInfo.stream()
                .filter(userLogin -> isCityTrueForUser(userLogin, cityName))
                .toList();
    }
    public List<UserLogin> findAllByUserDoctorsAndCityTrue(String cityName) {
        List<UserLogin> usersWithChildrenInfo = userLoginRepository.findAllByUserDoctorsInfoTrue();
        return usersWithChildrenInfo.stream()
                .filter(userLogin -> isCityTrueForUser(userLogin, cityName))
                .toList();
    }

    public List<UserLogin> findAllByUserHairBeautyAndCityTrue(String cityName) {
        List<UserLogin> usersWithChildrenInfo = userLoginRepository.findAllByUserHairBeautyInfoTrue();
        return usersWithChildrenInfo.stream()
                .filter(userLogin -> isCityTrueForUser(userLogin, cityName))
                .toList();
    }

    public List<UserLogin> findAllByUserLegalServicesAndCityTrue(String cityName) {
        List<UserLogin> usersWithChildrenInfo = userLoginRepository.findAllByUserLegalServicesInfoTrue();
        return usersWithChildrenInfo.stream()
                .filter(userLogin -> isCityTrueForUser(userLogin, cityName))
                .toList();
    }

//    public List<UserLogin> findAllByUserPharmaciesAndCityTrue(String cityName) {
//        List<UserLogin> usersWithChildrenInfo = userLoginRepository.findAllByUserPharmaciesInfoTrue();
//        return usersWithChildrenInfo.stream()
//                .filter(userLogin -> isCityTrueForUser(userLogin, cityName))
//                .toList();
//    }

    public List<UserLogin> findAllByUserRestaurantsAndCityTrue(String cityName) {
        List<UserLogin> usersWithChildrenInfo = userLoginRepository.findAllByUserRestaurantsInfoTrue();
        return usersWithChildrenInfo.stream()
                .filter(userLogin -> isCityTrueForUser(userLogin, cityName))
                .toList();
    }

    public List<UserLogin> findAllByUserShopsAndCityTrue(String cityName) {
        List<UserLogin> usersWithChildrenInfo = userLoginRepository.findAllByUserShopsInfoTrue();
        return usersWithChildrenInfo.stream()
                .filter(userLogin -> isCityTrueForUser(userLogin, cityName))
                .toList();
    }

    public List<UserLogin> findAllByUserTranslatorsAndCityTrue(String cityName) {
        List<UserLogin> usersWithChildrenInfo = userLoginRepository.findAllByUserTranslatorsInfoTrue();
        return usersWithChildrenInfo.stream()
                .filter(userLogin -> isCityTrueForUser(userLogin, cityName))
                .toList();
    }

    public List<UserLogin> findAllByUserNewsAndCityTrue(String cityName) {
        List<UserLogin> usersWithChildrenInfo = userLoginRepository.findAllByUserNewsInfoTrue();
        return usersWithChildrenInfo.stream()
                .filter(userLogin -> isCityTrueForUser(userLogin, cityName))
                .toList();
    }



    private boolean isCityTrueForUser(UserLogin userLogin, String cityName) {
        switch (cityName.toLowerCase()) {
            case "berlin":
                return userLogin.getUser().isBerlin();
            case "muenchen":
                return userLogin.getUser().isMuenchen();
            case "dusseldorf":
                return userLogin.getUser().isDusseldorf();
            case "hamburg":
                return userLogin.getUser().isHamburg();
            case "frankfurt":
                return userLogin.getUser().isFrankfurt();
            default:
                return false;
        }
    }


    @Transactional
    public void updateUserSubscriptionsByToken(User newUser, String userName) {

        UserLogin userLogin = userLoginRepository.findByUsername(userName);

        User user = userLogin.getUser();
        if (user == null) {
            throw new RuntimeException("Отсутствует связанный объект User для пользователя");
        }

        // Обновляем подписки пользователя
        user.setFirstname(newUser.getFirstname());
        user.setLastname(newUser.getLastname());
        user.setChildren_info(newUser.isChildren_info());
        user.setDoctors_info(newUser.isDoctors_info());
        user.setHair_beauty_info(newUser.isHair_beauty_info());
        user.setLegal_services_info(newUser.isLegal_services_info());
        user.setRestaurants_info(newUser.isRestaurants_info());
        user.setShops_info(newUser.isShops_info());
        user.setTranslators_info(newUser.isTranslators_info());
        user.setBerlin(newUser.isBerlin());
        user.setMuenchen(newUser.isMuenchen());
        user.setDusseldorf(newUser.isDusseldorf());
        user.setHamburg(newUser.isHamburg());
        user.setFrankfurt(newUser.isFrankfurt());

        userRepository.save(user);
    }


}
