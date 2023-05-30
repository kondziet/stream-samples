package pl.kondziet.streamsamples.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kondziet.streamsamples.model.DTO.AuthenticationRequest;
import pl.kondziet.streamsamples.model.DTO.AuthenticationResponse;
import pl.kondziet.streamsamples.model.DTO.SignupRequest;
import pl.kondziet.streamsamples.service.AuthenticationService;

@AllArgsConstructor
@RestController()
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(authenticationService.register(signupRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
