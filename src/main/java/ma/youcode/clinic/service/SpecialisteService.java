package ma.youcode.clinic.service;

import java.util.Comparator;
import java.util.List;

import ma.youcode.clinic.model.Specialite;
import ma.youcode.clinic.model.User;
import ma.youcode.clinic.repository.UserRepository;

public class SpecialisteService {

    private final UserRepository userRepository = new UserRepository();

    public List<User> getSpecialistesBySpecialite(String specialiteStr) {
        if (specialiteStr == null || specialiteStr.isBlank()) {
            throw new IllegalArgumentException(
                    "La specialite est obligatoire.");
        }

        Specialite specialite;

        try {
            specialite = Specialite.valueOf(
                    specialiteStr.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Specialite invalide: " + specialiteStr);
        }

        return userRepository.findSpecialistes().stream()
                .filter(user -> specialite.equals(user.getSpecialite()))
                .sorted(Comparator.comparing(
                        User::getTarif,
                        Comparator.nullsLast(
                                Comparator.naturalOrder())))
                .toList();
    }
}