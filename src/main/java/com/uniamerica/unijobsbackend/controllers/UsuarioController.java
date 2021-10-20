package com.uniamerica.unijobsbackend.controllers;

import com.uniamerica.unijobsbackend.dto.UsuarioDto;
import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@OpenAPIDefinition
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired //injecao de dependecias conceito - SOLID eh um padrao de design de codigo
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>>BuscarTodos(){
        List<UsuarioDto> usuarios = usuarioRepository.findAll()
                .stream().map(user->new UsuarioDto(user)).collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarUsuario(@PathVariable Integer id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UsuarioDto(user.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isEmpty()) {
            return  ResponseEntity.notFound().build();
        }
        Usuario saved = user.get();
        saved.setEmail(usuario.getEmail());
        saved.setSenha(usuario.getSenha());
        saved.setNome(usuario.getNome());
        saved.setCelular(usuario.getCelular());
        saved.setRa(usuario.getCelular());
        saved = usuarioRepository.save(saved);
        return ResponseEntity.ok(new UsuarioDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuario(@PathVariable Integer id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
