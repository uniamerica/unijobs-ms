package com.uniamerica.unijobsbackend.auth.model;

import com.uniamerica.unijobsbackend.models.Privilege;
import com.uniamerica.unijobsbackend.models.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserSecurity extends Usuario implements UserDetails {

    public UserSecurity(Usuario usuario) {
        super(usuario);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Privilege> privileges = new ArrayList<>();
        List<GrantedAuthority> authorities = new ArrayList<>();

        getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
            privileges.addAll(role.getPrivileges());
        });

        privileges.forEach(privilege ->
                authorities.add(new SimpleGrantedAuthority(privilege.getName())));

        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getSenha();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
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
