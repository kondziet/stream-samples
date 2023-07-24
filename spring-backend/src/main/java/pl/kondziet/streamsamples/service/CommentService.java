package pl.kondziet.streamsamples.service;

import java.util.List;
import pl.kondziet.streamsamples.model.entity.Comment;

public interface CommentService {

    Comment addNewComment(Comment comment);
    List<Comment> getPostComments(Long postId);
    Comment addCommentToPost(Comment comment, Long postId);
}
