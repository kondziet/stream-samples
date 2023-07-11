package pl.kondziet.streamsamples.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.kondziet.streamsamples.model.DTO.PostPOST;
import pl.kondziet.streamsamples.model.entity.Post;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.model.repository.PostRepository;
import pl.kondziet.streamsamples.model.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController()
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository;
    private PostRepository postRepository;

    @GetMapping()
    public ResponseEntity<List<User>> get() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> post(Authentication authentication, @RequestBody PostPOST newPost) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(authentication.getName()))
                .findAny()
                .orElse(null);

        Post post = Post.builder()
                .title(newPost.getTitle())
                .code(newPost.getCode())
                .author(user)
                .build();

        postRepository.save(post);

        assert user != null;
        user.getPosts().add(post);

        return ResponseEntity.ok("Created");
    }
}
