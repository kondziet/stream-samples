package pl.kondziet.streamsamples.service;

import pl.kondziet.streamsamples.model.entity.Post;

public interface PostService {

    void addNewPost(Post post);
    Long addPostToLiked(String userEmail, Long postId);
}
