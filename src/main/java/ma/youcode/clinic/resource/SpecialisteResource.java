package ma.youcode.clinic.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.youcode.clinic.service.SpecialisteService;

@Path("/specialistes")
@Produces(MediaType.APPLICATION_JSON)
// @RolesAllowed("")
public class SpecialisteResource {
    private final SpecialisteService specialisteService = new SpecialisteService();
    @GET 
    public Response getSpecialistes(@QueryParam("specialite")String specialite){
        try {
            var result = specialisteService.getSpecialistesBySpecialite(specialite);
            return Response.ok(result).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }
}
