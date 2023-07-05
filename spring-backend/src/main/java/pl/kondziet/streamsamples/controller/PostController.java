package pl.kondziet.streamsamples.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.kondziet.streamsamples.model.DTO.PostGET;
import pl.kondziet.streamsamples.model.DTO.PostPOST;
import pl.kondziet.streamsamples.model.entity.Post;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.model.repository.PostRepository;
import pl.kondziet.streamsamples.service.PostService;
import pl.kondziet.streamsamples.service.UserService;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostRepository postRepository;
    private PostService postService;
    private UserService userService;

    @PostMapping
    public ResponseEntity<PostGET> addNewUserPost(Authentication authentication, @RequestBody PostPOST newPost) {

        User user = userService.findByEmail(authentication.getName());

        Post post = Post.builder()
                .author(user)
                .title(newPost.getTitle())
                .code(newPost.getCode())
                .build();

        postService.addNewPost(post);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PostGET.builder()
                        .authorNickname(user.getNickname())
                        .title(post.getTitle())
                        .code(post.getCode())
                        .build()
                );
    }

    @GetMapping
    public ResponseEntity<List<PostGET>> getAllPosts() {
        return ResponseEntity.ok(
                postRepository.findAll().stream()
                        .map(post -> PostGET.builder()
                                .authorNickname(post.getAuthor().getNickname())
                                .title(post.getTitle())
                                .code(post.getCode())
                                .build()).toList()
        );
    }
}