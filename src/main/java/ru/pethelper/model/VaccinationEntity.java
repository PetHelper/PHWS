package ru.pethelper.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vaccination", schema = "pet", catalog = "pethelper")
public class VaccinationEntity {
    private int vacId;
    private String vacNm;
    private Integer vacPeriodMonth;

    @Id
    @Column(name = "vac_id", nullable = false)
    public int getVacId() {
        return vacId;
    }

    public void setVacId(int vacId) {
        this.vacId = vacId;
    }

    @Basic
    @Column(name = "vac_nm", nullable = false, length = 30)
    public String getVacNm() {
        return vacNm;
    }

    public void setVacNm(String vacNm) {
        this.vacNm = vacNm;
    }

    @Basic
    @Column(name = "vac_period_month", nullable = true)
    public Integer getVacPeriodMonth() {
        return vacPeriodMonth;
    }

    public void setVacPeriodMonth(Integer vacPeriodMonth) {
        this.vacPeriodMonth = vacPeriodMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccinationEntity that = (VaccinationEntity) o;
        return vacId == that.vacId &&
                Objects.equals(vacNm, that.vacNm) &&
                Objects.equals(vacPeriodMonth, that.vacPeriodMonth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacId, vacNm, vacPeriodMonth);
    }
}
