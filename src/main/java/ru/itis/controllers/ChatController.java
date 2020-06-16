package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.models.User;
import ru.itis.services.AuthenticationService;

import java.util.UUID;

import static ru.itis.security.role.Role.ADMIN;

@PreAuthorize("isAuthenticated()")
@Controller
public class ChatController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/chat")
    public String getChatPage(Model model, Authentication authentication) {
        model.addAttribute("pageId", UUID.randomUUID().toString());

        boolean admin = ADMIN.equals(authenticationService.getUserByAuthentication(authentication).getRole());
        model.addAttribute("admin", admin);

        User user = authenticationService.getUserByAuthentication(authentication);
        model.addAttribute("user", user);
        return "chat";
    }
}
