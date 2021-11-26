package com.uniamerica.unijobsbackend.controllers;

import com.uniamerica.unijobsbackend.dto.UsuarioDto;
import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import com.uniamerica.unijobsbackend.services.UsuarioService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@OpenAPIDefinition
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @PostMapping
    public ResponseEntity<Usuario> store(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.store(usuario));
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> index() {
        return ResponseEntity.ok(usuarioService.index());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> show(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.show(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {
        Optional<Usuario> optionalUsuario = usuarioService.show(id);

        if (optionalUsuario.isPresent()) {
            usuario.setId(id);
            return ResponseEntity.ok(usuarioService.update(usuario));
        } else {
            return null;
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Integer id) {
        usuarioService.destroy(id);
        return ResponseEntity.ok(null);
    }

}
