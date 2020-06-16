package ru.itis.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.itis.forms.UserRegistrationForm;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.Optional;

@Slf4j
@Component
public class UserRegistrationFormValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(UserRegistrationForm.class.getName());
    }

    // Валидируем объект target
    @Override
    public void validate(Object target, Errors errors) {
        // Валидатору приходит все подряд
        // Мы преобразуем входные данные в UserRegistrationForm
        UserRegistrationForm form = (UserRegistrationForm)target;

        // получили/не получили пользователя
        Optional<User> existedUser = userRepository.findByLogin(form.getLogin());
        // если пользователь есть
        if (existedUser.isPresent()) {
            errors.reject("bad.login", "Login isn't available");
        }
        // проверяем на пустоту логин или пароль
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.login", "Empty login");
        log.error("Empty login");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password1", "empty.password1", "Empty password");
        log.error("Empty password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "empty.password2", "Empty password");
        log.error("Empty second password");

        if (!form.getPassword1().equals(form.getPassword2())) {
            errors.reject("bad.passwords.equals","Passwords are not the same");
        }

    }
}
