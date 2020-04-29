package ru.pethelper.controller.model;

import ru.pethelper.servlet.validation.ValidPassword;
import ru.pethelper.servlet.validation.ValidPhoneNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserWeb {
    @NotBlank(message = "Username cannot be empty")
    @NotNull(message = "Username could not be NULL")
    private String username;
    @NotBlank(message = "userSurname cannot be empty")
    @NotNull(message = "userSurname could not be NULL")
    private String userSurname;
    private String userAddress;
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not correct")
    @NotNull(message = "Email could not be NULL")
    private String userEmail;
    private Date userBirthDate;
    @NotBlank(message = "Phone cannot be empty")
    @NotNull(message = "Phone could not be NULL")
    @ValidPhoneNumber(message = "Phone is not valid")
    private String userPhone;
    @NotBlank(message = "Password could not be empty")
    @NotNull(message = "Password could not be NULL")
    @ValidPassword
    private String password;
    @NotBlank(message = "Matching Password could not be empty")
    @NotNull(message = "Matching Password could not be NULL")
    private String matchingPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(Date userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
