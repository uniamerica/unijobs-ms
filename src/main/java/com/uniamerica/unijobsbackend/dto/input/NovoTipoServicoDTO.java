package com.uniamerica.unijobsbackend.dto.input;

import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NovoTipoServicoDTO {
    @NotBlank(message = "Titulo é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;

    public Servico converteModelo(){
        Servico servico = new Servico();
        servico.setTitulo(nome);
        servico.setDescricao(descricao);
        return servico;
    }
    
}
