package pl.kondziet.streamsamples.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @GetMapping("/signup")
    public String signup() {
        return "allowed";
    }
}
