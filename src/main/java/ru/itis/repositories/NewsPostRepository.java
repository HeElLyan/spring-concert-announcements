package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.NewsPost;

import java.util.List;

public interface NewsPostRepository extends JpaRepository<NewsPost, Long> {
    //Optional<NewsPost> findOneByLogin(String login);
    //List<NewsPost> findAllByRole(Role role);
    List<NewsPost> findAllById(Long newsId);
    List<NewsPost> findAllByDescription(String description);
//    List<NewsPost> findAllByLikesContains(User user);
}
