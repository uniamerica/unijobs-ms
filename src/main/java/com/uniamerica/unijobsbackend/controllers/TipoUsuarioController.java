package com.uniamerica.unijobsbackend.controllers;

import com.uniamerica.unijobsbackend.models.TipoUsuario;
import com.uniamerica.unijobsbackend.repositories.TipoUsuarioRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@OpenAPIDefinition
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/tipo_usuarios")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping
    public ResponseEntity<List<TipoUsuario>> BuscarTodos(){
        return ResponseEntity.ok(tipoUsuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> buscarTipoUsuario(@PathVariable Integer id){
        Optional<TipoUsuario> user = tipoUsuarioRepository.findById(id);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }


    @PostMapping
    public ResponseEntity<TipoUsuario> criarTipoUsuario(@RequestBody TipoUsuario t_user){
        TipoUsuario saved = tipoUsuarioRepository.save(t_user);
        return new ResponseEntity<TipoUsuario>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TipoUsuario> removerTipoUsuario(@PathVariable Integer id){
        Optional<TipoUsuario> t_user = tipoUsuarioRepository.findById(id);
        if (t_user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
      tipoUsuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> atualizarTipoUsuario(@PathVariable Integer id, @RequestBody TipoUsuario tipoUsuario){
        Optional<TipoUsuario> t_user = tipoUsuarioRepository.findById(id);
        if (t_user.isEmpty()) {
            return  ResponseEntity.notFound().build();
        }
        TipoUsuario saved = t_user.get();
        saved.setNome(tipoUsuario.getNome());
        saved = tipoUsuarioRepository.save(saved);
        return ResponseEntity.ok(saved);
    }
}
