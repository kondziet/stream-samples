package pl.kondziet.streamsamples.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kondziet.streamsamples.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
