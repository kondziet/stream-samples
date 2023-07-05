package pl.kondziet.streamsamples.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kondziet.streamsamples.model.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
