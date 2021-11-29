package com.uniamerica.unijobsbackend.controllers;


import com.uniamerica.unijobsbackend.models.Perfil;
import com.uniamerica.unijobsbackend.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/profiles")
public class PerfilController {

    private PerfilService perfilService;

    @Autowired

    public PerfilController(PerfilService perfilService){
        this.perfilService = perfilService;
    }

    @PostMapping
    public ResponseEntity<Perfil> store(@Valid @RequestBody Perfil perfil) {
        return ResponseEntity.ok(perfilService.store(perfil));
    }
    @GetMapping
    public ResponseEntity<List<Perfil>> index() {
        return ResponseEntity.ok(perfilService.index());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Perfil>> show(@PathVariable Integer id) {
        return ResponseEntity.ok(perfilService.show(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Perfil> update(@PathVariable Integer id, @Valid @RequestBody Perfil perfil) {
        Optional<Perfil> optionalPerfil = perfilService.show(id);

        if (optionalPerfil.isPresent()) {
            perfil.setId(id);
            return ResponseEntity.ok(perfilService.update(perfil));
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Perfil> destroy(@PathVariable Integer id) {
        perfilService.destroy(id);
        return ResponseEntity.ok(null);
    }
}
