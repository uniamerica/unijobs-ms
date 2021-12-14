package com.uniamerica.unijobsbackend.controllers;

import com.uniamerica.unijobsbackend.dto.UsuarioDto;
import com.uniamerica.unijobsbackend.dto.input.AtualizarUsuarioDTO;
import com.uniamerica.unijobsbackend.services.UsuarioService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@OpenAPIDefinition
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/users")
@CrossOrigin
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> index() {

        var users = usuarioService.index().stream().map(UsuarioDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> show(@PathVariable Integer id) {

        var user = usuarioService.show(id);
        return ResponseEntity.ok(new UsuarioDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> update(@PathVariable Integer id, @Valid @RequestBody AtualizarUsuarioDTO usuario) {
        var userUpdate = usuarioService.update(usuario.toModel(), id);
        return ResponseEntity.ok(new UsuarioDto(userUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Integer id) {
        usuarioService.destroy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
