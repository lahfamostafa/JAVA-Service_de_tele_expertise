package ma.youcode.clinic.repository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import ma.youcode.clinic.config.JPAUtil;
import ma.youcode.clinic.model.Role;
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

    public List<User> findSpecialistes(){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery("Select u from User u where u.role = :role", User.class)
            .setParameter("role",Role.SPECIALISTE)
            .getResultList();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public User findById(Long id){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }
}
