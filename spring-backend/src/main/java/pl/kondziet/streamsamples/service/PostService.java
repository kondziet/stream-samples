package pl.kondziet.streamsamples.service;

import pl.kondziet.streamsamples.model.entity.Post;
import pl.kondziet.streamsamples.model.subselect.PostWithLikes;

import java.util.List;

public interface PostService {
    Post findPostById(Long postId);

    Long addNewPost(Post post);
    Long addPostToLiked(String userEmail, Long postId);
    Long removePostFromLiked(String userEmail, Long postId);
    List<PostWithLikes> getPostsWithLikes();
    PostWithLikes getPostWithLikes(Long id);
}
