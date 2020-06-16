package ru.itis.forms;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConcertForm {

//    @NotNull(message = "{errors.null.concert.city}")
    private String city;

//    @NotNull(message = "{errors.null.concert.description}")
//    @Size(min = 5)
    private String description;
}

