package ru.itis.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.itis.forms.UserAuthForm;

@Component
public class UserAuthFormValidator implements Validator{

//    @Autowired
//    private UsersRepository usersRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(UserAuthForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Валидатору приходит все подряд
        // Мы преобразуем входные данные в UserRegistrationForm
//        UserAuthForm form = (UserAuthForm) target;

//        // если нашли пользователя по логину, то выбрасываем ошибку
//        Optional<User> existedUser = usersRepository.findOneByLogin(form.getLogin());
//        if (existedUser.isPresent()) {
//            errors.reject("bad.login", "Логин занят");
//        }
        // проверяем на пустоту логин или пароль
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.login", "Пустой логин");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.password", "Пустой пароль");

    }
}
