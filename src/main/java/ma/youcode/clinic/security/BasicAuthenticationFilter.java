// package ma.youcode.clinic.security;

// import java.nio.charset.StandardCharsets;
// import java.util.Base64;
// import java.util.Map;

// import jakarta.annotation.Priority;
// import jakarta.ws.rs.Priorities;
// import jakarta.ws.rs.container.ContainerRequestContext;
// import jakarta.ws.rs.container.ContainerRequestFilter;
// import jakarta.ws.rs.core.HttpHeaders;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;
// import jakarta.ws.rs.ext.Provider;
// import ma.youcode.clinic.model.User;
// import ma.youcode.clinic.repository.UserRepository;
// import ma.youcode.clinic.service.AuthentificationService;

// @Provider
// @Secured
// @Priority(Priorities.AUTHENTICATION)
// public class BasicAuthenticationFilter implements ContainerRequestFilter {
//     private static final String BASIC_PREFIX = "Basic ";

//     private final AuthentificationService authentificationService = new AuthentificationService(new UserRepository());

//     @Override
//     public void filter(ContainerRequestContext requestContext) {
//         if(requestContext.getUriInfo().getPath().equals("test")){
            
//         }
//         String path = requestContext.getUriInfo().getPath();
//         if (path.equals("specialistes") && path.equals("health/database")) {
//             return;
//         }
//         String authorization = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//         if (authorization == null || !authorization.startsWith(BASIC_PREFIX)) {
//             abortUnauthorized(requestContext);
//             return;
//         }

//         String credentials = decodeCredentials(authorization.substring(BASIC_PREFIX.length()));
//         int separatorIndex = credentials.indexOf(':');
//         if (separatorIndex <= 0) {
//             abortUnauthorized(requestContext);
//             return;
//         }

//         String username = credentials.substring(0, separatorIndex);
//         String password = credentials.substring(separatorIndex + 1);

//         try {
//             User user = authentificationService.authenticate(username, password);
//             boolean secure = "https".equalsIgnoreCase(
//                     requestContext.getUriInfo().getRequestUri().getScheme());
//             requestContext.setSecurityContext(new UserSecurityContext(user, secure));
//         } catch (AuthenticationException exception) {
//             abortUnauthorized(requestContext);
//         }
//     }

//     private String decodeCredentials(String encodedCredentials) {
//         try {
//             byte[] decoded = Base64.getDecoder().decode(encodedCredentials);
//             return new String(decoded, StandardCharsets.UTF_8);
//         } catch (IllegalArgumentException exception) {
//             return "";
//         }
//     }

//     private void abortUnauthorized(ContainerRequestContext requestContext) {
//         Response response = Response.status(Response.Status.UNAUTHORIZED)
//                 .header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"tele-expertise\"")
//                 .type(MediaType.APPLICATION_JSON)
//                 .entity(Map.of("error", "Authentication required"))
//                 .build();
//         requestContext.abortWith(response);
//     }
// }
