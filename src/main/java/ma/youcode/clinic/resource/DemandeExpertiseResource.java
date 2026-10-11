package ma.youcode.clinic.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import ma.youcode.clinic.dto.DemandeRequestDTO;
import ma.youcode.clinic.service.DemandeExpertiseService;
import java.util.Map;

@Path("demandes")
@Produces(MediaType.APPLICATION_JSON)
public class DemandeExpertiseResource {

    private final DemandeExpertiseService service = new DemandeExpertiseService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    // @RolesAllowed("GENERALISTE")
    public Response creerDemande(DemandeRequestDTO dto, @Context SecurityContext securityContext) {
        // return Response.status(Response.Status.NOT_IMPLEMENTED).build();

        if (dto == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "Données manquantes"))
                    .build();
        }

        // return Response.ok(dto).build();
        try {
        var demande = service.creerDemande(dto);
        return Response.status(Response.Status.CREATED).entity(demande).build(); //
        // HTTP 201 Created
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