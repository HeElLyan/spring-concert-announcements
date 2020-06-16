package ru.itis.models.scope;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "message",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MessageData {

    @Getter
    private int count;

    public void onVisit() {
        count++;
    }
}
