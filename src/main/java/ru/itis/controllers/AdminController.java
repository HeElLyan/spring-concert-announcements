package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.services.AdminService;
import ru.itis.services.AuthenticationService;
import ru.itis.services.TicketService;

import static ru.itis.security.role.Role.ADMIN;


@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/users")
    public String getMainAdminPage(@ModelAttribute("model") ModelMap model, Authentication authentication) {
        model.addAttribute("users", adminService.getAllUsers());
        boolean admin = ADMIN.equals(authenticationService.getUserByAuthentication(authentication).getRole());
        model.addAttribute("admin", admin);
        return "admin";
    }

    @GetMapping("/kill/{id}")
    public String kill(@PathVariable("id") String id) {
        authenticationService.kill(Long.parseLong(id));
        return "redirect:/admin/users";
    }

    @GetMapping("/make_super/{id}")
    public String allowSuper(@PathVariable("id") String id) {
        ticketService.changToClosed(Long.parseLong(id));
        authenticationService.makeSuperUser(Long.parseLong(id));
        return "redirect:/admin/users";
    }

    @GetMapping("/dis/{id}")
    public String dis(@PathVariable("id") String id) {
        ticketService.changToClosed(Long.parseLong(id));
        return "redirect:/user/home";
    }

    // позволяет получить временный пароль
    // для того, чтобы зайти под пользователем каким-либо
    @GetMapping("/password/temp/{user-id}")
    public String getNewPasswordOfUserPage(@ModelAttribute("model") ModelMap model,
                                           @PathVariable("user-id") Long userId) {
        // генерируем пароль и отправляем на почту
        adminService.createTempPassword(userId);
        // скидываем админу страничку - что пароль отправлен на почту
        return "temp_password_page";
    }
}
