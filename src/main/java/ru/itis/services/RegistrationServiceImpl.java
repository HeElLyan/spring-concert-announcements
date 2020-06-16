package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.forms.UserRegistrationForm;
import ru.itis.models.StateReg;
import ru.itis.models.User;
import ru.itis.repositories.HQRepository;
import ru.itis.repositories.PhotoRepository;
import ru.itis.repositories.UserRepository;
import ru.itis.security.role.Role;
import ru.itis.security.states.State;

import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private HQRepository hqRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
//    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String signUp(UserRegistrationForm userForm) {

//        boolean isEmailAvailable = usersRepository.findByEmail(userForm.getEmail()).isPresent();
//        if (!isEmailAvailable) {
//            throw new IllegalArgumentException("Email already exists");
//        }

        String confirmId = UUID.randomUUID().toString();

        User newUser = User.builder()
                .login(userForm.getLogin())
                .hashPassword(passwordEncoder.encode(userForm.getPassword1()))
                .name(userForm.getName())
                .email(userForm.getEmail())
                .headQuarter(hqRepository.findByCity(userForm.getCity()))
                .role(Role.USER)
                .metalGenre(userForm.getMetalGenre())
                .state(State.CONFIRMED)
                .stateReg(StateReg.UNCONFIRMED)
                .confirmId(confirmId)
                .build();

        userRepository.save(newUser);

        return confirmId;
    }

    @Override
    public void signUpWithGoogle(User user) {
        userRepository.save(user);
    }
}
