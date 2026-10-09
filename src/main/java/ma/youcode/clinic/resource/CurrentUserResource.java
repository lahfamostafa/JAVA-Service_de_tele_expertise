package ma.youcode.clinic.resource;

import java.util.Map;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

@Path("auth/me")
@Produces(MediaType.APPLICATION_JSON)
public class CurrentUserResource {
    @GET
    @RolesAllowed({"INFIRMIER", "GENERALISTE", "SPECIALISTE"})
    public Map<String, String> currentUser(@Context SecurityContext securityContext) {
        return Map.of("username", securityContext.getUserPrincipal().getName());
    }
}
