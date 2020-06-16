package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.models.User;
import ru.itis.security.role.Role;
import ru.itis.services.AuthenticationService;

@Controller
public class RootController {

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/")
    public String root(Authentication authentication) {
        if (authentication != null) {
            User user = authenticationService.getUserByAuthentication(authentication);
            if (user.getRole().equals(Role.USER) || user.getRole().equals(Role.SUPERUSER) ) {
                return "redirect:/user/profile";
            } else if (user.getRole().equals(Role.ADMIN)) {
                return "redirect:/admin/users";
            }
        }
        return "redirect:/login";
    }
}
