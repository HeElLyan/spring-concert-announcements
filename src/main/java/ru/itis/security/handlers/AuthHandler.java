//package ru.itis.security.handlers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import ru.itis.models.User;
//import ru.itis.services.RegistrationService;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class AuthHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//    @Autowired
//    private RegistrationService registrationService;
//
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        User user = (User) authentication.getPrincipal();
//        registrationService.signUpWithGoogle(user);
//        super.onAuthenticationSuccess(request, response, authentication);
//    }
//}
