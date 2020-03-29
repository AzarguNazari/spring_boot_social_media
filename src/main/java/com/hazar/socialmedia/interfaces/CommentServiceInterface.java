package com.hazar.socialmedia.interfaces;

import com.hazar.socialmedia.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentServiceInterface {
    void saveComment(Comment comment);

    void deleteComment(Long commentID);

    void updateComment(Comment comment, Long commentID);

    Optional<Comment> getComment(Long commentID);

    List<Comment> getComments();
}
