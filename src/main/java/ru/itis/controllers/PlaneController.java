package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itis.models.ModulInfo;
import ru.itis.models.NewsPost;
import ru.itis.services.AuthenticationService;
import ru.itis.services.NewsPostService;
import ru.itis.services.PlaneService;

import java.util.List;

import static ru.itis.security.role.Role.ADMIN;

@PreAuthorize("isAuthenticated()")
@Controller
public class PlaneController {

    @Autowired
    private PlaneService planeService;

    @Autowired
    private AuthenticationService authService;

    @GetMapping("/plane")
    public String plane(Authentication authentication, @ModelAttribute("model") ModelMap model) {
        //получаем юзера через секьюрити
        model.addAttribute(authService.getUserByAuthentication(authentication));

        List<ModulInfo> info = planeService.getInfo();
        model.addAttribute("news",info);

        boolean admin = ADMIN.equals(authService.getUserByAuthentication(authentication).getRole());
        model.addAttribute("admin", admin);
        return "plane";
    }
}
