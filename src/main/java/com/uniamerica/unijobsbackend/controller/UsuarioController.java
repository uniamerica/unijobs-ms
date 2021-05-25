package com.uniamerica.unijobsbackend.controller;

import com.uniamerica.unijobsbackend.model.Usuario;
import com.uniamerica.unijobsbackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired //injecao de dependecias conceito - SOLID eh um padrao de design de codigo
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>>BuscarTodos(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Integer id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }
    @PostMapping("/{id}")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario user){
        Usuario saved = usuarioRepository.save(user);
        return new ResponseEntity<Usuario>(saved, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario){
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
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> removerUsuario(@PathVariable Integer id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
