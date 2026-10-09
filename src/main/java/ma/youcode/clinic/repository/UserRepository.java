package ma.youcode.clinic.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import ma.youcode.clinic.config.JPAUtil;
import ma.youcode.clinic.model.Role;
import ma.youcode.clinic.model.User;

public class UserRepository {
    public List<User> findSpecialistes(){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("Select u From User u Where u.role = :role",User.class).setParameter("role", Role.SPECIALISTE).getResultList();
            
        } finally {
            em.close();
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
