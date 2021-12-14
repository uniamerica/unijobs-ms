package com.uniamerica.unijobsbackend.auth.controller;

import com.uniamerica.unijobsbackend.auth.dto.LoginDto;
import com.uniamerica.unijobsbackend.auth.dto.RegisterDto;
import com.uniamerica.unijobsbackend.auth.services.AuthService;
import com.uniamerica.unijobsbackend.dto.UsuarioDto;
import com.uniamerica.unijobsbackend.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/register")
    public ResponseEntity<UsuarioDto> saveUser(@RequestBody RegisterDto user) throws Exception {
        Usuario usuario = service.register(user.toModel());
        return ResponseEntity.ok(new UsuarioDto(usuario));
    }




}
