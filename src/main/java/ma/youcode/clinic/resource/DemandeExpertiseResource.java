package ma.youcode.clinic.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import ma.youcode.clinic.dto.DemandeRequestDTO;
import ma.youcode.clinic.dto.DemandeResponseDTO;
import ma.youcode.clinic.service.DemandeExpertiseService;
import ma.youcode.clinic.model.DemandeExpertise;
import ma.youcode.clinic.exception.ForbiddenOperationException;
import java.util.List;
import java.util.Map;

/*consulterDemandes() et demandeConsultation() */
@Path("demandes")
@Produces(MediaType.APPLICATION_JSON)
public class DemandeExpertiseResource {

    private final DemandeExpertiseService service = new DemandeExpertiseService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("GENERALISTE")
    public Response creerDemande(DemandeRequestDTO dto, @Context SecurityContext securityContext) {
        // return Response.status(Response.Status.NOT_IMPLEMENTED).build();

        if (dto == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "Données manquantes"))
                    .build();
        }
        // return Response.ok(dto).build();
        try {
            String username = securityContext
                    .getUserPrincipal()
                    .getName();
            // DemandeExpertise demande = service.creerDemande(dto , String username);
            DemandeExpertise demande = service.creerDemande(dto, username);

            return Response.status(Response.Status.CREATED)
                    .entity(toResponseDTO(demande))
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build(); // HTTP 400
        } catch (ForbiddenOperationException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(Map.of("error", e.getMessage()))
                    .build();
        } catch (RuntimeException e) {
            if (e.getMessage().startsWith("NOT_FOUND")) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"" + e.getMessage() + "\"}")
                        .build(); // HTTP 404
            }
            return Response.serverError().build();
        }
    }

    @GET
    @RolesAllowed({ "GENERALISTE", "SPECIALISTE" })
    public Response getDemandes(
            @QueryParam("statut") String statut,
            @QueryParam("consultationId") Long consultationId,
            @Context SecurityContext securityContext) {

        String username = securityContext.getUserPrincipal().getName();

        try {
            List<DemandeExpertise> demandes;

            if (consultationId != null) {

                if (!securityContext.isUserInRole("GENERALISTE")) {
                    throw new ForbiddenOperationException(
                            "Seul un généraliste peut consulter les demandes d'une consultation.");
                }

                if (statut != null && !statut.isBlank()) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity(Map.of("error",
                                    "Impossible d'utiliser consultationId et statut ensemble."))
                            .build();
                }

                demandes = service.demandeParConsultationd(
                        username, consultationId);

            } else {

                if (!securityContext.isUserInRole("SPECIALISTE")) {
                    throw new ForbiddenOperationException(
                            "Cette opération est réservée aux spécialistes.");
                }

                demandes = service.consulterDemandes(username, statut);
            }

            List<DemandeResponseDTO> response = demandes.stream()
                    .map(this::toResponseDTO)
                    .toList();

            return Response.ok(response).build();

        } catch (IllegalArgumentException exception) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", exception.getMessage()))
                    .build();

        } catch (ForbiddenOperationException exception) {

            return Response.status(Response.Status.FORBIDDEN)
                    .entity(Map.of("error", exception.getMessage()))
                    .build();
        }
    }

    private DemandeResponseDTO toResponseDTO(DemandeExpertise demande) {
        DemandeResponseDTO response = new DemandeResponseDTO();
        response.setId(demande.getId());
        response.setConsultationId(demande.getConsultation().getId());
        response.setSpecialisteId(demande.getSpecialiste().getId());
        response.setQuestion(demande.getQuestion());
        response.setPriorite(demande.getPriorite());
        response.setStatutDemande(demande.getStatut());
        return response;
    }
}
