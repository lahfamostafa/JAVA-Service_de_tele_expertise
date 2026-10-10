package ma.youcode.clinic.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.clinic.config.JpaUtil;
import ma.youcode.clinic.model.DemandeExpertise;

public class DemandeExpertiseRepository {
    public void save(DemandeExpertise demande){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(demande);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally{
            em.close();
        }
    }
    
}
