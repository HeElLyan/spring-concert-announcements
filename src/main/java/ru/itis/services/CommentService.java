package ru.itis.services;

import ru.itis.forms.CommentForm;
import ru.itis.models.Comment;
import ru.itis.models.HeadQuarter;

import java.util.List;

public interface CommentService {
    List<Comment> findAllByHq(HeadQuarter hq);
    List<Comment> findAllByDescription(String search);
    List<Comment> findAllByHqId(Long hqId);

    Comment findById(Long id);

    void add(String newComment,Long hqId,Long userId);
    void delete(Long commentId);

}
