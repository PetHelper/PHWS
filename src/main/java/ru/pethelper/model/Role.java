package ru.pethelper.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, VETCLINIC;

    @Override
    public String getAuthority() {
        return name();
    }
}
