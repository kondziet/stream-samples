package pl.kondziet.streamsamples.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kondziet.streamsamples.model.entity.Comment;
import pl.kondziet.streamsamples.model.entity.Post;
import pl.kondziet.streamsamples.model.repository.CommentRepository;
import pl.kondziet.streamsamples.model.repository.PostRepository;
import pl.kondziet.streamsamples.service.CommentService;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Override
    public Comment addNewComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getPostComments(Long postId) {
        return commentRepository.findCommentByPost_Id(postId);
    }

    @Transactional
    @Override
    public Comment addCommentToPost(Comment comment, Long postId) {
        addNewComment(comment);
        Post post = postRepository.findById(postId).orElse(null);
        post.getComments().add(comment);
        return comment;
    }
}
