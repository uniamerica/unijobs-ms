package com.uniamerica.unijobsbackend.auth.dto;

import com.sun.istack.NotNull;
import com.uniamerica.unijobsbackend.model.Usuario;
import lombok.Data;

import javax.persistence.Column;
@Data
public class RegisterDto {
    @NotNull
    private String email;
    @NotNull
    private String senha;
    @NotNull
    private String nome;
    @NotNull
    private String celular;
    @NotNull
    private  String ra;

    public Usuario toModel(){
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setCelular(celular);
        usuario.setNome(nome);
        usuario.setRa(ra);
        return usuario;
    }
}


