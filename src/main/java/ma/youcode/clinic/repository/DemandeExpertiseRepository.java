package ma.youcode.clinic.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.clinic.config.JpaUtil;
import ma.youcode.clinic.model.Consultation;
import ma.youcode.clinic.model.DemandeExpertise;
import ma.youcode.clinic.model.StatutDemande;

public class DemandeExpertiseRepository {
    public void save(DemandeExpertise demande) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(demande);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive())
                tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<DemandeExpertise> findBySpecialisteId(Long specialisteId,
            StatutDemande statut) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT d FROM DemandeExpertise d "
                    + "WHERE d.specialiste.id = :specialisteId";

            if (statut != null) {
                jpql += " AND d.statut = :statut";
            }

            var query = em.createQuery(jpql, DemandeExpertise.class)
                    .setParameter("specialisteId", specialisteId);

            if (statut != null) {
                query.setParameter("statut", statut);
            }

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<DemandeExpertise> findByConsultationId(Long idConsiltation) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM DemandeExpertise c WHERE c.consultation_id = :idConsultation";
            return em.createQuery(jpql, DemandeExpertise.class)
                    .setParameter("idConsultation", idConsiltation)
                    .getResultList();
        } finally {
            em.close();
        }

    }
}
