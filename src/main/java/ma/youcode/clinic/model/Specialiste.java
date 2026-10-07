package ma.youcode.clinic.model;

import java.math.BigDecimal;

public class Specialiste {
    private Long id;
    private long userId;
    private Specialite Specialite;
    private BigDecimal tarif;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Specialite getSpecialite() {
        return Specialite;
    }

    public void setSpecialite(Specialite Specialite) {
        this.Specialite = Specialite;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }
}
