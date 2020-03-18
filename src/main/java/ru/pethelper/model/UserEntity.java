package ru.pethelper.model;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.pethelper.validation.ValidPassword;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user", schema = "pet", catalog = "pethelper")
public class UserEntity implements UserDetails {
    @Basic
    @Column(name = "user_id", nullable = false)
    @NaturalId
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;
    @Basic
    @Column(name = "user_name", nullable = false, length = 50)
    @NotBlank(message = "Username cannot be empty")
    @NotNull(message = "Username could not be NULL")
    private String username;
    @Basic
    @Column(name = "user_surname", nullable = false, length = 50)
    private String userSurname;
    @Basic
    @Column(name = "user_address", length = 50)
    private String userAddress;
    @Basic
    @Column(name = "user_email", nullable = false, length = 30)
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not correct")
    @NotNull(message = "Email could not be NULL")
    private String userEmail;
    @Basic
    @Column(name = "user_reg_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date userRegDate;
    @Basic
    @Column(name = "user_birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date userBirthDate;
    @Id
    @Column(name = "user_phone", nullable = false)
    @NotNull(message = "Phone could not be empty")
    private long userPhone;
    @Basic
    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password could not be empty")
    @NotNull(message = "Password could not be NULL")
    @ValidPassword
    private String password;
    @Transient
    private String matchingPassword;
    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;
    @Basic
    @Column(name = "activation_code")
    private String activationCode;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userId == that.userId &&
                userPhone == that.userPhone &&
                Objects.equals(username, that.username) &&
                Objects.equals(userSurname, that.userSurname) &&
                Objects.equals(userAddress, that.userAddress) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(userRegDate, that.userRegDate) &&
                Objects.equals(userBirthDate, that.userBirthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, userSurname, userAddress, userEmail, userRegDate, userBirthDate, userPhone);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserRegDate() {
        return userRegDate;
    }

    public void setUserRegDate(Date userRegDate) {
        this.userRegDate = userRegDate;
    }

    public Date getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(Date userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.PERSIST)
    private Set<PetEntity> petEntities;
}
