package pl.kondziet.streamsamples.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kondziet.streamsamples.model.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentByPost_Id(Long postId);
}
