package ru.itis.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.itis.forms.ConcertForm;

@Slf4j
@Component
public class ConcertFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(ConcertForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "empty.city", "Empty city");
        log.error("Empty city");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "empty.description", "Empty description");
        log.error("Empty description");

    }
}

