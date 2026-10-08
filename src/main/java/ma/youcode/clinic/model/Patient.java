package ma.youcode.clinic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "social_security_number", nullable = false, unique = true)
    private String socialSecurityNumber;
    @Column(name = "blood_pressure", nullable = false)
    private String bloodPressure;
    @Column(name = "heart_rate", nullable = false)
    private Integer heartRate;
    @Column(nullable = false)
    private float temperature;
    @Column(name = "respiratory_rate", nullable = false)
    private Integer respiratoryRate;
    @Column(name = "arrived_at", nullable = false)
    private LocalDateTime arrivedAt;

    public Patient() {
    }

    public Patient(Long id, String lastName, String firstName, LocalDate birthDate,
            String socialSecurityNumber, String bloodPressure, Integer heartRate,
            float temperature, Integer respiratoryRate, LocalDateTime arrivedAt) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.socialSecurityNumber = socialSecurityNumber;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.respiratoryRate = respiratoryRate;
        this.arrivedAt = arrivedAt;
    }
    // public Patient(String bloodPressure,Integer heartRate,float
    // temperature,Integer respiratoryRate,LocalDateTime arrivedAt){
    // this.bloodPressure = bloodPressure;
    // this.heartRate = heartRate;
    // this.temperature = temperature;
    // this.respiratoryRate = respiratoryRate;
    // this.arrivedAt = arrivedAt;
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Integer getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(Integer respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public LocalDateTime getArrivedAt() {
        return arrivedAt;
    }

    public void setArrivedAt(LocalDateTime arrivedAt) {
        this.arrivedAt = arrivedAt;
    }
}
