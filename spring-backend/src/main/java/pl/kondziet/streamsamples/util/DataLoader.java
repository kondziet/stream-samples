package pl.kondziet.streamsamples.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.model.enums.Role;
import pl.kondziet.streamsamples.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = User.builder()
                .nickname("michaelScott")
                .email("example")
                .password(passwordEncoder.encode("example"))
                .role(Role.USER)
                .active(true)
                .build();

        userRepository.save(user1);
    }
}
