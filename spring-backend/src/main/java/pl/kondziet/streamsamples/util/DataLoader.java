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
        User user1 = new User();
        user1.setUsername("michaelScott");
        user1.setActive(false);

        User user2 = new User();
        user2.setUsername("dwightSchrute");
        user2.setActive(false);

        userRepository.save(user1);
        userRepository.save(user2);
    }
}
