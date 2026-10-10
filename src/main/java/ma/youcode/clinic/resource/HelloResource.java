package ma.youcode.clinic.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test")

public class HelloResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return """
                {
                  "message": "Jakarta REST fonctionne","errr" :"ghjkjh"
                }
                """;
    }
}