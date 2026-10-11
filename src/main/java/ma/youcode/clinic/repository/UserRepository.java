package ma.youcode.clinic.repository;

import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import ma.youcode.clinic.config.JPAUtil;
import ma.youcode.clinic.model.User;

public class UserRepository {

    public Optional<User> findByUsername(String username) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            User user = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return Optional.of(user);

        } catch (NoResultException e) {

            return Optional.empty();

        } finally {

            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

    }

    public Optional<User> findById(Long id) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            User user = entityManager.find(User.class, id);
            return Optional.ofNullable(user);

        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return Optional.empty();
    }
}
