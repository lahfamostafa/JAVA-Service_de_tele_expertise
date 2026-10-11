package ma.youcode.clinic.repository;

import ma.youcode.clinic.model.Consultation;
import ma.youcode.clinic.config.JPAUtil;

import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class ConsultationRepository {
    public Optional<Consultation> findById(Long id) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            Consultation consultation = entityManager.find(Consultation.class, id);
            return Optional.ofNullable(consultation);

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
