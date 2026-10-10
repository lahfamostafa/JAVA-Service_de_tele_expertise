// package ma.youcode.clinic.resource;

// import jakarta.persistence.EntityManager;
// import jakarta.persistence.PersistenceException;
// import jakarta.servlet.http.HttpSession;
// import jakarta.ws.rs.FormParam;
// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.POST;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;
// import ma.youcode.clinic.model.User;
// import org.mindrot.jbcrypt.BCrypt;

// @Path("/auth")
// @Produces(MediaType.APPLICATION_JSON)
// public class AuthentifactionRessources {
//     @Path("/login")
//     @POST 
//     public Response login(@FormParam("name") String name, @FormParam("password") String password) {
//         // EntityManager entityManager = null;
        
//         try {
//             User user = userService.authenticate(
//                 name, password
//             );
//             HttpSession session = request.getSession();
//             request.changeSessionId();
//             session.setAttribute(SessionAttributes.AUTHENTICATED_USER, user);
//             redirectToDashboard(request, response, user);
//         } catch (AuthenticationException exception) {
//             showLoginForm(request, response, exception.getMessage());
//         }
//     }

    
// }
