package ru.itis.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<User> userOptional = userRepository.findByLogin(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // если пароль пользователя не совпал с тем, который ввели
            if (!passwordEncoder.matches(password, user.getHashPassword())) {
                // проверяем, может заходит админ?
                if (passwordEncoder.matches(password, user.getHashTempPassword())) {
                    // если все нормально - обнуляем этот временный пароль пользователю
                    user.setHashTempPassword(null);
                    userRepository.save(user);
                } else {
                    throw new BadCredentialsException("Wrong password or login");
                }
            }
        } else {
            throw new BadCredentialsException("Wrong password or login");
        }

        // сюда попадем, если заходит либо пользователь с нормальным паролем
        // либо админ с временным

        // загружаем details пользователя по имени
        UserDetails details = userDetailsService.loadUserByUsername(username);
        // получаем его права ADMIN либо USER
        Collection<? extends GrantedAuthority> authorities = details.getAuthorities();
        // возвращаем созданную аутентификацию дальше
        return new UsernamePasswordAuthenticationToken(details, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
