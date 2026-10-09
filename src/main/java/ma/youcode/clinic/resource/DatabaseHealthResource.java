
package ma.youcode.clinic.resource;

import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import ma.youcode.clinic.config.JpaUtil;

@Path("health/database")
@Produces(MediaType.APPLICATION_JSON)
public class DatabaseHealthResource {

    @GET
    public Response checkDatabaseConnection() {

        EntityManager entityManager = null;

        try {
            entityManager = JpaUtil.getEntityManager();

            Object result = entityManager
                    .createNativeQuery("SELECT 1")
                    .getSingleResult();

            return Response.ok(
                    Map.of(
                            "status", "UP",
                            "database", "CONNECTED"
                    )
            ).build();

        } catch (PersistenceException exception) {

            return Response.status(
                    Response.Status.SERVICE_UNAVAILABLE
            ).entity(
                    Map.of(
                            "status", "DOWN",
                            "database", "DISCONNECTED"
                    )
            ).build();

        } finally {
            if (entityManager != null
                    && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
