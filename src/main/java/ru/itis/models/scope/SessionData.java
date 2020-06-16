package ru.itis.models.scope;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;

@SessionScope
@Data
@Component
public class SessionData {

    private LocalDateTime localDateTime;

}
