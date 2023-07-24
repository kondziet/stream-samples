package pl.kondziet.streamsamples.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.kondziet.streamsamples.model.DTO.CommentGET;
import pl.kondziet.streamsamples.model.DTO.CommentPOST;
import pl.kondziet.streamsamples.model.entity.Comment;
import pl.kondziet.streamsamples.service.CommentService;
import pl.kondziet.streamsamples.service.PostService;
import pl.kondziet.streamsamples.service.UserService;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;
    private PostService postService;
    private UserService userService;

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentGET>> getPostComments(@PathVariable Long postId) {
        List<CommentGET> postComments = commentService.getPostComments(postId).stream()
                .map(comment -> CommentGET.builder()
                        .id(comment.getId())
                        .authorNickname(comment.getAuthor().getNickname())
                        .content(comment.getContent())
                        .build()
                ).toList();

        return ResponseEntity.ok(
                postComments
        );
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentGET> addPostComment(Authentication authentication, @RequestBody CommentPOST commentPOST, @PathVariable Long postId) {
        Comment comment = Comment.builder()
                .post(postService.findPostById(postId))
                .author(userService.findByEmail(authentication.getName()))
                .content(commentPOST.getContent())
                .build();

        Comment addedComment = commentService.addCommentToPost(comment, postId);

        return ResponseEntity.ok(
                CommentGET.builder().build()
        );
    }
}