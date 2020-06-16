package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Comment;
import ru.itis.models.HeadQuarter;
import ru.itis.repositories.CommentRepository;
import ru.itis.repositories.HQRepository;
import ru.itis.repositories.UserRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private HQRepository hqRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Comment> findAllByHq(HeadQuarter hq) {
        return commentRepository.findAllByHq(hq);
    }

    @Override
    public List<Comment> findAllByDescription(String search) {
        return commentRepository.findAllByDescriptionContaining(search);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findAllByHqId(Long hqId) {
        return commentRepository.findAllByHqId(hqId);
    }

    @Override
    public void add(String newComment, Long hqId, Long userId) {
        Comment comment = Comment.builder()
                .description(newComment)
                .user(userRepository.findOne(userId))
                .hq(hqRepository.findOne(hqId))
                .build();
        commentRepository.save(comment);
    }

    @Override
    public void delete(Long commentId) {
        commentRepository.delete(commentId);
    }
}
