package com.uniamerica.unijobsbackend.auth.controller;

import com.uniamerica.unijobsbackend.auth.dto.LoginDto;
import com.uniamerica.unijobsbackend.auth.dto.RegisterDto;
import com.uniamerica.unijobsbackend.auth.dto.ResponseTokenDto;
import com.uniamerica.unijobsbackend.auth.services.AuthService;
import com.uniamerica.unijobsbackend.dto.UsuarioDto;
import com.uniamerica.unijobsbackend.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> login(@RequestBody LoginDto login) throws Exception {
        return ResponseEntity.ok(service.login(login.getEmail(), login.getPassword()));
    }

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<?> refreshTokenAccess(@RequestParam("refresh_token") String refreshToken) {
        return ResponseEntity.ok(service.updateTokenWithRefreshToken(refreshToken));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto saveUser(@RequestBody RegisterDto user) throws Exception {
        Usuario usuario = service.register(user.toModel());
        return new UsuarioDto(usuario);
    }


}
