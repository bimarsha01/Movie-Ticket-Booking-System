package com.example.userauth.Helpers;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface userDetails {
    Collection<? extends GrantedAuthority> getAuthorities();
    String getPassword();
    String getUsername();
    boolean isAccountNonExpired();
    boolean isAccountNonLocked();
    boolean isCredentialsNonExpired();
    boolean isEnabled();
}
