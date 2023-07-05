package pl.kondziet.streamsamples.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import pl.kondziet.streamsamples.model.DTO.AuthenticationRequest;
import pl.kondziet.streamsamples.model.DTO.AuthenticationResponse;
import pl.kondziet.streamsamples.model.DTO.RegisterRequest;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.model.enums.Role;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest registerRequest);
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
