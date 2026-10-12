package ma.youcode.clinic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

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
import ma.youcode.clinic.exception.ForbiddenOperationException;

public class DemandeExpertiseService {
    private final DemandeExpertiseRepository demandeRepository = new DemandeExpertiseRepository();
    private final UserRepository userRepository = new UserRepository();
    private final ConsultationRepository consultationRepository = new ConsultationRepository();

    public DemandeExpertise creerDemande(DemandeRequestDTO dto, String username) {
        if (dto == null) {
            throw new IllegalArgumentException("La demande est obligatoire.");
        }

        if (dto.getConsultationId() == null) {
            throw new IllegalArgumentException(
                    "L'identifiant de la consultation est obligatoire.");
        }

        if (dto.getSpecialisteId() == null) {
            throw new IllegalArgumentException(
                    "L'identifiant du specialiste est obligatoire.");
        }

        if (dto.getQuestion() == null || dto.getQuestion().isBlank()) {
            throw new IllegalArgumentException(
                    "La question ne peut pas etre vide.");
        }

        if (dto.getPriorite() == null) {
            throw new IllegalArgumentException(
                    "La priorite est obligatoire.");
        }
        User generaliste = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Utilisateur authentifie introuvable."));

        Consultation consultation = consultationRepository
                .findById(dto.getConsultationId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Consultation introuvable."));
        if (consultation.getDoctor() == null
                || !generaliste.getId()
                        .equals(consultation.getDoctor().getId())) {
            throw new ForbiddenOperationException(
                    "Vous n'etes pas autorise a acceder a cette consultation.");
        }
        User specialiste = userRepository.findById(dto.getSpecialisteId());

        if (specialiste == null
                || specialiste.getRole() != Role.SPECIALISTE) {
            throw new RuntimeException(
                    "NOT_FOUND: Specialiste introuvable.");
        }

        if (specialiste.getRole() != Role.SPECIALISTE) {
            throw new RuntimeException(
                    "NOT_FOUND: Specialiste introuvable.");
        }
        // return null;
        // Priorite prioriteEnum;
        // try {
        // prioriteEnum = dto.getPriorite();
        // } catch (Exception e) {
        // throw new IllegalArgumentException("Priorité invalide.");
        // }

        DemandeExpertise demande = new DemandeExpertise();
        demande.setConsultation(consultation);
        demande.setSpecialiste(specialiste);
        demande.setQuestion(dto.getQuestion());
        demande.setPriorite(dto.getPriorite());
        demande.setStatut(StatutDemande.EN_ATTENTE);
        demande.setDateCreation(LocalDateTime.now());
        demandeRepository.save(demande);
        return demande;
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
        // Consultation consultation =
        // consultationRepository.findById(dto.getConsultationId())
        // .orElseThrow(() -> new IllegalArgumentException("Consultation introuvable"));

    }

    public List<DemandeExpertise> consulterDemandes(String username,
            String statut) {
        User specialiste = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Utilisateur authentifie introuvable."));

        if (specialiste.getRole() != Role.SPECIALISTE) {
            throw new ForbiddenOperationException(
                    "Cette ressource est reservee aux specialistes.");
        }

        StatutDemande statutDemande = convertirStatut(statut);

        return demandeRepository.findBySpecialisteId(specialiste.getId(),
                statutDemande);
    }

    public List<DemandeExpertise> demandeParConsultationd(String username, Long idConsiltation) {
        User generaliste = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur authentifie introuvable."));
        Consultation consultation = consultationRepository.findById(idConsiltation)
                .orElseThrow(() -> new IllegalArgumentException("Consultation introuvable."));
        if (consultation.getDoctor() == null
                || !generaliste.getId()
                        .equals(consultation.getDoctor().getId())) {
            throw new ForbiddenOperationException(
                    "Vous n'etes pas autorise a acceder a cette consultation.");

        }
        return demandeRepository.findByConsultationId(idConsiltation);
    }

    private StatutDemande convertirStatut(String statut) {
        if (statut == null || statut.isBlank()) {
            return null;
        }

        try {
            return StatutDemande.valueOf(statut.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Statut invalide.");
        }

    }
}
