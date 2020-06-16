package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Comment;
import ru.itis.models.HeadQuarter;
import ru.itis.models.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByHq(HeadQuarter hq);
    List<Comment> findAllByUser(User user);
    List<Comment> findAllByUserNot(User user);
    List<Comment> findAllByHqId(Long hqId);
    List<Comment> findAllByDescriptionContaining(String search);
//    List<Comment> findAllByUser(User user);
    List<Comment> findAllByHqAndUser(HeadQuarter hq, User user);

    Comment findById(Long id);
}
