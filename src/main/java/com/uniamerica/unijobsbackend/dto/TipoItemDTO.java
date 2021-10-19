package com.uniamerica.unijobsbackend.dto;

import com.uniamerica.unijobsbackend.models.TipoProduto;
import com.uniamerica.unijobsbackend.models.TipoServico;
import lombok.Data;

@Data
public class TipoItemDTO {
    private Integer id;
    private String nome;
    private String descricao;

    public TipoItemDTO(TipoServico tipoServico) {
        this.id = tipoServico.getId_tipo_servico();
        this.nome = tipoServico.getNome();
        this.descricao = tipoServico.getDescricao();
    }
    public TipoItemDTO(TipoProduto tipoProduto) {
        this.id = tipoProduto.getId_tipo_produto();
        this.nome = tipoProduto.getNome();
        this.descricao = tipoProduto.getDescricao();
    }
}
