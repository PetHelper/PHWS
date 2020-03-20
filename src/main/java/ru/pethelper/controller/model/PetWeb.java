package ru.pethelper.controller.model;

import java.sql.Date;

public class PetWeb {
    private String petName;
    private String colour;
    private Integer weight;
    private String breed;
    private Integer sex;
    private String distMarks;
    private String tagNumber;
    private String pedigreeNum;
    private String animalCardNum;
    private Date birthDate;

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
}
