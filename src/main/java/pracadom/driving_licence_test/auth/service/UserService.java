package pracadom.driving_licence_test.auth.service;

import pracadom.driving_licence_test.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}