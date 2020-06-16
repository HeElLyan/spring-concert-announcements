package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.forms.UserAuthForm;
import ru.itis.forms.UserDto;
import ru.itis.models.Ticket;
import ru.itis.models.User;
import ru.itis.repositories.TicketRepository;
import ru.itis.repositories.UserRepository;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.security.role.Role;

import java.util.Optional;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto signIn(UserAuthForm userAuthForm) {
        String username = userAuthForm.getLogin();
        String password = userAuthForm.getPassword();

        User candidate = userRepository.findByLogin(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String expected = candidate.getHashPassword();
        boolean isPasswordCorrect = passwordEncoder.matches(password, expected);
        if (!isPasswordCorrect) {
            throw new IllegalArgumentException("Incorrect password");
        }

        return UserDto.from(candidate);
    }

    @Override
    public User getUserByAuthentication(Authentication authentication) {
        UserDetailsImpl currentUserDetails = (UserDetailsImpl)authentication.getPrincipal();
        User currentUserModel = currentUserDetails.getUser();
        Long currentUserId = currentUserModel.getId();
        Optional<User> userOptional = userRepository.findById(currentUserId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else throw new AccessDeniedException("User not found");
    }

    @Override
    public void makeSuperUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole(Role.SUPERUSER);
            userRepository.save(user);
        } else throw new AccessDeniedException("User not found");
    }

    @Override
    public void kill(Long userId) {
        Ticket ticket = ticketRepository.findByUserId(userId);
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            ticketRepository.delete(ticket);
            User user = userOptional.get();
            userRepository.delete(user);
        } else throw new AccessDeniedException("User not found");
    }
}
