package com.uniamerica.unijobsbackend.dto;

import com.uniamerica.unijobsbackend.models.TipoUsuario;
import lombok.Getter;

@Getter
public class TipoUsuarioDto {
    private Integer id;
    private String nome;

    public TipoUsuarioDto(TipoUsuario tipoUsuario) {
        this.id = tipoUsuario.getId();
        this.nome = tipoUsuario.getNome();
    }
}
