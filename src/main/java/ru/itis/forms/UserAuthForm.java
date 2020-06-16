package ru.itis.forms;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthForm {
//    @NotEmpty(message = "Please enter your username.")
//    @Size(min = 1, message = "Your username must be at least 1 characters")
    private String login;

//    @NotEmpty(message = "Please enter your password.")
//    @Size(min = 6, max = 15, message = "Your password must be between 6 and 15 characters")
    private String password;
}
