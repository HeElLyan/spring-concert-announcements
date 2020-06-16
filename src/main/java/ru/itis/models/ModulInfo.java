package ru.itis.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "plane")
public class ModulInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Photo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    private String text;

}
