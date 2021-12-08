package com.uniamerica.unijobsbackend.services;


import com.uniamerica.unijobsbackend.Excessoes.AuthorizationExeption;
import com.uniamerica.unijobsbackend.Excessoes.RecursoNaoEncontradoExcessao;
import com.uniamerica.unijobsbackend.auth.services.UserService;
import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public List<Usuario> index() {
        return usuarioRepository.findAll();
    }

    public Usuario show(int id) {

        var auth = UserService.getAuthenticatedUser(); //recupera o usuario auteticado do token

        if (auth == null || auth.getId() != id){
            throw new AuthorizationExeption("Acesso recusado");
        }
        return usuarioRepository.findById(id).orElseThrow(() ->new RecursoNaoEncontradoExcessao("User not found"));
    }

    public Usuario update(Usuario userToUpdate, int id) {

        var auth = UserService.getAuthenticatedUser(); //recupera o usuario auteticado do token

        if (auth == null || auth.getId() != id){
            throw new AuthorizationExeption("Acesso recusado");
        }

        var user = this.show(id);
        user.setNome(userToUpdate.getNome());
        user.setRa(userToUpdate.getRa());
        user.setCelular(userToUpdate.getCelular());


        return usuarioRepository.save(user);
    }

    public void destroy(int id) {

        var auth = UserService.getAuthenticatedUser(); //recupera o usuario auteticado do token

        if (auth == null || auth.getId() != id){
            throw new AuthorizationExeption("Acesso recusado");
        }
        this.show(id);
        usuarioRepository.deleteById(id);
    }
}
