package com.uniamerica.unijobsbackend.dto;

import com.uniamerica.unijobsbackend.models.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDto {
    private Integer id;
    private String email;
    private String nome;
    private String celular;
    private  String ra;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.celular = usuario.getCelular();
        this.ra = usuario.getRa();
    }
}
