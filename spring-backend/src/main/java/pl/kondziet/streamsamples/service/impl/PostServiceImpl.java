package pl.kondziet.streamsamples.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kondziet.streamsamples.model.entity.Post;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.model.repository.PostRepository;
import pl.kondziet.streamsamples.model.repository.UserRepository;
import pl.kondziet.streamsamples.model.subselect.PostWithLikes;
import pl.kondziet.streamsamples.service.PostService;

import java.util.List;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    @Override
    public Post findPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

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
    public List<PostWithLikes> getPostsWithLikes() {
        return postRepository.findPostsWithLikes();
    }

    @Override
    public PostWithLikes getPostWithLikes(Long id) {
        return postRepository.findPostWithLikesById(id);
    }
}
