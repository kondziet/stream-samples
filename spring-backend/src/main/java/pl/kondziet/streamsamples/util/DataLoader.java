package pl.kondziet.streamsamples.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = User.builder()
                .nickname("michaelScott")
                .active(false)
                .build();

        User user2 = User.builder()
                .nickname("dwightSchrute")
                .active(false)
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
    }
}
