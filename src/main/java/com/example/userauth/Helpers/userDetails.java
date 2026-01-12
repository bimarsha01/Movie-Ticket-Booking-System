package com.example.userauth.Helpers;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface userDetails {
    Collection<? extends GrantedAuthority> getAuthorities(); // roles/permissions
    String getPassword();        // password hash
    String getUsername();        // unique identifier (usually email/username)
    boolean isAccountNonExpired();
    boolean isAccountNonLocked();
    boolean isCredentialsNonExpired();
    boolean isEnabled();
}
