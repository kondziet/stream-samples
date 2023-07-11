package pl.kondziet.streamsamples.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kondziet.streamsamples.model.entity.UserPostLikes;

public interface UserPostLikesRepository extends JpaRepository<UserPostLikes, Long> {

}
