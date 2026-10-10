package ma.youcode.clinic.service;

import java.util.List;
import java.util.stream.Collectors;

import ma.youcode.clinic.model.Specialite;
import ma.youcode.clinic.model.User;
import ma.youcode.clinic.repository.UserRepository;

public class SpecialisteService {
    private final UserRepository userRepository = new UserRepository();

    public List<User> getSpecialistesBySpecialite(String specialiteStr){
        if (specialiteStr == null || specialiteStr.trim().isEmpty()) {
            throw new IllegalArgumentException("La spécialité est obligatoire.");
        }
        Specialite specialiteEnum;
        try {
            specialiteEnum = Specialite.valueOf(specialiteStr.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Spécialité invalide: " + specialiteStr);
        }

        return userRepository.findSpecialistes().stream().filter(u -> specialiteEnum.equals(u.getSpecialite()))
        .sorted((s1,s2)-> s1.getTarif().compareTo(s2.getTarif()))
        .collect(Collectors.toList());

    }
}
