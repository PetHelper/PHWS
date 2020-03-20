package ru.pethelper.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pet", schema = "pet", catalog = "pethelper")
public class PetEntity {
    @Id
    @Column(name = "pet_id", nullable = false)
    private int petId;
    @Basic
    @Column(name = "pet_name", nullable = false, length = 50)
    private String petName;
    @Basic
    @Column(name = "colour", nullable = true, length = 50)
    private String colour;
    @Basic
    @Column(name = "weight", nullable = true)
    private Integer weight;
    @Basic
    @Column(name = "breed", nullable = true, length = 50)
    private String breed;
    @Basic
    @Column(name = "sex", nullable = true)
    private Integer sex;
    @Basic
    @Column(name = "dist_marks", nullable = true, length = 100)
    private String distMarks;
    @Basic
    @Column(name = "tag_number", nullable = true, length = 20)
    private String tagNumber;
    @Basic
    @Column(name = "pedigree_num", nullable = true, length = 20)
    private String pedigreeNum;
    @Basic
    @Column(name = "animal_card_num", nullable = true, length = 20)
    private String animalCardNum;
    @Basic
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDistMarks() {
        return distMarks;
    }

    public void setDistMarks(String distMarks) {
        this.distMarks = distMarks;
    }

    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    public String getPedigreeNum() {
        return pedigreeNum;
    }

    public void setPedigreeNum(String pedigreeNum) {
        this.pedigreeNum = pedigreeNum;
    }

    public String getAnimalCardNum() {
        return animalCardNum;
    }

    public void setAnimalCardNum(String animalCardNum) {
        this.animalCardNum = animalCardNum;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetEntity petEntity = (PetEntity) o;
        return petId == petEntity.petId &&
                Objects.equals(petName, petEntity.petName) &&
                Objects.equals(colour, petEntity.colour) &&
                Objects.equals(weight, petEntity.weight) &&
                Objects.equals(breed, petEntity.breed) &&
                Objects.equals(sex, petEntity.sex) &&
                Objects.equals(distMarks, petEntity.distMarks) &&
                Objects.equals(tagNumber, petEntity.tagNumber) &&
                Objects.equals(pedigreeNum, petEntity.pedigreeNum) &&
                Objects.equals(animalCardNum, petEntity.animalCardNum) &&
                Objects.equals(birthDate, petEntity.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId, petName, colour, weight, breed, sex, distMarks, tagNumber, pedigreeNum, animalCardNum, birthDate);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToMany
    Set<PetXVaccinationEntity> petXVaccinationEntities;
}
