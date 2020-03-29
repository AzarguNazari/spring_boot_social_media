package com.hazar.socialmedia.interfaces;

import com.hazar.socialmedia.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostServiceInterface {
    void savePost(Post post);

    void deletePost(Long postID);

    void updatePost(Post post, Long postID);

    Optional<Post> getPost(Long postID);

    List<Post> getPosts();
}
