package com.uniamerica.unijobsbackend.dto.input;

import com.uniamerica.unijobsbackend.models.Usuario;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AtualizarUsuarioDTO {

    @NotNull(message = "O nome é obrigatório")
    private String nome;

    private String celular;

    @NotNull(message = "O ra é obrigatório")
    private  String ra;

    public Usuario toModel(){
        return Usuario.builder()
                .nome(nome)
                .celular(celular)
                .ra(ra)
                .build();
    }

}
