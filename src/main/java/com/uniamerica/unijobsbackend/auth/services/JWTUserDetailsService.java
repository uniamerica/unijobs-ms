package com.uniamerica.unijobsbackend.auth.services;

import com.uniamerica.unijobsbackend.auth.dto.JwtRequest;
import com.uniamerica.unijobsbackend.auth.model.UserSecurity;
import com.uniamerica.unijobsbackend.model.Usuario;
import com.uniamerica.unijobsbackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = usuarioRepository
                .findByEmail(email)
                .orElseThrow( () -> new UsernameNotFoundException(email) );

        return new UserSecurity(user);

    }

    public Usuario register(Usuario usuario) throws Exception {
        Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());
        if(user.isPresent()){
            throw new Exception("Ja existe usuario cadastrado com este e-mail!");
        } else {
            usuario.setSenha(bcryptEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);

        }

    }

}
