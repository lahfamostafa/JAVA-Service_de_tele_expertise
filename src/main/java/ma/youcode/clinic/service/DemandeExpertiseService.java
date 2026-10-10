package ma.youcode.clinic.service;

import ma.youcode.clinic.dto.DemandeRequestDTO;
import ma.youcode.clinic.model.Consultation;
import ma.youcode.clinic.model.DemandeExpertise;
import ma.youcode.clinic.model.Priorite;
import ma.youcode.clinic.model.Role;
import ma.youcode.clinic.model.StatutDemande;
import ma.youcode.clinic.model.User;
import ma.youcode.clinic.repository.DemandeExpertiseRepository;
import ma.youcode.clinic.repository.UserRepository;

public class DemandeExpertiseService {
    private final DemandeExpertiseRepository demandeRepository = new DemandeExpertiseRepository();
    private final UserRepository userRepository = new UserRepository();

    public DemandeExpertise creerDemande(DemandeRequestDTO dto){
        if (dto.getQuestion() == null || dto.getQuestion().trim().isEmpty()) {
            throw new IllegalArgumentException("La question ne peut pas être vide.");
        }

        Priorite prioriteEnum;
        try {
            prioriteEnum = Priorite.valueOf(dto.getPriorite().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Priorité invalide.");
        }

        User specialiste = userRepository.findById(dto.getSpecialisteId());
        if (specialiste == null || specialiste.getRole() != Role.SPECIALISTE) {
            throw new RuntimeException("NOT_FOUND: Spécialiste introuvable.");
        }

        DemandeExpertise demande = new DemandeExpertise();
        Consultation consultation = new Consultation();

        consultation.setId(dto.getConsultationId());
        demande.setConsultation(consultation);
        demande.setSpecialiste(specialiste);
        demande.setQuestion(dto.getQuestion());
        demande.setPriorite(prioriteEnum);
        demande.setStatut(StatutDemande.EN_ATTENTE);
        demandeRepository.save(demande);
        return demande;
    }
}
