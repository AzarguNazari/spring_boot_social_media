package com.facebook.example.interfaces;

import com.facebook.example.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentServiceInterface {
    void saveComment(Comment comment);

    void deleteComment(Long commentID);

    void updateComment(Comment comment, Long commentID);

    Optional<Comment> getComment(Long commentID);

    List<Comment> getComments();
}
