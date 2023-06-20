package pl.kondziet.streamsamples.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kondziet.streamsamples.model.DTO.AuthenticationRequest;
import pl.kondziet.streamsamples.model.DTO.AuthenticationResponse;
import pl.kondziet.streamsamples.model.DTO.RegisterRequest;
import pl.kondziet.streamsamples.service.AuthenticationService;

@AllArgsConstructor
@RestController()
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
