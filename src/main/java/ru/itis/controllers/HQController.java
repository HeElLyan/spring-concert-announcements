package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.forms.CommentForm;
import ru.itis.forms.SearchForm;
import ru.itis.models.HeadQuarter;
import ru.itis.models.User;
import ru.itis.services.AuthenticationService;
import ru.itis.services.CommentService;
import ru.itis.services.HQService;

import java.util.LinkedList;
import java.util.List;

import static ru.itis.security.role.Role.ADMIN;
import static ru.itis.security.role.Role.SUPERUSER;

@PreAuthorize("isAuthenticated()")
@Controller
public class HQController {

    @Autowired
    private HQService hqService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/user/hq/comment/{id}")
    public String commentOn(@PathVariable("id") String hq_id, CommentForm commentForm, Authentication authentication, @ModelAttribute("model") ModelMap model) {
        commentService.add(commentForm.getComment(),Long.parseLong(hq_id),authService.getUserByAuthentication(authentication).getId());
        System.out.println(commentForm.getComment());
        return "redirect:/user/hq/" + hq_id;
    }

    @GetMapping("/user/hq/{id}")
    public String hq(@PathVariable("id") String rawId, SearchForm searchForm, Authentication authentication, @ModelAttribute("model") ModelMap model) {
        Long id = Long.parseLong(rawId);
        HeadQuarter hq = hqService.findById(id);
        model.addAttribute("hq",hq);
        model.addAttribute(authService.getUserByAuthentication(authentication));
        boolean superuser = SUPERUSER.equals(authService.getUserByAuthentication(authentication).getRole());
        model.addAttribute("superuser", superuser);

        if (searchForm.getSearch() == null) {
            model.addAttribute("comments",commentService.findAllByHq(hq));
        } else {
            if (searchForm.getSearch().isEmpty() ){
                model.addAttribute("comments",commentService.findAllByHq(hq));
            } else {
                model.addAttribute("comments",commentService
                        .findAllByDescription(searchForm.getSearch()));
            }
        }
        return "hq";
    }

    @GetMapping("/user/hq")
    public String headQuarter(SearchForm searchForm, Authentication authentication, @ModelAttribute("model") ModelMap model) {
        List<HeadQuarter> headQuarters = new LinkedList<>();
        if (searchForm.getSearch() == null) {
            headQuarters.clear();
            headQuarters = hqService.findAll();
        } else {
            if (searchForm.getSearch().isEmpty() ){
                headQuarters.clear();
                headQuarters = hqService.findAll();
            } else {
                headQuarters.clear();
                headQuarters = hqService.findAllByCity(searchForm.getSearch());
            }
        }

        User user = authService.getUserByAuthentication(authentication);

//        List<HeadQuarterDto> headQuarterRecommendationList = hqService.recommend(user);

//        model.addAttribute("hq_recommendation_list", headQuarterRecommendationList);

        List<HeadQuarter> headQuarterListRecommended = hqService.recommend(user);
        System.out.println("!!!!!!!!" + headQuarterListRecommended);
        model.addAttribute("hq_list_recommended", headQuarterListRecommended);

        model.addAttribute("hq_list", headQuarters);
        model.addAttribute(authService.getUserByAuthentication(authentication));
        boolean superuser = SUPERUSER.equals(user.getRole());
        model.addAttribute("superuser", superuser);

        boolean admin = ADMIN.equals(user.getRole());
        model.addAttribute("admin", admin);
        return "head_quarters";
    }
}
