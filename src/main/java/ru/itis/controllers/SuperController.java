package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.forms.ConcertForm;
import ru.itis.models.MetalGenre;
import ru.itis.services.*;
import ru.itis.validators.ConcertFormValidator;
import ru.itis.validators.UserRegistrationFormValidator;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static ru.itis.security.role.Role.SUPERUSER;

@PreAuthorize("hasRole('SUPERUSER')")
@Controller
@RequestMapping("/superuser")
public class SuperController {

    @Autowired
    private HQService hqService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private NewsPostService newsPostService;

    @GetMapping("/delete/hq/{id}")
    public String deleteHQ(@PathVariable("id") String hq_id, @ModelAttribute("model") ModelMap model) {
        hqService.delete(Long.parseLong(hq_id));
        return "redirect:/user/hq";
    }

    @GetMapping("/delete/news/{id}")
    public String deleteNews(@PathVariable("id") String id, @ModelAttribute("model") ModelMap model) {
        newsPostService.delete(Long.parseLong(id));
        return "redirect:/user/home";
    }

    @GetMapping("/delete/{hq_id}/comment/{com_id}")
    public String deleteComment(@PathVariable("hq_id") String hq_id, @PathVariable("com_id") String com_id, @ModelAttribute("model") ModelMap model) {
        commentService.delete(Long.parseLong(com_id));
        return "redirect:/user/hq/" + hq_id;
    }

    @Autowired
    private ConcertFormValidator concertFormValidator;

    @InitBinder("concertForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(concertFormValidator);
    }

    @GetMapping("/create_hq")
    public String createHQ(Authentication authentication, ModelMap model) {
        model.addAttribute(authService.getUserByAuthentication(authentication));
//        model.addAttribute("concertForm", new ConcertForm());
        return "create_hq";
    }

    @PostMapping("/save_hq")
    public String saveHQ(@Valid @ModelAttribute("concertForm") ConcertForm concertForm, BindingResult errors, RedirectAttributes attributes, Model model) {
//        System.out.println(errors.getAllErrors());
        if (errors.hasErrors()) {
            // кладем специальный атрибут error
            attributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());
            // перенаправляем пользователя снова на эту же страницу
            // но у же с атрибутом ошибки
            return "redirect:/superuser/create_hq";
        }
        hqService.add(concertForm);
//        model.addAttribute("concertForm", concertForm);
        return "redirect:/user/hq";
    }

    @GetMapping("/create_news")
    public String createNews(Authentication authentication, @ModelAttribute("model") ModelMap model){
        model.addAttribute(authService.getUserByAuthentication(authentication));
        boolean superuser = SUPERUSER.equals(authService.getUserByAuthentication(authentication).getRole());
        model.addAttribute("superuser", superuser);
        return "create_news";
    }

    @PostMapping("/upload")
    public @ResponseBody String handleFileUpload(/*@RequestParam("name") String name,*/
                                                 @RequestParam("file") MultipartFile file,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("title") String title){
        String name = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();

                String rootPath = "/Users/heel/IdeaProjects/2Course/4sem-spring/MyProjects/spring-concert-announcements-app/src/main/resources/static/images";  //try also "C:\path\"
                File dir = new File(rootPath + File.separator + "loadFiles");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

//                logger.info("uploaded: " + uploadedFile.getAbsolutePath());
                photoService.add("loadFiles/" + name);
                newsPostService.add(title, description,"loadFiles/" + name);
//                putDB(title, description,"loadFiles/" + name);
                return "You successfully uploaded file=" + name  +
                        "<li><a href=\"/user/home\" class=\"news\">ok</a></li>\n";

            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

//    private void putDB(String title, String description, String filePath) {
//        photoService.add(filePath);
//        newsPostService.add(title, description, filePath);
//    }
}
