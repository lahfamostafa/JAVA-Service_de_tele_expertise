package ma.youcode.clinic.repository;

import java.lang.StackWalker.Option;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import ma.youcode.clinic.config.JpaUtil;
import ma.youcode.clinic.model.User;

public class UserRepository {

    public Optional<User> findByUsername(String username) {
        EntityManager entityManager = null;
        try {
            entityManager = JpaUtil.getEntityManager();
            User user = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            // User userFinded = Optional.of(user);
            return Optional.of(user);

        } catch (NoResultException e) {

            return Optional.empty();

        } catch (PersistenceException e) {
            // Handle exception or log it
            throw e;
        } finally {

            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

    }
}
