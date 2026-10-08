package ma.youcode.clinic.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @Column(length = 255)
    private String reason;
    @Column(columnDefinition = "TEXT")
    private String observations;
    @Column(columnDefinition = "TEXT")
    private String diagnosis;
    @Column(name = "prescribed_treatment", columnDefinition = "TEXT")
    private String prescribedTreatment;
    @Column
    private float cost;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConsultationStatus status;
    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    public Consultation() {
    }

    public Consultation(
            Long id,
            Patient patient,
            ConsultationStatus status) {
        this.id = id;
        this.patient = patient;
        this.status = status;
    }

    public Consultation(Long id, Patient patient, User doctor, String reason,
            String observations, String diagnosis, String prescribedTreatment,
            float cost, ConsultationStatus status, LocalDateTime closedAt) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.reason = reason;
        this.observations = observations;
        this.diagnosis = diagnosis;
        this.prescribedTreatment = prescribedTreatment;
        this.cost = cost;
        this.status = status;
        this.closedAt = closedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescribedTreatment() {
        return prescribedTreatment;
    }

    public void setPrescribedTreatment(String prescribedTreatment) {
        this.prescribedTreatment = prescribedTreatment;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public ConsultationStatus getStatus() {
        return status;
    }

    public void setStatus(ConsultationStatus status) {
        this.status = status;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDateTime closedAt) {
        this.closedAt = closedAt;
    }
}
