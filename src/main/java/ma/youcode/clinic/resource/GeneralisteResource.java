package ma.youcode.clinic.resource;

import java.util.Map;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("auth/generaliste")
@Produces(MediaType.APPLICATION_JSON)
public class GeneralisteResource {
    @GET
    @RolesAllowed("GENERALISTE")
    public Map<String, String> generalisteOnly() {
        return Map.of("message", "Access granted to GENERALISTE");
    }
}
