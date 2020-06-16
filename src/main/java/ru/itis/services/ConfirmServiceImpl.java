package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.StateReg;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.Optional;

@Service
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean confirm(String confirmId) {
        Optional<User> userOptional = userRepository.findByConfirmId(confirmId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStateReg(StateReg.CONFIRMED);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
