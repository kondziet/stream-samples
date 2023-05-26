package pl.kondziet.streamsamples.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kondziet.streamsamples.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
