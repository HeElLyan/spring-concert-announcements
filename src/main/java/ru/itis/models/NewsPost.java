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
@Table(name = "news_post")
public class NewsPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String headLine;
    private String description;

    @OneToOne(targetEntity = Photo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private Photo photo;

//    @ManyToMany
//    private List<User> likes;
}
