package com.uniamerica.unijobsbackend.auth.dto;

import com.sun.istack.NotNull;
import com.uniamerica.unijobsbackend.models.Usuario;
import lombok.Data;

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

    public RegisterDto(String email, String senha, String nome, String celular, String ra) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.celular = celular;
        this.ra = ra;
    }

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


