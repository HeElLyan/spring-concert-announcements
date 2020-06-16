package ru.itis.forms;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ru.itis.models.Photo;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationForm {
    private String name;
//    @NotEmpty(message = "Please enter your username.")
//    @Size(min = 1, message = "Your username must be between 1 and 15 characters")
    private String login;

//    @NotEmpty(message = "Please enter your password.")
//    @Size(min = 6, max = 15, message = "Your password must be between 6 and 15 characters")
    private String password1;

//    @NotEmpty(message = "Please repeat your password.")
//    @Size(min = 6, max = 15, message = "Incorrect")
    private String password2;

    private String city;

//    @NotEmpty
//    @Email(message = "{errors.incorrect.email}")
    private String email;

    private String metalGenre;
}
