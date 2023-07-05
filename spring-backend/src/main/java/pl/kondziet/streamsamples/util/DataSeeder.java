package pl.kondziet.streamsamples.util;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kondziet.streamsamples.model.entity.Post;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.model.enums.Role;
import pl.kondziet.streamsamples.model.repository.PostRepository;
import pl.kondziet.streamsamples.model.repository.UserRepository;

@AllArgsConstructor
@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

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

        Post post1 = Post.builder()
                .code("System.out.println(\"Hello world\");")
                .author(user1)
                .title("printing")
                .build();

        Post post2 = Post.builder()
                .code("nums.filter(num -> num % 2 == 0).toList();")
                .author(user1)
                .title("filtering")
                .build();

        postRepository.save(post1);
        postRepository.save(post2);

        user1.getPosts().add(post1);
        user1.getPosts().add(post2);
    }
}
