package pl.kondziet.streamsamples.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kondziet.streamsamples.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
