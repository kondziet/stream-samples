package pl.kondziet.streamsamples.service;

import pl.kondziet.streamsamples.model.DTO.PostGET;
import pl.kondziet.streamsamples.model.entity.Post;
import pl.kondziet.streamsamples.model.subselects.PostWithLikes;

import java.util.List;

public interface PostService {

    Long addNewPost(Post post);
    Long addPostToLiked(String userEmail, Long postId);
    Long removePostFromLiked(String userEmail, Long postId);
    PostWithLikes getPostWithLikes(Long id);
}
