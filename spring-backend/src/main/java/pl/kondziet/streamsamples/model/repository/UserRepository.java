package pl.kondziet.streamsamples.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kondziet.streamsamples.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
