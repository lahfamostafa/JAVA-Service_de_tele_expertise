package ma.youcode.clinic.dto;

public class DemandeRequestDTO {
    private Long consultationId;
    private Long specialisteId;
    private String question;
    private String priorite;

    public Long getConsultationId() {return consultationId;}
    public void setConsultationId(Long consultationId) {this.consultationId = consultationId;}
    public Long getSpecialisteId() {return specialisteId;}
    public void setSpecialisteId(Long specialisteId) {this.specialisteId = specialisteId;}
    public String getQuestion() {return question;}
    public void setQuestion(String question) {this.question = question;}
    public String getPriorite() {return priorite;}
    public void setPriorite(String priorite) {this.priorite = priorite;}
}