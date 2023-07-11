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
import pl.kondziet.streamsamples.model.subselects.PostWithLikes;
import pl.kondziet.streamsamples.service.PostService;
import pl.kondziet.streamsamples.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostRepository postRepository;
    private PostService postService;
    private UserService userService;


    @GetMapping("/home")
    public ResponseEntity<List<PostGET>> getPostsWithLikes() {
        return ResponseEntity.ok(
                postRepository.findPostsWithLikes().stream()
                        .map(post -> PostGET.builder()
                                .postCategory(post.getPostCategory().name())
                                .postId(post.getPostId())
                                .title(post.getTitle())
                                .likesCount(post.getLikesCount())
                                .authorNickname(post.getUser().getNickname())
                                .description(post.getDescription())
                                .code(post.getCode())
                                .build()
                        ).collect(Collectors.toList())
        );
    }

    @PostMapping("/{postId}/likes")
    public ResponseEntity<Long> likePost(Authentication authentication, @PathVariable Long postId) {
        return ResponseEntity.ok(
                postService.addPostToLiked(authentication.getName(), postId)
        );
    }

}