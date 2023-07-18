package pl.kondziet.streamsamples.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.kondziet.streamsamples.model.DTO.PostPOST;
import pl.kondziet.streamsamples.model.entity.Post;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.model.repository.PostRepository;
import pl.kondziet.streamsamples.model.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController()
@RequestMapping("/api/users")
public class UserController {


}
