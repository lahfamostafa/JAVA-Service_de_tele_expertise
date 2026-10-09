package ma.youcode.clinic.service;

import org.mindrot.jbcrypt.BCrypt;

import ma.youcode.clinic.model.User;
import ma.youcode.clinic.repository.UserRepository;
import ma.youcode.clinic.security.AuthenticationException;

public class AuthentificationService {
    private final UserRepository userRepository;

    public AuthentificationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(String username, String password) {
        if (isBlank(username) || isBlank(password)) {
            throw new AuthenticationException();
        }

        User user = userRepository.findByUsername(username.trim())
                .orElseThrow(AuthenticationException::new);

        try {
            if (BCrypt.checkpw(password, user.getPasswordHash())) {
                return user;
            }
        } catch (IllegalArgumentException exception) {
            // A malformed stored hash must never authenticate a user.
        }

        throw new AuthenticationException();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
