package pl.kondziet.streamsamples.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.repository.UserRepository;

import java.util.List;

@RestController()
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public ResponseEntity<List<User>> get() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
}
