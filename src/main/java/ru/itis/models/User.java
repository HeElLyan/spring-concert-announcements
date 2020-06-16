package ru.itis.models;

import lombok.*;
import ru.itis.security.role.Role;
import ru.itis.security.states.State;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String login;

    @ManyToOne(targetEntity = HeadQuarter.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "head_quarter_id")
    private HeadQuarter headQuarter;

    @OneToOne(targetEntity = Photo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    private String hashPassword;
    private String hashTempPassword;

    @Column(name = "confirm_id")
    private String confirmId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    private StateReg stateReg;

    private String email;

    @Column(name = "metalGenre")
    private String metalGenre;

    @ManyToOne(targetEntity = Ticket.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
