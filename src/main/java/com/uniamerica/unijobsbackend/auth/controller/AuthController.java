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
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> login(@RequestBody LoginDto login) throws Exception {
        String token = service.login(login.getEmail(), login.getPassword());
        return ResponseEntity.ok(new ResponseTokenDto(token));
    }
    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto saveUser(@RequestBody RegisterDto user) throws Exception {
        Usuario usuario = service.register(user.toModel());
        return new UsuarioDto(usuario);
    }


}
