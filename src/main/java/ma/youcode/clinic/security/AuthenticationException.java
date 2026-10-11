package ma.youcode.clinic.security;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Authentication failed: Invalid username or password.");
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
