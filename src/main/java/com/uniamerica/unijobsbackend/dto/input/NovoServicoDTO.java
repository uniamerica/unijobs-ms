package com.uniamerica.unijobsbackend.dto.input;

import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NovoServicoDTO {
    @NotBlank(message = "Titulo é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;

    @NotNull(message = "O preço é obrigatório.")
    private Double preco;

    @NotBlank(message = "A Miniatura é obrigatório")
    private String miniatura;
    
    @NotNull(message = "O prazo é obrigatório.")
    private Integer prazo;

    @NotNull(message = "O tipo servico é obrigatório")
    private Integer id_tipo_servico;

    public Servico converteModelo(){
        Servico servico = new Servico();
        servico.setTitulo(titulo);
        servico.setDescricao(descricao);
        servico.setPreco(preco);
        servico.setMiniatura(miniatura);
        servico.setPrazo(prazo);
        servico.setTipoServico(new TipoServico(id_tipo_servico));

        return servico;
    }
    
}
