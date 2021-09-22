package com.uniamerica.unijobsbackend.services;


import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario store(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    public List<Usuario> index() {
        return usuarioRepository.findAll();
    }
    public Optional<Usuario> show(Integer id) {
        return usuarioRepository.findById(id);
    }
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    public void destroy(Integer id) {
        Optional<Usuario> optionalUsuario = this.show(id);

        if (optionalUsuario.isPresent()) {
            usuarioRepository.deleteById(id);
        }
    }



}
