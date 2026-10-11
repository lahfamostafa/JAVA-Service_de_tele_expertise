package ma.youcode.clinic.service;

import java.util.Optional;

import ma.youcode.clinic.dto.DemandeRequestDTO;
import ma.youcode.clinic.model.Consultation;
import ma.youcode.clinic.model.DemandeExpertise;
// import ma.youcode.clinic.model.Priorite;
import ma.youcode.clinic.model.Role;
import ma.youcode.clinic.model.StatutDemande;
import ma.youcode.clinic.model.User;
import ma.youcode.clinic.repository.DemandeExpertiseRepository;
import ma.youcode.clinic.repository.UserRepository;
import ma.youcode.clinic.repository.ConsultationRepository;

public class DemandeExpertiseService {
    private final DemandeExpertiseRepository demandeRepository = new DemandeExpertiseRepository();
    private final UserRepository userRepository = new UserRepository();
    private final ConsultationRepository consultationRepository = new ConsultationRepository();

    public DemandeExpertise creerDemande(DemandeRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("La demande est obligatoire.");
        }
        if (dto.getQuestion() == null || dto.getQuestion().trim().isEmpty()) {
            throw new IllegalArgumentException("La question ne peut pas etre vide.");
        }
        if (dto.getPriorite() == null) {
            throw new IllegalArgumentException("la priorite n peut pas etre vide");
        }
        // return null;
        // Priorite prioriteEnum;
        // try {
        // prioriteEnum = dto.getPriorite();
        // } catch (Exception e) {
        // throw new IllegalArgumentException("Priorité invalide.");
        // }

        Optional<User> specialiste = userRepository.findById(dto.getSpecialisteId());
        if (specialiste.isEmpty() || specialiste.get().getRole() != Role.SPECIALISTE) {
            throw new RuntimeException("NOT_FOUND: Spécialiste introuvable.");
        }
        /*
         * je dois d abord verifier si cette consultation est dejat lier au medecin
         * authetifier lui qui veut envoyer n
         * 
         * et j ai des erreur de compatibiliter (The method
         * setConsultation(Consultation) in the type DemandeExpertise is not applicable
         * for the arguments (Long))
         * The method setSpecialiste(User) in the type DemandeExpertise is not
         * applicable for the arguments (Long)
         */
        DemandeExpertise demande = new DemandeExpertise();
        Consultation consultation = consultationRepository.findById(dto.getConsultationId())
                .orElseThrow(() -> new IllegalArgumentException("Consultation introuvable"));
        demande.setConsultation(consultation);
        demande.setSpecialiste(specialiste.get());
        demande.setQuestion(dto.getQuestion());
        demande.setPriorite(dto.getPriorite());
        demande.setStatut(StatutDemande.EN_ATTENTE);
        demandeRepository.save(demande);
        return demande;
    }

}
