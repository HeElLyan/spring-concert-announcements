package ru.itis.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.forms.UserRegistrationForm;
import ru.itis.services.EmailService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Aspect
@Component
public class MailConfirmationAspect {

//    @Autowired
//    ExecutorService executorService;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Autowired
    private EmailService emailService;

    @AfterReturning(value = "execution(* ru.itis.services.RegistrationService.signUp(*))", returning = "confirmId")
    public void sendConfirmationMail(JoinPoint joinPoint, String confirmId) {
        UserRegistrationForm userRegistrationForm = (UserRegistrationForm) joinPoint.getArgs()[0];
        String email = userRegistrationForm.getEmail();

        executorService.submit(() ->
                emailService.sendMail("<h1>Confirmation link: http://localhost:8080/confirm/" + confirmId + "</h1>",
                        "Submit your registration to be a true metallist", email));
    }
}

