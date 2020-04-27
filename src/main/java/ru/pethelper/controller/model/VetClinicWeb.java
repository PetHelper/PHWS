package ru.pethelper.controller.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VetClinicWeb {
    private long id;
    @NotBlank(message = "Address cannot be empty")
    @NotNull(message = "Address could not be NULL")
    private String address;
    @NotBlank(message = "District cannot be empty")
    @NotNull(message = "District could not be NULL")
    private String district;
    @NotBlank(message = "Name cannot be empty")
    @NotNull(message = "Name could not be NULL")
    private String name;
    private String site;
    @NotBlank(message = "Email cannot be empty")
    @NotNull(message = "Email could not be NULL")
    private String email;
    @NotBlank(message = "PhoneNumbers cannot be empty")
    @NotNull(message = "PhoneNumbers could not be NULL")
    private String phoneNumbers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
