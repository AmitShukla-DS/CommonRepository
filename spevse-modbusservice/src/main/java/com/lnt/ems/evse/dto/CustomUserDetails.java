package com.lnt.ems.evse.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lnt.ems.evse.entity.UserPassword;
import com.lnt.ems.evse.entity.Users;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private Users user;
    private UserPassword userPassword;

    public CustomUserDetails(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(simpleGrantedAuthority);
        return list;
    }

    @Override
    public String getPassword() {
        return userPassword.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLoginId();
    }

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
        return true;
    }
}
