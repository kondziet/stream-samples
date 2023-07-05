package pl.kondziet.streamsamples.service;

import pl.kondziet.streamsamples.model.entity.User;

public interface UserService {

    public boolean doesUserExist(String email);
    User findByEmail(String email);
}
