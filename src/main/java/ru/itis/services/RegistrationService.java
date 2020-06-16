package ru.itis.services;

import ru.itis.forms.UserRegistrationForm;
import ru.itis.models.User;

public interface RegistrationService {
    String signUp(UserRegistrationForm userForm);
    void signUpWithGoogle(User user);
}
