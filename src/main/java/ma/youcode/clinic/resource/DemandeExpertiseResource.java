package ma.youcode.clinic.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.youcode.clinic.dto.DemandeRequestDTO;
import ma.youcode.clinic.service.DemandeExpertiseService;

@Path("/demandes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DemandeExpertiseResource {

    private final DemandeExpertiseService service = new DemandeExpertiseService();

    @POST
    public Response creerDemande(DemandeRequestDTO dto) {
        try {
            var demande = service.creerDemande(dto);
            return Response.status(Response.Status.CREATED).entity(demande).build(); // HTTP 201 Created
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"error\": \"" + e.getMessage() + "\"}")
                           .build(); // HTTP 400
        } catch (RuntimeException e) {
            if (e.getMessage().startsWith("NOT_FOUND")) {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{\"error\": \"" + e.getMessage() + "\"}")
                               .build(); // HTTP 404
            }
            return Response.serverError().build();
        }
    }
}