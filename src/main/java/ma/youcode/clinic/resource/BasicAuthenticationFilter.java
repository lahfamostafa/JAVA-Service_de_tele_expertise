package ma.youcode.clinic.resource;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import ma.youcode.clinic.model.User;
import ma.youcode.clinic.repository.UserRepository;
import ma.youcode.clinic.security.AuthenticationException;
import ma.youcode.clinic.security.UserSecurityContext;
import ma.youcode.clinic.service.AuthentificationService;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class BasicAuthenticationFilter implements ContainerRequestFilter {
    private static final String BASIC_PREFIX = "Basic ";

    private final AuthentificationService authentificationService =
            new AuthentificationService(new UserRepository());

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authorization = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorization == null || !authorization.startsWith(BASIC_PREFIX)) {
            abortUnauthorized(requestContext);
            return;
        }

        String credentials = decodeCredentials(authorization.substring(BASIC_PREFIX.length()));
        int separatorIndex = credentials.indexOf(':');
        if (separatorIndex <= 0) {
            abortUnauthorized(requestContext);
            return;
        }

        try {
            User user = authentificationService.authenticate(
                    credentials.substring(0, separatorIndex),
                    credentials.substring(separatorIndex + 1));
            boolean secure = "https".equalsIgnoreCase(
                    requestContext.getUriInfo().getRequestUri().getScheme());
            requestContext.setSecurityContext(new UserSecurityContext(user, secure));
        } catch (AuthenticationException exception) {
            abortUnauthorized(requestContext);
        }
    }

    private String decodeCredentials(String encodedCredentials) {
        try {
            return new String(Base64.getDecoder().decode(encodedCredentials), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException exception) {
            return "";
        }
    }

    private void abortUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"tele-expertise\"")
                .type(MediaType.APPLICATION_JSON)
                .entity(Map.of("error", "Authentication required"))
                .build());
    }
}
