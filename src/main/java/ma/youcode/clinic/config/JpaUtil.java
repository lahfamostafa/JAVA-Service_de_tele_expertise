package ma.youcode.clinic.config;

import jakarta.persistence.*;

public class JpaUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

    private JpaUtil() {
    };

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}

