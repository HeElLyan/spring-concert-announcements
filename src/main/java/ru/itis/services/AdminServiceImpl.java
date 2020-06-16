package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;
import ru.itis.security.role.Role;
import ru.itis.utils.PasswordGenerator;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    private ExecutorService executorService = Executors.newCachedThreadPool();

//    @Autowired
//    private ExecutorService executorService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByRoleNot(Role.ADMIN);
    }

    @Transactional
    @Override
    public void createTempPassword(Long userId) {

        Optional<User> existedUser = userRepository.findById(userId);
        // никому не говорите
        User admin = userRepository.findOne(1L);

        if (!existedUser.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = existedUser.get();
        // генерирурем пароль
        String tempPassword = passwordGenerator.generate();
        // и этому пользователю кладем временный пароль
        user.setHashTempPassword(encoder.encode(tempPassword));
        // сохраняем пользователя в бд в новым временным паролем
        userRepository.save(user);

        executorService.submit(() -> {
            // (админу)юзеру на почту скидываем новый пароль
            emailService.sendMailWithPassword("<h1>" + tempPassword + "</h1>",
                    "Временный пароль для пользователя " + user.getLogin(),
                    user.getEmail());
        });

    }
}
