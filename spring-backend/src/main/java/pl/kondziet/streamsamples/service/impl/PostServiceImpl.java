package pl.kondziet.streamsamples.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.kondziet.streamsamples.model.entity.Post;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.model.repository.PostRepository;
import pl.kondziet.streamsamples.model.repository.UserRepository;
import pl.kondziet.streamsamples.service.PostService;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    @Override
    public void addNewPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Long addPostToLiked(String userEmail, Long postId) {
        User user = userRepository.findByEmail(userEmail).orElse(null);
        Post post = postRepository.findById(postId).orElse(null);

        user.getLikedPosts().add(post);
        post.getUsersWhoLiked().add(user);

        userRepository.save(user);
        postRepository.save(post);

        return postId;
    }
}
