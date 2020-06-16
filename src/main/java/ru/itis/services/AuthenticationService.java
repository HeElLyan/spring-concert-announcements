package ru.itis.services;

import org.springframework.security.core.Authentication;
import ru.itis.forms.UserAuthForm;
import ru.itis.forms.UserDto;
import ru.itis.models.User;


public interface AuthenticationService {

    UserDto signIn(UserAuthForm userAuthForm);

    User getUserByAuthentication(Authentication authentication);

    void makeSuperUser(Long id);

    void kill(Long id);
}
