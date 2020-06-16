package ru.itis.models.scope;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class ApplicationData {

    @Getter
    private int count;

    public void onVisit() {
        count++;
    }

}
