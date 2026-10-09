package ma.youcode.clinic.security;

import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import jakarta.ws.rs.ext.Provider;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

@Provider
public class RolesAllowedFeature implements Feature {
    @Override
    public boolean configure(FeatureContext context) {
        context.register(RolesAllowedDynamicFeature.class);
        return true;
    }
}
