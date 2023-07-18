package pl.kondziet.streamsamples.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.kondziet.streamsamples.model.DTO.PostGET;
import pl.kondziet.streamsamples.model.entity.Post;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.model.repository.PostRepository;
import pl.kondziet.streamsamples.model.repository.UserRepository;
import pl.kondziet.streamsamples.model.subselects.PostWithLikes;
import pl.kondziet.streamsamples.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    @Override
    public Long addNewPost(Post post) {
        return postRepository.save(post).getId();
    }

    @Override
    @Transactional
    public Long addPostToLiked(String userEmail, Long postId) {
        User user = userRepository.findByEmail(userEmail).orElse(null);
        Post post = postRepository.findById(postId).orElse(null);

        user.getLikedPosts().add(post);
        post.getUsersWhoLiked().add(user);

        return postId;
    }

    @Override
    @Transactional
    public Long removePostFromLiked(String userEmail, Long postId) {
        User user = userRepository.findByEmail(userEmail).orElse(null);
        Post post = postRepository.findById(postId).orElse(null);

        user.getLikedPosts().remove(post);
        post.getUsersWhoLiked().remove(user);

        return postId;
    }

    @Override
    public PostWithLikes getPostWithLikes(Long id) {
        return postRepository.findPostWithLikesById(id);
    }
}
