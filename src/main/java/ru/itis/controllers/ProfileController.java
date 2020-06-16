package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.User;
import ru.itis.models.scope.ApplicationData;
import ru.itis.models.scope.MessageData;
//import ru.itis.models.scope.RequestData;
import ru.itis.models.scope.SessionData;
import ru.itis.repositories.PhotoRepository;
import ru.itis.repositories.UserRepository;
import ru.itis.services.AuthenticationService;
import ru.itis.services.PhotoService;
//import ru.itis.validators.UserAuthFormValidator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Optional;

import static ru.itis.security.role.Role.ADMIN;

@Controller
public class ProfileController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private SessionData sessionData;

    @Autowired
    private ApplicationData applicationData;

//    @Autowired
//    private RequestData requestData;

    @Autowired
    private MessageData messageData;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/profile")
    public String getProfilePage(Authentication authentication, @ModelAttribute("model") ModelMap model) {
        User user = authenticationService.getUserByAuthentication(authentication);
        model.addAttribute("user", user);

        boolean admin = ADMIN.equals(authenticationService.getUserByAuthentication(authentication).getRole());
        model.addAttribute("admin", admin);

        if (sessionData.getLocalDateTime() == null) {
            sessionData.setLocalDateTime(LocalDateTime.now());
        }

        model.addAttribute("time", sessionData.getLocalDateTime().toString());
        model.addAttribute("enterTimes", applicationData.getCount());
//        model.addAttribute("header", requestData.getUserAgent());
        applicationData.onVisit();

        int count = messageData.getCount();
        messageData.onVisit();
        model.addAttribute("messagesNumbers", count);
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/profile/{id}")
    public String getUserById(@PathVariable("id") Long id, @ModelAttribute("model") ModelMap model, Authentication authentication) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        model.addAttribute("user", user);

        boolean admin = ADMIN.equals(authenticationService.getUserByAuthentication(authentication).getRole());
        model.addAttribute("admin", admin);

        if (sessionData.getLocalDateTime() == null) {
            sessionData.setLocalDateTime(LocalDateTime.now());
        }
        model.addAttribute("time", sessionData.getLocalDateTime().toString());
        model.addAttribute("enterTimes", applicationData.getCount());
//        model.addAttribute("header", requestData.getUserAgent());
        applicationData.onVisit();

        int count = messageData.getCount();
        messageData.onVisit();
        model.addAttribute("messagesNumbers", count);

        return "profile";
    }

    @PostMapping("/upload")
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file, Authentication authentication){
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

                User user = authenticationService.getUserByAuthentication(authentication);

                photoService.add("loadFiles/" + name);
                user.setPhoto(photoRepository.findByPath("loadFiles/" + name));
                userRepository.save(user);

                return "You successfully uploaded file=" + name  +
                        "<li><a href=\"/user/home\" class=\"news\">ok</a></li>\n";

            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
}
