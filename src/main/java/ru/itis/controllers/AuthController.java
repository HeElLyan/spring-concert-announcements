package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.forms.UserAuthForm;
import ru.itis.services.AuthenticationService;
import ru.itis.validators.UserAuthFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthController {

//    @Autowired
//    private AuthenticationService authenticationService;

//    @Autowired
//    private UserAuthFormValidator userAuthFormValidator;

    @GetMapping("/login")
    public String getSignInPage(ModelMap model, HttpServletRequest request, Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
//        model.addAttribute("userAuthForm", new UserAuthForm());
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error1", true);
        }
//        model.addAttribute("error", error);

//        System.out.println(messageDataConfig.hello().saySomething());
//        String message = messageDataConfig.signIn().saySomething();
//        model.addAttribute("signIn", message);
        return "login";
    }

//    @InitBinder("userForm")
//    public void initUserFormValidator(WebDataBinder binder) {
//        binder.addValidators(userAuthFormValidator);
//    }

//    @PostMapping("/login")
//    public String signIn(@Valid @ModelAttribute("userAuthForm") UserAuthForm userAuthForm, BindingResult errors, RedirectAttributes attributes, Model model) {
//        if (errors.hasErrors()) {
//            // кладем специальный атрибут error
//            attributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());
//            // перенаправляем пользователя снова на эту же страницу
//            // но у же с атрибутом ошибки
//            return "redirect:/login";
////            System.out.println(errors.getAllErrors());
////            model.addAttribute("authForm", userAuthForm);
////            return "redirect:/login";
//        }
//        authenticationService.signIn(userAuthForm);
////        return "redirect:/main/" + user.getUsername() + "";
//        return "redirect:/user/profile";
//    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Authentication authentication, Model model) {
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }

//    @Autowired
//    FileInfoService fileInfoService;

//    @PostMapping("/upload")
//    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file, Authentication authentication){
//        String name = null;
//
//        if (!file.isEmpty() & fileInfoService.save(file)) {
//                fileInfoService.save(file);
//
//                User user = authenticationService.getUserByAuthentication(authentication);
//
//                photoService.add("loadFiles/" + name);
//                user.setPhoto(photoRepository.findByPath("loadFiles/" + name));
//                usersRepository.save(user);
//
//                return "You successfully uploaded file=" + name  +
//                        "<li><a href=\"/user/home\" class=\"news\">ok</a></li>\n";
//        } else {
//            return "You failed to upload " + name + " because the file was empty.";
//        }
//    }
}
