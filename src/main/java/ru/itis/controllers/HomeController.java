package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.models.NewsPost;
import ru.itis.models.State;
import ru.itis.models.Ticket;
import ru.itis.services.AuthenticationService;
import ru.itis.services.NewsPostService;
import ru.itis.services.TicketService;

import java.util.List;

import static ru.itis.security.role.Role.ADMIN;
import static ru.itis.security.role.Role.SUPERUSER;

@PreAuthorize("isAuthenticated()")
@Controller
public class HomeController {

    @Autowired
    private NewsPostService newsService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/user/please/{id}")
    public String tick(@PathVariable("id") String id) {
        ticketService.addNew(Long.parseLong(id));
        return "redirect:/user/home";
    }

    @GetMapping("/user/home")
    public String home(Authentication authentication, @ModelAttribute("model") ModelMap model) {
        List<NewsPost> news = newsService.getNews();
        model.addAttribute("news",news);
        model.addAttribute(authService.getUserByAuthentication(authentication));

        try {
            model.addAttribute("currentState",
                    ticketService.getOne(
                            authService.getUserByAuthentication(authentication).getId()
                    ).getState()
            );
        } catch (NullPointerException e) {
            System.out.println("error");
        }

        boolean superuser = SUPERUSER.equals(authService.getUserByAuthentication(authentication).getRole());
        model.addAttribute("superuser", superuser);

        boolean admin = ADMIN.equals(authService.getUserByAuthentication(authentication).getRole());
        model.addAttribute("admin", admin);

        if (admin) {
            List<Ticket> tickets = ticketService.getTickets();
            model.addAttribute("tickets", tickets);
            for (Ticket ticket : tickets) {
                ticketService.changeToInProgress(ticket);
            }
        }
        return "home";
    }


}
