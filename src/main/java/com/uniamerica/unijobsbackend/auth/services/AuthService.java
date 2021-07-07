package com.uniamerica.unijobsbackend.auth.services;

import com.uniamerica.unijobsbackend.Excessoes.RegraNegocioExcessao;
import com.uniamerica.unijobsbackend.auth.config.JwtTokenUtil;
import com.uniamerica.unijobsbackend.auth.model.UserSecurity;
import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder bcryptEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    public Usuario register(Usuario usuario) throws Exception {
        Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());
        if (user.isPresent()) {
            throw new RegraNegocioExcessao("Ja existe usuario cadastrado com este e-mail!");
        } else {
            usuario.setSenha(bcryptEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);

        }

    }

    public String login(String email, String password) throws Exception {
        authenticate(email, password);

        final UserSecurity userDetails = (UserSecurity) userDetailsService.loadUserByUsername(email);

        return jwtTokenUtil.generateToken(userDetails);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
