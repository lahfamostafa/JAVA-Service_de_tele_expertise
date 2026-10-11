package ma.youcode.clinic.dto;

import ma.youcode.clinic.model.Priorite;
import ma.youcode.clinic.model.StatutDemande;

public class DemandeResponseDTO {
    private Long id;
    private Long consultationId;
    private Long specialisteId;
    private String question;
    private Priorite priorite;
    private StatutDemande statutDemande;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatutDemande getStatutDemande() {
        return statutDemande;
    }

    public void setStatutDemande(StatutDemande statutDemande) {
        this.statutDemande = statutDemande;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public Long getSpecialisteId() {
        return specialisteId;
    }

    public void setSpecialisteId(Long specialisteId) {
        this.specialisteId = specialisteId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }
}