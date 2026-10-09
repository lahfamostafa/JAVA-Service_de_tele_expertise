package ma.youcode.clinic.model;


public class Specialiste {
    private Long id;
    private long userId;
    private Specialite Specialite;
    private Double tarif;

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

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }
}
