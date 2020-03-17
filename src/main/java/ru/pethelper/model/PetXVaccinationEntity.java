package ru.pethelper.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pet_x_vaccination", schema = "pet", catalog = "pethelper")
public class PetXVaccinationEntity {
    private int vacId;
    private Date vacDate;

    @Id
    @Column(name = "vac_id", nullable = false)
    public int getVacId() {
        return vacId;
    }

    public void setVacId(int vacId) {
        this.vacId = vacId;
    }

    @Basic
    @Column(name = "vac_date", nullable = false)
    public Date getVacDate() {
        return vacDate;
    }

    public void setVacDate(Date vacDate) {
        this.vacDate = vacDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetXVaccinationEntity that = (PetXVaccinationEntity) o;
        return vacId == that.vacId &&
                Objects.equals(vacDate, that.vacDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacId, vacDate);
    }

    @ManyToMany
    Set<PetEntity> petEntities;
}
