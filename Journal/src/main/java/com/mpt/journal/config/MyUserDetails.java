package com.mpt.journal.config;

import com.mpt.journal.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class MyUserDetails implements UserDetails {

    private UserModel user;

    public MyUserDetails(UserModel user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(
                        user.getRoleModel().getNameRole().split(",")
                )
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

//    @Override
//    public boolean isAccountNonExpired() {
//        return true; // Здесь можно добавить логику для проверки срока действия аккаунта
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true; // Логика для проверки блокировки аккаунта
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true; // Проверка срока действия учетных данных
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true; // Логика для проверки, активен ли аккаунт
//    }
}
