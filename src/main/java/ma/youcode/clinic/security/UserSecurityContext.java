package ma.youcode.clinic.security;

import java.security.Principal;

import jakarta.ws.rs.core.SecurityContext;
import ma.youcode.clinic.model.User;

public class UserSecurityContext implements SecurityContext {
    private final User user;
    private final boolean secure;

    public UserSecurityContext(User user, boolean secure) {
        this.user = user;
        this.secure = secure;
    }

    @Override
    public Principal getUserPrincipal() {
        return user::getUsername;
    }

    @Override
    public boolean isUserInRole(String role) {
        return user.getRole() != null && user.getRole().name().equals(role);
    }

    @Override
    public boolean isSecure() {
        return secure;
    }

    @Override
    public String getAuthenticationScheme() {
        return BASIC_AUTH;
    }
}
