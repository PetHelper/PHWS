package ru.pethelper.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserJWTDTO implements UserDetails {

    private long userId;
    private String username;
    private String userEmail;
    private boolean active;
    private String password;

    public UserJWTDTO(long userId, String username, String userEmail, boolean active, String password) {
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
        this.active = active;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public boolean isActive() {
        return active;
    }
}
